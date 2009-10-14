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

package ro.bmocanu.zendo.capabilities.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ro.bmocanu.zendo.ZendoConstants;
import ro.bmocanu.zendo.capabilities.AbstractCapability;
import ro.bmocanu.zendo.capabilities.intf.JDBCCapability;

/**
 * Capability that allows test to use plain JDBC connections to a particular database.
 * 
 * @author bogdan.mocanu
 */
public class JDBCCapabilityImpl extends AbstractCapability implements JDBCCapability {
    private static Log log = LogFactory.getLog( JDBCCapability.class.getName() );

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Object getCapabilityObject() {
        return getConnection();
    }

    public void init() {
        String driverClassName = propertiesManager.getString( propertiesManager.formatPropertyForCapability(
                ZendoConstants.PROPERTY_CAPABILITY_PREFIX_JDBC, capabilityName, "driverName" ) );
        String connectionURL = propertiesManager.getString( propertiesManager.formatPropertyForCapability(
                ZendoConstants.PROPERTY_CAPABILITY_PREFIX_JDBC, capabilityName, "url" ) );
        String username = propertiesManager.getString( propertiesManager.formatPropertyForCapability(
                ZendoConstants.PROPERTY_CAPABILITY_PREFIX_JDBC, capabilityName, "username" ) );
        String password = propertiesManager.getString( propertiesManager.formatPropertyForCapability(
                ZendoConstants.PROPERTY_CAPABILITY_PREFIX_JDBC, capabilityName, "password" ) );
        try {
            Class.forName( driverClassName );
            connection = DriverManager.getConnection( connectionURL, username, password );

        } catch ( ClassNotFoundException exception ) {
            log.error( exception );
        } catch ( SQLException exception ) {
            log.error( exception );
        }
    }

    public void destroy() {
        try {
            connection.close();
        } catch ( SQLException e ) {
            log.error( e );
        }
    }
}
