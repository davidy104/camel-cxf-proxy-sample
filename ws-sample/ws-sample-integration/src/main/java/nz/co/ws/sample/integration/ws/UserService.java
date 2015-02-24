package nz.co.ws.sample.integration.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import nz.co.ws.sample.model.User;

@WebService(targetNamespace = "http://ws.integration.sample.ws.co.nz", name = "UserEndpoint")
public interface UserService {

	@WebResult(name = "userResponse", targetNamespace = "http://ws.integration.sample.ws.co.nz", partName = "user")
	@WebMethod(operationName = "CreateUser", action = "http://ws.integration.sample.ws.co.nz/CreateUser")
	User createUser(@WebParam(partName = "role", name = "roleRequest", targetNamespace = "http://ws.integration.sample.ws.co.nz") String role,
			@WebParam(partName = "info", name = "infoRequest", targetNamespace = "http://ws.integration.sample.ws.co.nz") String info) throws FaultMessage;
}
