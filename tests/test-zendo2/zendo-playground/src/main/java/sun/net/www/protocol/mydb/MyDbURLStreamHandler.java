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

package sun.net.www.protocol.mydb;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * 
 *
 * @author mocanu
 */
public class MyDbURLStreamHandler extends URLStreamHandler {
    
    private static final String MYDB_PROTOCOL = "mydb";

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "unused" )
    protected URLConnection openConnection( URL url ) throws IOException {
        String protocol = url.getProtocol();
        if ( !MYDB_PROTOCOL.equals( protocol ) ) {
            // we have been invoked for a different protocol
            // so let the nature take its course
            URL classicURL = new URL( url.toString() );
            return classicURL.openConnection();
        }
        
        String service = url.getHost();
        String path = url.getPath();
        
        // process here the service name + path, and get the property
        return new MyDbURLConnection( url, "some nice test value" );
    }

}
