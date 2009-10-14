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

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ro.bmocanu.zendo.base.AnnotationBasedCapabilityInjector;
import ro.bmocanu.zendo.base.PackageHierarchyPackageLoader;
import ro.bmocanu.zendo.base.PropertiesLoader;
import ro.bmocanu.zendo.base.PropertiesManager;
import ro.bmocanu.zendo.base.TestDescriptor;
import ro.bmocanu.zendo.base.TestDescriptorFactory;
import ro.bmocanu.zendo.capabilities.CapabilitiesManager;

/**
 * The central class of the Zendo framework.
 * 
 * @author Bogdan Mocanu
 */
public class ZendoCore {
    private static Log log = LogFactory.getLog( ZendoCore.class );

    static {
        log.info( "Welcome to " + ZendoConstants.APPLICATION_NAME + ", ver. " + ZendoConstants.APPLICATION_VERSION );
    }

    @SuppressWarnings("unchecked")
    public static void testThis() {
        log.info( "Discovering test class" );
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String testClassName = discoverTestClassInStackTrace( stackTrace );

        log.info( "Identified test class: " + testClassName );

        try {
            Class<ZendoTest> testClass = (Class<ZendoTest>) Class.forName( testClassName );
            ZendoTest test = (ZendoTest) testClass.newInstance();
            testThis( test );
        } catch ( InstantiationException e ) {
            log.error( "Cannot create new instance of test class" + testClassName, e );
        } catch ( IllegalAccessException e ) {
            log.error( "Cannot create new instance of test class" + testClassName, e );
        } catch ( ClassNotFoundException e ) {
            log.error( "Cannot create new instance of test class" + testClassName, e );
        }
    }

    public static void testThis( ZendoTest test ) {
        // 1. create the test descriptor
        TestDescriptorFactory descriptorFactory = new TestDescriptorFactory();
        TestDescriptor descriptor = descriptorFactory.createDescriptor( test );

        // 2. dinamically and hierarchically load the test properties
        Properties testProperties = new Properties();
        PropertiesLoader propertiesLoader = new PackageHierarchyPackageLoader();
        propertiesLoader.loadProperties( testProperties, descriptor );

        PropertiesManager propertiesManager = new PropertiesManager( testProperties );

        // 3. create the capabilities manager
        CapabilitiesManager capabilitiesManager = new CapabilitiesManager();
        capabilitiesManager.setPropertiesManager( propertiesManager );
        capabilitiesManager.setTestDescriptor( descriptor );
        capabilitiesManager.init();

        test.setPropertiesManager( propertiesManager );
        test.setCapabilitiesManager( capabilitiesManager );

        // 4. check for any fields annotated with the Capability annotation and inject the core objects
        AnnotationBasedCapabilityInjector injector = new AnnotationBasedCapabilityInjector();
        injector.setCapabilitiesManager( capabilitiesManager );
        injector.injectCapabilities( test );

        // 5. finally start the test
        log.info( "Starting the test" );
        long startTime = System.currentTimeMillis();

        test.test();

        log.info( "Test finished" );
        long endTime = System.currentTimeMillis();

        log.info( "Test duration: " + ( endTime - startTime ) + " milliseconds" );

        // 6. destroy the capabilities manager
        log.info( "Cleaning the data" );
        capabilitiesManager.destroy();

        log.info( "Test closed. Thank you for using Zendo Testing Framework" );
    }

    //----------------------------------------------------------------------------------------------

    private static String discoverTestClassInStackTrace( StackTraceElement[] stackTrace ) {
        // the 4th element in this stack trace should be the testing class
        // (0=dumpThreads, 1=getStackTrace, 2=testThis())
        // for most cases it is better to assume that the last element in the stack trace is our
        // class
        // StackTraceElement testStackElement = stackTrace[stackTrace.length - 1];
        // String testClassName = testStackElement.getClassName();
        for ( StackTraceElement element : stackTrace ) {
            String className = element.getClassName();
            if ( className.startsWith( ZendoConstants.ZENDO_TEST_PACKAGE_PREFIX ) ) {
                return className;
            }
        }

        throw new InvalidTestException(
                "Cannot determine the test class for the given test. Please use the ZendoCore.testThis(test) method." );
    }

}
