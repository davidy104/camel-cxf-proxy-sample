package nz.co.ws.client.config;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spring.CamelBeanPostProcessor;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CamelSpringConfig {

	@Resource
	private Environment environment;

	@Resource
	private CxfEndpoint userServiceEndpoint;

	@Resource
	private ApplicationContext context;

	@Bean
	public CamelBeanPostProcessor camelBeanPostProcessor() {
		CamelBeanPostProcessor camelBeanPostProcessor = new CamelBeanPostProcessor();
		camelBeanPostProcessor.setApplicationContext(context);
		return camelBeanPostProcessor;
	}

	@Bean
	public CamelContext camelContext() throws Exception {
		SpringCamelContext camelContext = new SpringCamelContext(context);
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("userServiceEndpoint", userServiceEndpoint);
		camelContext.setRegistry(registry);
//		camelContext.addRoutes(new UserServiceRoute());
		return camelContext;
	}

}
