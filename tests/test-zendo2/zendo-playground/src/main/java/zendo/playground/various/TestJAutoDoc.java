/**
 * 
 */
package zendo.playground.various;

import java.util.Arrays;

/**
 * The Class TestJAutoDoc.
 * 
 * @author mocanu
 */
public class TestJAutoDoc {
    
    public static void someMeth( int... args ) {
        System.out.println( Arrays.toString( args ) );
    }
    
    public static void main( String[] args ) {
        someMeth( 23, 24, new Integer( 23 ) );
    }

}
