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

import java.util.Properties;

/**
 * Wrapper class for the <tt>java.util.Properties</tt> class, providing
 * conversion methods for working with the properties.
 * 
 * @author Bogdan Mocanu
 */
public class PropertiesManager {

    private Properties properties;

    public PropertiesManager(Properties properties) {
        super();
        this.properties = properties;
    }

    /**
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    public int getInt( String name ) throws PropertyNotFoundException {
        return Integer.parseInt( getString( name ) ); 
    }

    public long getLong( String name ) throws PropertyNotFoundException {
        return Long.parseLong( getString( name ) ); 
    }

    public float getFloat( String name ) throws PropertyNotFoundException {
        return Float.parseFloat( getString( name ) ); 
    }

    public double getDouble( String name ) throws PropertyNotFoundException {
        return Double.parseDouble( getString( name ) ); 
    }

    public boolean getBoolean( String name ) throws PropertyNotFoundException {
        return Boolean.parseBoolean( getString( name ) ); 
    }

    public String getString( String name ) throws PropertyNotFoundException {
        String value = properties.getProperty( name );
        if ( value == null ) {
            throw new PropertyNotFoundException( "No value has been specified for property " + name );
        }
        return value;
    }

    public String formatPropertyForCapability( String prefix, String capabilityName, String suffix ) {
        return prefix + "." + ( capabilityName != null ? capabilityName + "." : "" ) + suffix;
    }

}
