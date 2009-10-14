/**
 * 
 */
package zendo.playground.various;

import java.io.IOException;

/**
 * 
 *
 * @author mocanu
 */
public class Snippet {
    
    public static void doSomeExc() {
        throw new IllegalArgumentException( "IAE problem" );
    }
    
    public static void doSomeIOExc() throws IOException {
        throw new IOException( "IO problem" );
    }
    
    public static void test() throws IOException {
        try {
            System.out.println( "Inside try" );
            doSomeExc();
            doSomeIOExc();
        } finally {
            System.out.println( "Inside finally" );
        }
    }
    
    /**
     * @throws IOException 
     * 
     */
    public static void main( String[] args ) throws IOException {
        test();
    }
    
}

