
package nz.co.ws.guice.client.stub;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2015-02-26T09:50:13.968+13:00
 * Generated source version: 3.0.2
 * 
 */
public final class UserEndpointPortType_UserEndpointPortTypePort_Client {

    private static final QName SERVICE_NAME = new QName("http://ws.integration.sample.ws.co.nz", "UserServiceService");

    private UserEndpointPortType_UserEndpointPortTypePort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = UserServiceService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        UserServiceService ss = new UserServiceService(wsdlURL, SERVICE_NAME);
        UserEndpointPortType port = ss.getUserEndpointPortTypePort();  
        
        {
        System.out.println("Invoking createUser...");
        nz.co.ws.guice.client.stub.CreateUserRequest _createUser_parameters = null;
        nz.co.ws.guice.client.stub.CreateUserResponse _createUser__return = port.createUser(_createUser_parameters);
        System.out.println("createUser.result=" + _createUser__return);


        }

        System.exit(0);
    }

}
