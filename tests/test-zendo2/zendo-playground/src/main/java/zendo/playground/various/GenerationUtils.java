/**
 * 
 */
package zendo.playground.various;

import java.util.UUID;

/**
 * 
 *
 * @author mocanu
 */
public class GenerationUtils {
    
    public static void generateUUID() {
        System.out.println( UUID.randomUUID().toString() );
    }
    
    public static boolean test1() {
        System.out.println( "test1" );
        return true;
    }
    
    public static boolean test2() {
        System.out.println( "test2" );
        return true;
    }
    
    public static void main( String[] args ) {
        System.out.println( "catcat".indexOf( "" ) );
    }

}
