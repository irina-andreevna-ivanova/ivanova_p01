/**
 * 
 */
package zendo.playground.various;

import java.util.Random;

/**
 * 
 *
 * @author mocanu
 */
public class TestArrays {

    public static void main( String[] args ) {
        Random random = new Random( 3 );
        for( int index = 0; index < 20; index++ ) {
            System.out.println( random.nextInt() );
        }
        
    }
    
}
