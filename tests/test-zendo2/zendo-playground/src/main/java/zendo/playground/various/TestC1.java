/**
 * 
 */
package zendo.playground.various;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;


/**
 * 
 *
 * @author mocanu
 */
public class TestC1 {

    public static void main( String[] args ) {
        OperatingSystemMXBean mxBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println("System architecture: " + mxBean.getArch());
        System.out.println("Number of processors: " + mxBean.getAvailableProcessors());
        System.out.println("OS Name: " + mxBean.getName());
    }
    
}
