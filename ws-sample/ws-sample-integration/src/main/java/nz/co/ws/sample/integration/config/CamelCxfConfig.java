package nz.co.ws.sample.integration.config;

import nz.co.ws.sample.integration.ws.UserService;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CamelCxfConfig {

	// http://localhost:8989/wssample/ws/user?wsdl
	@Bean
	public CxfEndpoint userWsEndpoint() {
		CxfEndpoint cxfEndpoint = new CxfEndpoint();
		cxfEndpoint.setAddress("/user");
		cxfEndpoint.setServiceClass(UserService.class);
		LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
		loggingOutInterceptor.setPrettyLogging(true);
		cxfEndpoint.getOutInterceptors().add(loggingOutInterceptor);
		cxfEndpoint.setDataFormat(DataFormat.POJO);
		return cxfEndpoint;
	}

}
