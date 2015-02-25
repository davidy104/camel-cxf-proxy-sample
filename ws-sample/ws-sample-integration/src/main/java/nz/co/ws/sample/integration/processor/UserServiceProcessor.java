package nz.co.ws.sample.integration.processor;

import java.util.List;

import nz.co.ws.sample.model.User;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceProcessor implements Processor {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceProcessor.class);

	@SuppressWarnings({ "unchecked" })
	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("exchange:{} ", exchange);
		Message inMessage = exchange.getIn();
		LOGGER.info("body:{} ", inMessage.getBody());
		LOGGER.info("body class:{} ", inMessage.getBody().getClass());
		LOGGER.info("headers:{} ", inMessage.getHeaders());
		LOGGER.info("properties:{} ", exchange.getProperties());
		LOGGER.info("operationName:{} ", inMessage.getHeaders().get("operationName"));

		List<String> paramsList = inMessage.getBody(List.class);
		LOGGER.info("paramsList:{} ", paramsList);

		User user = new User.Builder().userId("testId").build();
		exchange.getOut().setBody(user, User.class);
	}

}
