/**
 * 
 */
package zendo.playground.threads;

/**
 * 
 *
 * @author mocanu
 */
public class TestThreads {
    
    public static void main( String[] args ) {
        for( int index = 0; index < 2; index++ ) {
            new Thread( new AThread( ( index + 1 ) * 5000 ) ).start();
        }
    }

}
