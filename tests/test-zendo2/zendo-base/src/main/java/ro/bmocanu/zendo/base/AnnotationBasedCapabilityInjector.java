package ro.bmocanu.zendo.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ro.bmocanu.zendo.ZendoTest;
import ro.bmocanu.zendo.annotations.Capability;
import ro.bmocanu.zendo.capabilities.CapabilitiesManager;
import ro.bmocanu.zendo.capabilities.CapabilityInternalInterface;
import ro.bmocanu.zendo.capabilities.CapabilityType;

/**
 * This class implements the code required for injecting core capability objects (such as the SQL Connection
 * for a JDBCCapability type, the ApplicationContext for a SpringCapability type, and so on) inside the tests.
 *
 * @author bogdan.mocanu
 */
public class AnnotationBasedCapabilityInjector {
    private static Log log = LogFactory.getLog( AnnotationBasedCapabilityInjector.class );

    private CapabilitiesManager capabilitiesManager;

    // ------------------------------------------------------------------------------------------------------

    @SuppressWarnings( "unchecked" ) 
    public void injectCapabilities( ZendoTest test ) {
        Class testClass =  test.getClass();
        Field[] testFields = testClass.getDeclaredFields();

        for( Field testField : testFields ) {
            if ( testField.isAnnotationPresent( Capability.class ) ) {
                log.info( "Found field " + testField.getName() + " annotated. Injecting core object" );
                if ( !testField.isAccessible() ) {
                    testField.setAccessible( true );
                }
                Annotation testAnnotation = testField.getAnnotation( Capability.class );
                try {
                    Object invocationResponse = Capability.class.getMethod( "value" ).invoke( testAnnotation );

                    // the invocation response is actually the capability type given to the field annotation
                    CapabilityType capType = (CapabilityType) invocationResponse;
                    CapabilityInternalInterface capabilityObject = (CapabilityInternalInterface)capabilitiesManager.getCapability( capType );
                    Object capabilityCoreObject = capabilityObject.getCapabilityObject();

                    testField.set( test, capabilityCoreObject );

                } catch ( NoSuchMethodException e ) {
                    // this should never happen, since the Capability class must have the value() method
                    assert false;
                } catch ( IllegalAccessException e ) {
                    log.error( e );
                } catch ( InvocationTargetException e ) {
                    log.error( e );
                }
            }
        }
                
    }

    // ------------------------------------------------------------------------------------------------------

    public void setCapabilitiesManager( CapabilitiesManager capabilitiesManager ) {
        this.capabilitiesManager = capabilitiesManager;
    }
}
