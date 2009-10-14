package ro.bmocanu.zendo.capabilities;

import ro.bmocanu.zendo.base.PropertiesManager;
import ro.bmocanu.zendo.base.TestDescriptor;

/**
 * This class is an incomplete implementation of the {@link ro.bmocanu.zendo.capabilities.Capability} interface and it
 * constitutes itself as a base class for all capabilities.
 *
 * @author bogdan.mocanu
 */
public abstract class AbstractCapability implements Capability, CapabilityInternalInterface {

    /**
     * The set of properties to use when initializing the capabilities.
     */
    protected PropertiesManager propertiesManager;

    /**
     * The manager responsible for all the other capabilities. This can be used in case this capability
     * needs to access other capabilities in order to properly initialize itself.
     */
    protected CapabilitiesManager capabilitiesManager;

    /**
     * The test descriptor for the currently executed test.
     */
    protected TestDescriptor testDescriptor;

    /**
     * The name of this capability.
     */
    protected String capabilityName;

    protected CapabilityType capabilityType;

    // ------------------------------------------------------------------------------------------------------

    public void setPropertiesManager( PropertiesManager propertiesManager ) {
        this.propertiesManager = propertiesManager;
    }

    public void setCapabilitiesManager( CapabilitiesManager capabilitiesManager ) {
        this.capabilitiesManager = capabilitiesManager;
    }

    public void setTestDescriptor( TestDescriptor testDescriptor ) {
        this.testDescriptor = testDescriptor;
    }

    public String getName() {
        return capabilityName;
    }

    public void setName( String name ) {
        capabilityName = name;
    }

    public CapabilityType getType() {
        return capabilityType;
    }

    public void setType( CapabilityType type ) {
        this.capabilityType = type;
    }
}
