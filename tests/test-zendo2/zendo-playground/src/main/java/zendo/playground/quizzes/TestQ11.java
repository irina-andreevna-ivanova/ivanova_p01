/**
 * 
 */
package zendo.playground.quizzes;

import java.util.Random;

/**
 * 
 *
 * @author mocanu
 */
public class TestQ11 {
    
    public static void main( String[] args ) {
        Random rnd = new Random();
        StringBuffer buffer = null;
        
        switch( rnd.nextInt( 2 ) ) {
            case 1 : buffer = new StringBuffer( 'A' );
            case 2 : buffer = new StringBuffer( 'B' );
            default: buffer = new StringBuffer( 'C' );
        }
        
        buffer.append( 'D' );
        buffer.append( 'E' );
        
        Systems.out.println( buffer.toString() );
    }

}
