package nz.co.ws.sample.integration.route;

import nz.co.ws.sample.integration.ws.FaultHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserWsRoute extends RouteBuilder {

	public static final String ENDPOINT = "cxf:bean:userWsEndpoint";

	@Override
	public void configure() throws Exception {

		from(ENDPOINT).routeId(ENDPOINT).onException(Exception.class)
				.handled(true)
				.setFaultBody(method(FaultHandler.class, "createFault")).end()
				.to("log:input")
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {

					}
				});
	}
}
