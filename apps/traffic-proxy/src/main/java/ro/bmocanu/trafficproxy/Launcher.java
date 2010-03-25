package ro.bmocanu.trafficproxy;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ro.bmocanu.trafficproxy.peers.PeerCommunicationServer;

/**
 * Main class of the traffic-proxy.
 */
public class Launcher extends Thread {
    private static final Logger LOG = Logger.getLogger( Launcher.class );

    private PeerCommunicationServer commServer;  

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        // log4j configuration
        PropertyConfigurator.configure( Constants.LOG4J_FILE );
        LOG.info( "Starting traffic-proxy, ver. 1.0" );

        // application configuration
        Configuration.loadConfiguration();

        // intercept the JVM shutdown
        Runtime.getRuntime().addShutdownHook( launcher );

        // carry on with the application start up
        launcher.startApplication();
    }

    private void startApplication() {
        if ( Configuration.MODE_SERVER.equals( Configuration.corePeerMode ) ) {
            LOG.info( "Peer works as SERVER" );
            commServer = new PeerCommunicationServer();
            commServer.start();
        }
    }

    /**
     * Called by the shutdown hook
     */
    @Override
    public void run() {
        LOG.info( "Preparing application shutdown" );
        if ( commServer != null && commServer.isAlive() ) {
            commServer.dispose();
            try {
                commServer.join();
            } catch (InterruptedException e) {
                LOG.error( e );
            }
        }

        LOG.info( "Application is now closed" );
    }
}
