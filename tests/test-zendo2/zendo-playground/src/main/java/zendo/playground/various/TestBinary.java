/**
 * 
 */
package zendo.playground.various;

import java.util.Arrays;

/**
 * 
 *
 * @author mocanu
 */
public class TestBinary {
    
    public static int[] getBinary( int nr ) {
        int[] result = new int[ 32 ];
        Arrays.fill( result, 0 );
        
        for( int index = 0; index < 32 && nr > 0; index++ ) {
            result[ index ] = nr % 2;
            nr = nr / 2;
        }
        
        return result;
    }
    
    public static void main( String[] args ) {
        int nr = 100;
        System.out.println( nr + " = " + Arrays.toString( getBinary( nr ) ) );
        
        nr = nr << 3;
        System.out.println( nr + " = " + Arrays.toString( getBinary( nr ) ) );
        
        System.out.println( Math.ceil( 5 / 4D ) );
        
    }

}
