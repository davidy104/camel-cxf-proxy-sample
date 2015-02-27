package nz.co.ws.proxy.integration.route;

import java.io.InputStream;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

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
				.convertBodyTo(String.class)
				.to("log:input")
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						Map<String, Object> headerMap =
								exchange.getIn().getHeaders();
						for (Map.Entry<String, Object> entry :
						headerMap.entrySet()) {
							System.out.println("Key : " + entry.getKey() +
									" Value : "
									+ entry.getValue());
						}
						final String bodyString = exchange.getIn().getBody(String.class);
						System.out.println("bodyString-------------:{} " + bodyString);
					}
				})
				.to("log:aftTransform")
				.convertBodyTo(InputStream.class)
				.removeHeaders("CamelHttp*")
				.to("log:output")
				.to(WS_ENDPOINT)
				.end();
	}
}
