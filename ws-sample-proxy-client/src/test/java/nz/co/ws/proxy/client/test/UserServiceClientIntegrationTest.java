package nz.co.ws.proxy.client.test;

import nz.co.ws.proxy.client.stub.CreateUserRequest;
import nz.co.ws.proxy.client.stub.CreateUserResponse;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfiguration.class)
public class UserServiceClientIntegrationTest {
	@Produce
	private ProducerTemplate producer;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceClientIntegrationTest.class);

	// userServiceEndpoint
	@Test
	public void test() {
		// List<String> paramsList = Lists.<String> newArrayList();
		// paramsList.add("admin");
		// paramsList.add("<user>");

		CreateUserRequest request = new CreateUserRequest();
		request.setInfo("<user>");
		request.setRole("admin");

		CreateUserResponse result = producer.requestBodyAndHeader("cxf:bean:userServiceEndpoint", request, "operationName", "CreateUser", CreateUserResponse.class);
		LOGGER.info("result:{} ", result.getUser());
	}

}
