/**
 * 
 */
package zendo.playground.mbeans;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class TestMBeans2 {

    public static void main( String[] args ) throws MalformedObjectNameException, NullPointerException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        try {
            ServerConfigurationMBean configBean = new ServerConfiguration();
            ObjectName configBeanName = new ObjectName( "test.mbeans:type=ServerConfiguration" );

            MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
            mbeanServer.registerMBean( configBean, configBeanName );

            while ( true ) {
                Thread.sleep( 1000 );
                System.out.println( "CacheSize: " + configBean.getCacheSize() );
            }

        } catch ( Exception exception ) {
            exception.printStackTrace();
        }
    }

}
