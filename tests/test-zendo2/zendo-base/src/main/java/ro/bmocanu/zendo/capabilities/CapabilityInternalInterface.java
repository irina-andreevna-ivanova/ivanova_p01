package ro.bmocanu.zendo.capabilities;

import ro.bmocanu.zendo.Destroyable;
import ro.bmocanu.zendo.Initializable;
import ro.bmocanu.zendo.base.PropertiesManager;
import ro.bmocanu.zendo.base.TestDescriptor;

/**
 * This internal interface must be implemented by the Zendo capabilities "behind-the-scenes" in order to
 * allow the Zendo core to set the properties manager, parent capabilities manager, the test descriptor as
 * well as the init() &amp; destroy() methods inherited from {@link ro.bmocanu.zendo.Initializable} and
 * {@link ro.bmocanu.zendo.Destroyable}.
 * 
 * <p>
 * Since these methods must not be visible to the client (the actual tests that use the capabilities), each
 * capability must implement two interfaces: this internal capability interface and the actual interface of
 * the capability.
 * </p>
 *
 * @author bogdan.mocanu
 */
public interface CapabilityInternalInterface extends Initializable, Destroyable {

    public String getName();
    public void setName( String name );

    public CapabilityType getType();
    public void setType( CapabilityType type );

    public void setPropertiesManager( PropertiesManager propertiesManager );
    public void setCapabilitiesManager( CapabilitiesManager capabilitiesManager );
    public void setTestDescriptor( TestDescriptor testDescriptor );

    /**
     * This is a generic-return-type version of the main method exposed by each capability. This should
     * return the core object (the SQL Connection, the ApplicationContext etc). 
     *
     * @return the core object of the capability, as an Object
     */
    public Object getCapabilityObject();

}
