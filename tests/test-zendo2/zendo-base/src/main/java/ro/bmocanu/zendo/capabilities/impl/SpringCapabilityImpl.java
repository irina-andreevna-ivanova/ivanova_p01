package ro.bmocanu.zendo.capabilities.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ro.bmocanu.zendo.capabilities.AbstractCapability;
import ro.bmocanu.zendo.capabilities.intf.SpringCapability;

/**
 * Default implementation for {@link ro.bmocanu.zendo.capabilities.intf.SpringCapability}.
 *
 * @author bogdan.mocanu
 */
public class SpringCapabilityImpl extends AbstractCapability implements SpringCapability {
    private static Log log = LogFactory.getLog( SpringCapability.class.getName() );

    private ApplicationContext applicationContext;

    public void init() {
        // for this version, we simply load the file that has the same name as the class, suffixed with .XML
        String className = testDescriptor.getClassName();
        String appContextFileName = "/" + className.replace( '.', '/' ) + ".xml";

        log.info( "Using " + appContextFileName + " to configure Spring" );
        applicationContext = new ClassPathXmlApplicationContext( new String[]{appContextFileName} );
    }

    public void destroy() {
        ((AbstractApplicationContext) applicationContext).registerShutdownHook();
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Object getCapabilityObject() {
        return getApplicationContext();
    }
}
