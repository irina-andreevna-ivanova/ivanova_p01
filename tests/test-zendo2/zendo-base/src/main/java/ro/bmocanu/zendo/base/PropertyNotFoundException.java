/* Copyright Bogdan Mocanu, 2008
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
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

package ro.bmocanu.zendo.base;

/**
 * Exception that is thrown when Zendo cannot find one of the properties that the client is asking for.
 * This exception is a descendant of runtime exception, allowing the user not to bother for handling it.
 * 
 * @author Bogdan Mocanu
 */
public class PropertyNotFoundException extends NullPointerException {

    private static final long serialVersionUID = 1023486056180922893L;

    public PropertyNotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PropertyNotFoundException(String arg0) {
        super( arg0 );
        // TODO Auto-generated constructor stub
    }

}
