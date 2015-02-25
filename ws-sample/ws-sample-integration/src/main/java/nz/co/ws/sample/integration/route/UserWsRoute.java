package nz.co.ws.sample.integration.route;

import javax.annotation.Resource;

import nz.co.ws.sample.integration.processor.UserServiceProcessor;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserWsRoute extends RouteBuilder {

	public static final String ENDPOINT = "cxf:bean:userWsEndpoint";

	@Resource
	private UserServiceProcessor userServiceProcessor;

	@Override
	public void configure() throws Exception {

		from(ENDPOINT).routeId(ENDPOINT).onException(Exception.class)
				.handled(true)
				// .setFaultBody(method(FaultHandler.class,
				// "createFault"))
				.end()
				.to("log:input")
				.process(userServiceProcessor)
				.to("log:output");
	}
}
