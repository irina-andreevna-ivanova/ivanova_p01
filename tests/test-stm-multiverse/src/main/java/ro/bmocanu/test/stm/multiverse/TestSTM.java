package ro.bmocanu.test.stm.multiverse;

import org.multiverse.annotations.TransactionalMethod;

/**
 * @author bogdan.mocanu
 */
public class TestSTM {

    public static void main( String[] args ) {
        Account acc1 = new Account( 1000 );
        Account acc2 = new Account( 0 );

        System.out.println( "Before: " + acc1 + ", " + acc2 );

        try {
            transfer( acc1, acc2, 300, false );
        } catch ( Exception exc ) {
            // ignore here
            System.out.println( "Exception encountered: " + exc.getClass().getSimpleName() );
        }

        System.out.println( "After: " + acc1 + ", " + acc2 );
    }

    @TransactionalMethod
    public static void transfer( Account fromAcc, Account toAcc, int amount, boolean fail ) {
        fromAcc.changeBalance( -amount );
        if ( fail ) {
            throw new NullPointerException( "Test exception" );
        }
        toAcc.changeBalance( amount );
    }

}
