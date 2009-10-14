/**
 * 
 */
package zendo.playground.threads;

/**
 * 
 *
 * @author mocanu
 */
public class AThread implements Runnable {
    
    private int startSleep;

    /**
     * @param startSleep
     */
    public AThread(int startSleep) {
        super();
        this.startSleep = startSleep;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            Thread.sleep( startSleep );
            while( true ) {
            }
            
        } catch ( InterruptedException exception ) {
            exception.printStackTrace();
        }
    }

}
