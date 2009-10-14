/**
 * 
 */
package zendo.playground.mbeans;

import java.lang.management.ManagementFactory;

import ro.bmocanu.zendo.AbstractZendoTest;
import ro.bmocanu.zendo.ZendoCore;

public class TestMBeans1 extends AbstractZendoTest {

    @Override
    public void test() {
        //MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        ManagementFactory.getPlatformMBeanServer();
        try {
            while( true ) {
                Thread.sleep( 1000 );
            }
        } catch ( InterruptedException exception ) {
            exception.printStackTrace();
        }
    }
    
    public static void main( String[] args ) {
        ZendoCore.testThis();
    }

}
