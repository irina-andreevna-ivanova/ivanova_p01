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

import org.springframework.ws.server.endpoint.AbstractDomPayloadEndpoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ro.bmocanu.tests.ws.model.Product;
import ro.bmocanu.tests.ws.model.impl.ProductManager;

/**
 * 
 *
 * @author mocanu
 */
public class ProductServiceDomPayloadEndpoint extends AbstractDomPayloadEndpoint {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Element invokeInternal( Element requestElement, Document responseDocument ) throws Exception {
        NodeList nodeList = requestElement.getChildNodes();
        String requestText = nodeList.item( 1 ).getTextContent();
        long id = Long.parseLong( requestText );
        System.out.println( "Client asked for product with ID=" + id );
        
        Product product = ProductManager.singleInstance.getProductById( id );

        Element responseElement = responseDocument.createElementNS("http://test.ro/product-service", "GetProductByIdResponse");
        Element productElement = responseDocument.createElementNS( "http://test.ro/product-service", "Product");
        productElement.setAttribute( "id", product.getId() + "" );
        productElement.setAttribute( "name", product.getName() );
        productElement.setAttribute( "descriptions", product.getDescription() );
        productElement.setAttribute( "received", product.getReceived().toString() );
        
        responseElement.appendChild( productElement );
        
        return responseElement;
    }

}
