/**
 * 
 */
package ro.bmocanu.test.delayproxy;

/**
 * 
 *
 * @author mocanu
 */
public class Launcher {
    
    public static void main( String[] args ) {
        System.out.println( "Starting delay proxy" );
        final Server server = new Server();
        server.start();
        
        Runtime.getRuntime().addShutdownHook( new Thread() {
            @Override
            public void run() {
                System.out.println( "Shutting down the proxy" );
                server.managedStop();
                
                System.out.println( "Done!" );
            }
        });
    }

}
