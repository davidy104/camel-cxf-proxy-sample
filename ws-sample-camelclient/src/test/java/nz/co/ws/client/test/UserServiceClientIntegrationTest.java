package nz.co.ws.client.test;

import java.util.List;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfiguration.class)
public class UserServiceClientIntegrationTest {
	@Produce
	private ProducerTemplate producer;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceClientIntegrationTest.class);

	// userServiceEndpoint
	@Test
	public void test() {
		List<String> paramsList = Lists.<String> newArrayList();
		paramsList.add("admin");
		paramsList.add("<user>");
		CreateUserResponse result = producer.requestBodyAndHeader("cxf:bean:userServiceEndpoint", paramsList, "operationName", "CreateUserService", CreateUserResponse.class);
		LOGGER.info("result:{} ", result.getUser());
	}

}
