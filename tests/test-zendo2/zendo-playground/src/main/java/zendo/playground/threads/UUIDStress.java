/**
 *
 */
package zendo.playground.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 *
 * @author mocanu
 */
public class UUIDStress {

    private static final int TEST_NR_GENS = 20000;
    private static final int TEST_THREADS = 500;

    public static void main( String[] args ) {
        long startTime = System.currentTimeMillis();

        UUIDStressThread[] threads = new UUIDStressThread[TEST_THREADS];
        for ( int index = 0; index < TEST_THREADS; index++ ) {
            System.out.println( "Starting thread nr " + ( index + 1 ) );
            threads[index] = new UUIDStressThread();
            threads[index].start();
        }

        for ( int index = 0; index < TEST_THREADS; index++ ) {
            try {
                threads[index].join();
            } catch ( InterruptedException exception ) {
                exception.printStackTrace();
            }
        }

        System.out.println( "Threads phase finished. Moving on to processing the results" );

        Map<String, String> union = new HashMap<String, String>();
        for ( int index = 0; index < TEST_THREADS; index++ ) {
            System.out.println( "Processing results from thread: " + ( index + 1 ) );

            List<String> currentList = threads[index].getUuids();
            for( String id : currentList ) {
                if ( union.containsKey( id ) ) {
                    System.out.println( "Found duplicated UUID: " + id );
                } else {
                    union.put( id, id );
                }
            }

            currentList.clear();
            threads[ index ].setUuids( null );
            threads[ index ] = null;
        }

        System.out.println( "DONE" );
        long endTime = System.currentTimeMillis();
        System.out.println( "Execution time: " + ( endTime - startTime ) + " ms" );
    }

    static class UUIDStressThread extends Thread {
        private List<String> uuids = new ArrayList<String>();

        @Override
        public void run() {
            for ( int index = 0; index < TEST_NR_GENS; index++ ) {
                uuids.add( UUID.randomUUID().toString() );
                Thread.yield();
            }
        }
        public List<String> getUuids() {
            return uuids;
        }
        public void setUuids( List<String> uuids ) {
            this.uuids = uuids;
        }
    }

}
