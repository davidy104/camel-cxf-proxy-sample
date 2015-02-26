package nz.co.ws.proxy.integration.config;

import javax.xml.namespace.QName;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CamelCxfConfig {

	// http://localhost:9898/wsproxy/ws/user?wsdl
	@Bean
	public CxfEndpoint userWsProxyEndpoint() {
		CxfEndpoint cxfEndpoint = new CxfEndpoint();
		cxfEndpoint.setAddress("/user");
		LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
		loggingOutInterceptor.setPrettyLogging(true);
		cxfEndpoint.getOutInterceptors().add(new LoggingOutInterceptor());
		cxfEndpoint.setDataFormat(DataFormat.RAW);
		cxfEndpoint.setWsdlURL("classpath:etc/user-service.wsdl");
		cxfEndpoint.setEndpointName(new QName("http://ws.integration.sample.ws.co.nz", "UserEndpointPortTypePort"));
		cxfEndpoint.setServiceName(new QName("http://ws.integration.sample.ws.co.nz", "UserServiceService"));
		return cxfEndpoint;
	}

	@Bean
	public CxfEndpoint userRealWsEndpoint() {
		CxfEndpoint cxfEndpoint = new CxfEndpoint();
		cxfEndpoint.setAddress("http://localhost:8989/wssample/ws/user");
		LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
		loggingOutInterceptor.setPrettyLogging(true);
		// cxfEndpoint.setServiceClass(UserEndpointPortType.class);
		// cxfEndpoint.setDefaultOperationNamespace("http://ws.integration.sample.ws.co.nz");
		cxfEndpoint.getOutInterceptors().add(loggingOutInterceptor);
		cxfEndpoint.setDataFormat(DataFormat.RAW);
		cxfEndpoint.setWsdlURL("classpath:etc/user-service.wsdl");
		cxfEndpoint.setEndpointName(new QName("http://ws.integration.sample.ws.co.nz", "UserEndpointPortTypePort"));
		cxfEndpoint.setServiceName(new QName("http://ws.integration.sample.ws.co.nz", "UserServiceService"));
		return cxfEndpoint;
	}

}
