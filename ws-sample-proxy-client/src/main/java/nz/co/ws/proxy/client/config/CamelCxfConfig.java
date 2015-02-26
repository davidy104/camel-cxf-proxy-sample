package nz.co.ws.proxy.client.config;

import nz.co.ws.proxy.client.stub.UserEndpointPortType;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelCxfConfig {

	@Bean
	public CxfEndpoint userServiceEndpoint() {
		CxfEndpoint cxfEndpoint = new CxfEndpoint();
		cxfEndpoint.setAddress("http://localhost:9898/wsproxy/ws/user");
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
