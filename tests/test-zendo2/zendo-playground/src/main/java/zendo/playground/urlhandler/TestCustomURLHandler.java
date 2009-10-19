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

package zendo.playground.urlhandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

/**
 * @author mocanu
 */
public class TestCustomURLHandler {

    public static void main( String[] args ) throws IOException {
        
        
        URL url = new URL( "mydb://myservice/myprop" );
        URLConnection connection = url.openConnection();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy( connection.getInputStream(), baos );

        System.out.println( baos.toString() );
    }

}
