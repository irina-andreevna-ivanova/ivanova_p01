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

/**
 * 
 *
 * @author mocanu
 */
public class Script {

    private String scriptContent;
    
    private long lastModified;

    /**
     * @param scriptContent
     * @param lastModified
     */
    public Script(String scriptContent, long lastModified) {
        super();
        this.scriptContent = scriptContent;
        this.lastModified = lastModified;
    }

    /**
     * Returns the scriptContent
     *
     * @return the scriptContent
     */
    public String getScriptContent() {
        return scriptContent;
    }

    /**
     * Sets the scriptContent to the given value.
     *
     * @param scriptContent the scriptContent to set
     */
    public void setScriptContent( String scriptContent ) {
        this.scriptContent = scriptContent;
    }

    /**
     * Returns the lastModified
     *
     * @return the lastModified
     */
    public long getLastModified() {
        return lastModified;
    }

    /**
     * Sets the lastModified to the given value.
     *
     * @param lastModified the lastModified to set
     */
    public void setLastModified( long lastModified ) {
        this.lastModified = lastModified;
    }
    
    public static void main( String[] args ) {
        System.out.println( System.getProperty( "java.protocol.handler.pkgs" ) );
        
    }
    
}
