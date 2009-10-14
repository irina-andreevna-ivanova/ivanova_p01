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

package ro.bmocanu.zendo.capabilities;

import ro.bmocanu.zendo.capabilities.impl.JDBCCapabilityImpl;
import ro.bmocanu.zendo.capabilities.impl.SpringCapabilityImpl;
import ro.bmocanu.zendo.capabilities.impl.SpringLDAPTemplateCapabilityImpl;

/**
 * Factory class for capability objects. To implement a capability, you have to take the following
 * steps:
 * <ol>
 * <li>Implement a capability interface, extending {@link Capability}.</li>
 * <li>Add the capability type to the {@link CapabilityType} enumeration.</li>
 * <li>Implement the capability interface (optionally by also extending {@link AbstractCapability}).
 * </li>
 * <li>Modify the {@link CapabilityFactory} class, so that it returns the appropiate class for the
 * given type.</li>
 * </ol>
 *
 * @author Bogdan Mocanu
 */
public class CapabilityFactory {

    @SuppressWarnings( "unchecked" )
    public static <T extends Capability> T createCapability( CapabilityType type ) {
        switch ( type ) {
            case JDBC:
                return (T) new JDBCCapabilityImpl();
            case SPRING:
                return (T) new SpringCapabilityImpl();
            case SPRING_LDAP_TEMPLATE:
                return (T) new SpringLDAPTemplateCapabilityImpl();
                // problem if the program gets to the default branch
            default:
                throw new AssertionError( "This type of capability is not implemented or the type is not declared here, in the capability factory" );
        }
    }

}
