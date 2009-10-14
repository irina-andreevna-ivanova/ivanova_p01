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

package ro.bmocanu.zendo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ro.bmocanu.zendo.base.PropertiesManager;
import ro.bmocanu.zendo.base.PropertyNotFoundException;
import ro.bmocanu.zendo.capabilities.CapabilitiesManager;

public class AbstractZendoTest implements ZendoTest {
    private static Log log = LogFactory.getLog( AbstractZendoTest.class );
    
    protected PropertiesManager properties;
    
    protected CapabilitiesManager capabilities;

    /* (non-Javadoc)
     * @see ro.bmocanu.zendo.ZendoTest#setPropertiesManager(ro.bmocanu.zendo.base.PropertiesManager)
     */
    public void setPropertiesManager(PropertiesManager propertiesManager) {
	properties = propertiesManager;
    }

    /* (non-Javadoc)
     * @see ro.bmocanu.zendo.ZendoTest#setCapabilitiesManager(ro.bmocanu.zendo.capabilities.CapabilitiesManager)
     */
    public void setCapabilitiesManager( CapabilitiesManager capabilities ) {
        this.capabilities = capabilities;
    }

    public void test() {
        // default implementation for this method
        log.warn( "The default test() method was invoked. You should overide this method in order to implement your test." );
    }

    // ------------------------------------------------------------------------------------------------------

    protected boolean getBoolean( String name ) throws PropertyNotFoundException {
        return properties.getBoolean( name );
    }

    protected double getDouble( String name ) throws PropertyNotFoundException {
        return properties.getDouble( name );
    }

    protected float getFloat( String name ) throws PropertyNotFoundException {
        return properties.getFloat( name );
    }

    protected int getInt( String name ) throws PropertyNotFoundException {
        return properties.getInt( name );
    }

    protected long getLong( String name ) throws PropertyNotFoundException {
        return properties.getLong( name );
    }

    protected String getString( String name ) throws PropertyNotFoundException {
        return properties.getString( name );
    }
}
