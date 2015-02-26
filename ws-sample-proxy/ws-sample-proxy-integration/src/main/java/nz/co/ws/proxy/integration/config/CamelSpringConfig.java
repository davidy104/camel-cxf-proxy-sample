package nz.co.ws.proxy.integration.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import nz.co.ws.proxy.integration.route.UserServiceProxyRoute;

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
	private CxfEndpoint userWsProxyEndpoint;

	@Resource
	private CxfEndpoint userRealWsEndpoint;

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
		camelContext.getExecutorServiceManager().registerThreadPoolProfile(
				custThreadPoolProfile());
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("userWsProxyEndpoint", userWsProxyEndpoint);
		registry.put("userRealWsEndpoint", userRealWsEndpoint);
		camelContext.setRegistry(registry);
		camelContext.addRoutes(new UserServiceProxyRoute());
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
