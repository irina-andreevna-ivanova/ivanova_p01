/**
 * 
 */
package zendo.playground.junit48;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * 
 *
 * @author mocanu
 */
@RunWith( Theories.class )
public class TestTheories {
    
    @DataPoint
    public static int POINT1 = 10;
    @DataPoint
    public static int POINT2 = 20;
    @DataPoint
    public static int POINT3 = 30;
    @DataPoint
    public static int POINT4 = 40;
    
    @Theory
    public void testSomething( int value, int value2 ) {
        System.out.println( "value1=" + value + ", value2=" + value2 );
    }

}
