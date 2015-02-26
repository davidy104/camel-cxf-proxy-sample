
package nz.co.ws.guice.client.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nz.co.ws.guice.client.stub package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _User_QNAME = new QName("http://ws.integration.sample.ws.co.nz", "User");
    private final static QName _CreateUserResponse_QNAME = new QName("http://ws.integration.sample.ws.co.nz", "CreateUserResponse");
    private final static QName _CreateUserRequest_QNAME = new QName("http://ws.integration.sample.ws.co.nz", "CreateUserRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nz.co.ws.guice.client.stub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link CreateUserRequest }
     * 
     */
    public CreateUserRequest createCreateUserRequest() {
        return new CreateUserRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.sample.ws.co.nz", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.sample.ws.co.nz", name = "CreateUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.integration.sample.ws.co.nz", name = "CreateUserRequest")
    public JAXBElement<CreateUserRequest> createCreateUserRequest(CreateUserRequest value) {
        return new JAXBElement<CreateUserRequest>(_CreateUserRequest_QNAME, CreateUserRequest.class, null, value);
    }

}
