package nz.co.ws.sample.integration.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import nz.co.ws.sample.integration.route.UserWsRoute;

import org.apache.camel.CamelContext;
import org.apache.camel.ThreadPoolRejectedPolicy;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spi.ThreadPoolProfile;
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
	private CxfEndpoint userWsEndpoint;

	@Resource
	private ApplicationContext context;

	@Resource
	private UserWsRoute userWsRoute;

	@Bean
	public CamelBeanPostProcessor camelBeanPostProcessor() {
		CamelBeanPostProcessor camelBeanPostProcessor = new CamelBeanPostProcessor();
		camelBeanPostProcessor.setApplicationContext(context);
		return camelBeanPostProcessor;
	}

	@Bean
	public CamelContext camelContext() throws Exception {
		SpringCamelContext camelContext = new SpringCamelContext(context);
		camelContext.getExecutorServiceManager().registerThreadPoolProfile(
				custThreadPoolProfile());
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("userWsEndpoint", userWsEndpoint);
		camelContext.setRegistry(registry);
		camelContext.addRoutes(userWsRoute);
		return camelContext;
	}

	@Bean
	public ThreadPoolProfile custThreadPoolProfile() {
		ThreadPoolProfile profile = new ThreadPoolProfile();
		profile.setId("genericThreadPool");
		profile.setKeepAliveTime(120L);
		profile.setPoolSize(2);
		profile.setMaxPoolSize(10);
		profile.setTimeUnit(TimeUnit.SECONDS);
		profile.setRejectedPolicy(ThreadPoolRejectedPolicy.Abort);
		return profile;
	}

}
