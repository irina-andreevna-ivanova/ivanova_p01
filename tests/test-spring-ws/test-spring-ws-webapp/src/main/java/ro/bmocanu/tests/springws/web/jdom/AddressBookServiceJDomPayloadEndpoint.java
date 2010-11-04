package ro.bmocanu.tests.springws.web.jdom;

import org.jdom.Element;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;


/**
 *
 *
 * @author bogdan.mocanu
 */
public class AddressBookServiceJDomPayloadEndpoint extends AbstractJDomPayloadEndpoint {

    /**
     *
     */
    public AddressBookServiceJDomPayloadEndpoint() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Element invokeInternal( Element requestElement ) throws Exception {
        System.out.println( ">>> Received: " + requestElement.toString() );
        return null;
    }

}
