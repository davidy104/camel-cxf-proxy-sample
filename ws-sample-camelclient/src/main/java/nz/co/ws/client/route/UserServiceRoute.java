package nz.co.ws.client.route;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

public class UserServiceRoute extends RouteBuilder {
	public static final String WS_ENDPOINT = "cxf:bean:userServiceEndpoint?dataFormat=POJO";

	@Override
	public void configure() throws Exception {
		from("direct:userService")
				.setExchangePattern(ExchangePattern.InOut)
				.setHeader("operationName", constant("CreateUserService"))
				.to(WS_ENDPOINT).to("log:myLog?showAll=true")
				.end();
	}

}
