package ro.bmocanu.tests.springws.web;

import org.jdom.Element;
import org.jdom.xpath.XPath;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;


/**
 *
 *
 * @author bogdan.mocanu
 */
public class AddressBookServiceJDomPayloadEndpoint extends AbstractJDomPayloadEndpoint {

    private XPath firstNameExpression;
    private XPath lastNameExpression;
    private XPath phoneNumberExpression;

    /**
     *
     */
    public AddressBookServiceJDomPayloadEndpoint() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Element invokeInternal( Element arg0 ) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
