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

/**
 * Custom exception thrown when the product could not be found.
 *
 * @author mocanu
 */
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2046554666219357862L;
    
    private long troublingId;

    /**
     * @param message
     * @param troublingId
     */
    public ProductNotFoundException(String message, long troublingId) {
        super( message );
        this.troublingId = troublingId;
    }

    /**
     * @param cause
     * @param troublingId
     */
    public ProductNotFoundException(Throwable cause, long troublingId) {
        super( cause );
        this.troublingId = troublingId;
    }

    /**
     * Returns the troublingId
     *
     * @return the troublingId
     */
    public long getTroublingId() {
        return troublingId;
    }

}
