package nz.co.ws.sample.integration.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.RequestWrapper;

import nz.co.ws.sample.model.User;

@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@WebService(targetNamespace = "http://ws.integration.sample.ws.co.nz", name = "UserEndpointPortType")
public interface UserService {
	@RequestWrapper(
			localName = "CreateUserRequest",
			targetNamespace = "http://ws.integration.sample.ws.co.nz")
	@WebResult(name = "User", targetNamespace = "http://ws.integration.sample.ws.co.nz")
	@WebMethod(operationName = "CreateUser", action = "http://ws.integration.sample.ws.co.nz/CreateUser")
	User createUser(
			@WebParam(name = "role", targetNamespace = "http://ws.integration.sample.ws.co.nz") String role,
			@WebParam(name = "info", targetNamespace = "http://ws.integration.sample.ws.co.nz") String info);
}
