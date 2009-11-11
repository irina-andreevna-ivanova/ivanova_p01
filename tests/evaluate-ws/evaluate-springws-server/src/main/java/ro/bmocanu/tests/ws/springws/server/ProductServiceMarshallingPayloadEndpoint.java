/*- 
 * Copyright Bogdan Mocanu, 2009
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package ro.bmocanu.tests.ws.springws.server;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.oxm.Marshaller;
import org.springframework.ws.server.endpoint.AbstractMarshallingPayloadEndpoint;

import ro.bmocanu.tests.ws.model.Product;
import ro.bmocanu.tests.ws.model.impl.ProductManager;
import ro.bmocanu.tests.ws.springws.generated.GetProductByIdRequest;
import ro.bmocanu.tests.ws.springws.generated.GetProductByIdResponse;
import ro.bmocanu.tests.ws.springws.generated.ObjectFactory;
import ro.bmocanu.tests.ws.springws.generated.ProductType;

/**
 * @author mocanu
 */
public class ProductServiceMarshallingPayloadEndpoint extends AbstractMarshallingPayloadEndpoint {

    public ProductServiceMarshallingPayloadEndpoint(Marshaller marshaller) {
        super( marshaller );
    }

    @SuppressWarnings( "restriction" )
    protected Object invokeInternal( Object request ) throws Exception {
        ObjectFactory objectFactory = new ObjectFactory();

        GetProductByIdRequest gpbiRequest = (GetProductByIdRequest) request;
        long productId = gpbiRequest.getProductId();
        Product product = ProductManager.singleInstance.getProductById( productId );

        XMLGregorianCalendar cal = new com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl();
        Calendar c = Calendar.getInstance();
        cal.setTime( c.get( Calendar.HOUR ), c.get( Calendar.MINUTE ), c.get( Calendar.SECOND ) );
        
        GetProductByIdResponse gpbiResponse = objectFactory.createGetProductByIdResponse();

        ProductType xmlProduct = objectFactory.createProductType();
        xmlProduct.setId( product.getId() );
        xmlProduct.setName( product.getName() );
        xmlProduct.setDescription( product.getDescription() );
        xmlProduct.setReceived( cal );
        
        gpbiResponse.setProduct( xmlProduct );

        return gpbiResponse;
    }
}
