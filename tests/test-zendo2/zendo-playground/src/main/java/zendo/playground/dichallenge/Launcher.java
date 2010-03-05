/**
 * 
 */
package zendo.playground.dichallenge;


/**
 * 
 *
 * @author mocanu
 */
public class Launcher {
    
    public static void main( String[] args ) {
        OrderProcessor op = DIContext.getContext().getBean( "orderProcessor" );
        op.createOrder( "o1", 16005 );
    }

}

class DIContext {
    public static DIContext getContext() { return null; }
    public <T> T getBean( String name ) { return null; };
}
