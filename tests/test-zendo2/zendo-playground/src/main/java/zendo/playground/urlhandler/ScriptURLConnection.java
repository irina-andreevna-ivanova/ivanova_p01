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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author mocanu
 */
public class ScriptURLConnection extends URLConnection {

    /**
     * The code of the current script.
     */
    private String scriptContent;

    /**
     * The timestamp when this script was last modified.
     */
    private long lastModified;

    /**
     * @param url
     * @param scriptContent
     * @param lastModified
     */
    public ScriptURLConnection(URL url, String scriptContent, long lastModified) {
        super( url );
        this.scriptContent = scriptContent;
        this.lastModified = lastModified;
    }

    /**
     * Convenience constructor, for creating script URL connection starting from a {@link Script} object.
     * 
     * @param url
     */
    public ScriptURLConnection(URL url, Script script) {
        this( url, script.getScriptContent(), script.getLastModified() );
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect() throws IOException {
        // no code here (no need for it)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream( scriptContent.getBytes( "utf-8" ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLastModified() {
        return lastModified;
    }

}
