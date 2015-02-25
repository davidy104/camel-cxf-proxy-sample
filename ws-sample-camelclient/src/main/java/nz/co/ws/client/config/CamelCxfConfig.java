package nz.co.ws.client.config;

import nz.co.ws.client.stub.UserEndpointPortType;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml",
		"classpath:META-INF/cxf/cxf-servlet.xml" })
public class CamelCxfConfig {

	@Bean
	public CxfEndpoint userServiceEndpoint() {
		CxfEndpoint cxfEndpoint = new CxfEndpoint();
		cxfEndpoint.setAddress("http://localhost:8989/wssample/ws/user");
		cxfEndpoint.setServiceClass(UserEndpointPortType.class);
		cxfEndpoint.getOutInterceptors().add(loggingOutInterceptor());
		cxfEndpoint.setDataFormat(DataFormat.POJO);
		cxfEndpoint.setDefaultOperationNamespace("http://ws.integration.sample.ws.co.nz");
		return cxfEndpoint;
	}

	@Bean
	public LoggingOutInterceptor loggingOutInterceptor() {
		return new LoggingOutInterceptor("target/write");
	}

}
