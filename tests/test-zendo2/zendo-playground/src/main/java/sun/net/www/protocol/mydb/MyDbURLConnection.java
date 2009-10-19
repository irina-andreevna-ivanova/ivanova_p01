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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * @author mocanu
 */
public class MyDbURLConnection extends URLConnection {

    private String value;

    /**
     * @param url
     * @param value
     */
    public MyDbURLConnection(URL url, String value) {
        super( url );
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect() throws IOException {
        // no code here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream( value.getBytes( Charset.forName( "utf-8" ) ) );
    }

}
