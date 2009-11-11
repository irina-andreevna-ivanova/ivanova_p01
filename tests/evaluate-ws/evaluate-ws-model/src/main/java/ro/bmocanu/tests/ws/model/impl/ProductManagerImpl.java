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

package ro.bmocanu.tests.ws.model.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.bmocanu.tests.ws.model.Product;

/**
 * Implementation for {@link ProductManager}.
 *
 * @author mocanu
 */
public final class ProductManagerImpl implements ProductManager {
    
    private List<Product> products;
    
    public ProductManagerImpl() {
        products = new ArrayList<Product>();
        products.add( new Product( 1, "Pronto Lemn Curat", "Pronto Lemn Curat desc", new Date() ) );
        products.add( new Product( 2, "Fairy", "Fairy pentru vase", new Date() ) );
        products.add( new Product( 3, "Snickers", "Ciocolata Snickers", new Date() ) );
        products.add( new Product( 4, "Nesquick cu Lapte", "Ciocolata cu lapte Nesquick", new Date() ) );
        products.add( new Product( 5, "Micopopcorn Clarkey's", "Floricele pentru cuptorul cu microunde de la Clarkey's", new Date() ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long calculatePrice( long productId ) {
        Product theProduct = getProductById( productId );
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append( theProduct.getId() );
        strBuilder.append( theProduct.getName() );
        strBuilder.append( theProduct.getDescription() );
        strBuilder.append( theProduct.getReceived() );
        return strBuilder.toString().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product getProductById( long productId ) {
        for( Product product : products ) {
            if ( product.getId() == productId ) {
                return product;
            }
        }
        throw new ProductNotFoundException( MessageFormat.format( "The message with ID={0} could not be found", productId ), productId );
    }

}
