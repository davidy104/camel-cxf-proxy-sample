package nz.co.ws.proxy.integration.route;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class UserServiceProxyRoute extends RouteBuilder {

	public static final String PROXY_ENDPOINT = "cxf:bean:userWsProxyEndpoint";
	public static final String WS_ENDPOINT = "cxf:bean:userRealWsEndpoint";

	@Override
	public void configure() throws Exception {
		from(PROXY_ENDPOINT).routeId(PROXY_ENDPOINT).onException(Exception.class)
				.handled(true)
				// .setFaultBody(method(FaultHandler.class,
				// "createFault"))
				.end()
				.to("log:input")
				// .transform(new Expression(){
				// @Override
				// public <T> T evaluate(Exchange exchange, Class<T> type) {
				// final String operationName =
				// exchange.getIn().getHeader(CxfConstants.OPERATION_NAME,String.class);
				// System.out.println("operationName:{} "+operationName);
				// InputStream rawInput = null;
				// rawInput = exchange.getIn().getBody(InputStream.class);
				//
				// return null;
				// }
				// })

				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						final String operationName = exchange.getIn().getHeader(CxfConstants.OPERATION_NAME, String.class);
						System.out.println("operationName:{} " + operationName);
						Map<String, Object> headerMap =
								exchange.getIn().getHeaders();
						for (Map.Entry<String, Object> entry :
						headerMap.entrySet()) {
							System.out.println("Key : " + entry.getKey() +
									" Value : "
									+ entry.getValue());
						}
						InputStream rawInput = exchange.getIn().getBody(InputStream.class);
						String bodyString = IOUtils.toString(rawInput, "UTF-8");
						System.out.println("bodyString:{} " + bodyString);

						Document respDoc = null;
						try {
							DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
							DocumentBuilder docBuilder = (DocumentBuilder) docBF.newDocumentBuilder();
							InputSource inSource = new InputSource(new InputStreamReader(rawInput, "UTF-8"));
							respDoc = docBuilder.parse(inSource);
						} catch (final Exception e) {
							throw new IllegalStateException(e);
						}
						exchange.getIn().setBody(respDoc);
					}
				})
				.to("log:aftTransform")
				// .bean(new RawDocProcessBean(), "process")
				.removeHeaders("CamelHttp*")
				.to("log:output")
				.to(WS_ENDPOINT)
				.end();
	}
}
