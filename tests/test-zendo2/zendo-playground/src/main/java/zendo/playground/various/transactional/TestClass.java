package zendo.playground.various.transactional;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bogdan.mocanu
 */
public class TestClass {

    public static void main( String[] args ) {
        AtomicInteger account1 = new AtomicInteger( 25000 );
        AtomicInteger account2 = new AtomicInteger( 0 );

        Transactional trans = new Transactional();
        trans.put( "account1", account1 );
        trans.put( "account2", account2 );

        System.out.println( "Before all: " + account1 + " | " + account2 );

        trans.execute( new Transactional.Execution() {
            protected void execute() throws Exception {
                AtomicInteger account1 = get( "account1" );
                AtomicInteger account2 = get( "account2" );

                System.out.println( "Before op: " + account1 + " | " + account2 );
                account1.set( account1.get() - 5000 );
                account2.set( account2.get() + 5000 );

                System.out.println( "After op: " + account1 + " | " + account2 );

                //throw new NullPointerException();
                rollback();
            }
        });

        account1 = trans.get( "account1" );
        account2 = trans.get( "account2" );

        System.out.println( "Finish: " + account1 + " | " + account2 );
    }

}
