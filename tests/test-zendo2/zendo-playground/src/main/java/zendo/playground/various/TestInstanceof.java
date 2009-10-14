/**
 *
 */
package zendo.playground.various;

/**
 *
 *
 * @author mocanu
 */
public class TestInstanceof {
    public static void main( String[] args ) {
        if ( ((String)null) instanceof String ) {
            System.out.println( "YES" );
        } else {
            System.out.println( "NO" );
        }

    }
}
