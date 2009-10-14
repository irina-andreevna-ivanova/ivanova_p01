/**
 *
 */
package zendo.playground.quizzes;


/**
 *
 *
 * @author mocanu
 */
public class TestQ16 implements Runnable {

    public void run() {
        try {
            Thread.sleep( 1000 );
            Thread.currentThread().setDaemon( true );
        } catch ( InterruptedException exception ) {
        } finally {
            System.out.println( "With the last breath..." );
        }
    }

    public static void main( String[] args ) {
        new Thread( new TestQ16() ).start();
    }

}
