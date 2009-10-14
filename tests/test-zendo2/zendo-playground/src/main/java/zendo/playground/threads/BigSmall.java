/**
 *
 */
package zendo.playground.threads;

import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author mocanu
 */
public class BigSmall {
    public static void main( String[] args ) {
        SmallThread st = new SmallThread( Thread.currentThread() );
        st.start();

        System.out.println( "Big thread: doing big stuff" );
        for( int index = 0; index < 10; index++ ) {
            try {
                TimeUnit.SECONDS.sleep( 1 );
            } catch ( InterruptedException exception ) {
                exception.printStackTrace();
            }
            System.out.println( "Big thread: Still waiting" );
        }

        System.out.println( "Big thread: Done" );
    }

    static class SmallThread extends Thread {

        private Thread parent;

        /**
         * @param parent
         */
        public SmallThread(Thread parent) {
            super();
            this.parent = parent;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            System.out.println( "Inside small thread" );
            System.out.println( "Waiting for big thread to finish" );

            try {
                parent.join();
            } catch ( InterruptedException exception ) {
                exception.printStackTrace();
            }

            System.out.println( "Done in small thread" );
        }

    }
}
