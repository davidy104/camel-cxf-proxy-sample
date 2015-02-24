package nz.co.ws.sample.integration.config;

import nz.co.ws.sample.integration.ws.UserService;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml",
		"classpath:META-INF/cxf/cxf-extension-soap.xml",
		"classpath:META-INF/cxf/cxf-servlet.xml" })
public class CamelCxfConfig {

	// http://localhost:8111/ws/user?wsdl
	@Bean
	public CxfEndpoint userWsEndpoint() {
		CxfEndpoint cxfEndpoint = new CxfEndpoint();
		cxfEndpoint.setAddress("/user");
		cxfEndpoint.setServiceClass(UserService.class);
		LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
		loggingOutInterceptor.setPrettyLogging(true);
		cxfEndpoint.getOutInterceptors().add(loggingOutInterceptor);
		return cxfEndpoint;
	}

}
