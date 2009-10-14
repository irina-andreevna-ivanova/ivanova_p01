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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ro.bmocanu.zendo.Destroyable;
import ro.bmocanu.zendo.Initializable;
import ro.bmocanu.zendo.base.PropertiesManager;
import ro.bmocanu.zendo.base.TestDescriptor;

/**
 * Manages the capabilities used by the current test.
 *
 * @author Bogdan Mocanu
 */
public class CapabilitiesManager implements Initializable, Destroyable {
    private static Log log = LogFactory.getLog( CapabilitiesManager.class.getName() );

    private PropertiesManager propertiesManager;

    private TestDescriptor testDescriptor;

    private Map<CapabilityDescriptor, Capability> capabilitiesMap = new HashMap<CapabilityDescriptor, Capability>();

    //----------------------------------------------------------------------------------------------
    // ---

    /*
     * (non-Javadoc)
     * 
     * @see ro.bmocanu.zendo.Initializable#init()
     */

    public void init() {
        // no code here. The capabilities are initialized lazily, when they are invoked
    }

    /*
     * (non-Javadoc)
     * 
     * @see ro.bmocanu.zendo.Destroyable#destroy()
     */
    public void destroy() {
        for ( Capability capability : capabilitiesMap.values() ) {
            CapabilityInternalInterface capabilityAsInternal = (CapabilityInternalInterface) capability;
            log.info( "Destroying " + capabilityAsInternal.getType().toString() + " capability: name= [" + capabilityAsInternal.getName() + "]" );
            capabilityAsInternal.destroy();
        }
    }

    //----------------------------------------------------------------------------------------------
    // ---

    @SuppressWarnings( "unchecked" )
    public <T extends Capability> T getCapability( CapabilityType type ) {
        return (T) getCapability( type, null );
    }

    @SuppressWarnings( "unchecked" )
    public <T extends Capability> T getCapability( CapabilityType type, String name ) {
        CapabilityDescriptor descriptorToLookFor = new CapabilityDescriptor( type, name );
        T capability = (T) capabilitiesMap.get( descriptorToLookFor );

        if ( capability != null ) {
            return capability;
        }

        CapabilityInternalInterface newCapability = (CapabilityInternalInterface) CapabilityFactory.createCapability( type );
        newCapability.setName( name );
        newCapability.setType( type );
        newCapability.setPropertiesManager( propertiesManager );
        newCapability.setCapabilitiesManager( this );
        newCapability.setTestDescriptor( testDescriptor );

        log.info( "Initializing " + type.toString() + " capability: name= [" + name + "]" );
        newCapability.init();

        capabilitiesMap.put( descriptorToLookFor, (Capability) newCapability );

        return (T) newCapability;
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * @param propertiesManager the propertiesManager to set
     */
    public void setPropertiesManager( PropertiesManager propertiesManager ) {
        this.propertiesManager = propertiesManager;
    }

    public void setTestDescriptor( TestDescriptor testDescriptor ) {
        this.testDescriptor = testDescriptor;
    }
}
