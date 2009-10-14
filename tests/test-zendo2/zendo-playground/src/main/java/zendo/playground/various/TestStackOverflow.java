/**
 * 
 */
package zendo.playground.various;

/**
 * @author mocanu
 */
public class TestStackOverflow {

    public static void method( int nr ) {
        System.out.println( "Reached " + nr );
        method( nr + 1 );
    }

    public static void main( String[] args ) {
        try {
            method( 1 );
        } catch ( Throwable e ) {
            System.out.println( e.getMessage() );
        }
    }

}
