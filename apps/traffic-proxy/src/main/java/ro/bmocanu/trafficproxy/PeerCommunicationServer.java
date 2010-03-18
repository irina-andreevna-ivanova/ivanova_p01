package ro.bmocanu.trafficproxy;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * The server connector that waits for incoming connection from another peer.
 * This is started for the peers that work in the &quot;server&quot; mode.
 */
public class PeerCommunicationServer extends Thread {
    private static final Logger LOG = Logger.getLogger(PeerCommunicationServer.class);

    private boolean keepRunning;

    public PeerCommunicationServer() {
        this.keepRunning = true;
    }

    @Override
    public void run() {
        LOG.info("Preparing peer communication server");
        try {
            ServerSocket server = new ServerSocket(Configuration.corePeerPort);
            Socket clientSocket = null;
            server.setSoTimeout(Constants.PEER_SOCKET_TIMEOUT);

            while (keepRunning) {
                try {
                    LOG.info("Waiting for incoming peer connections");
                    clientSocket = server.accept();
                } catch (SocketTimeoutException exception) {
                    // this is ok, it is expected
                }
            }

            if ( clientSocket == null ) {
                LOG.info( "No connection received. User abort" );
            }
        } catch (IOException exception) {
            LOG.error(exception);
        }

        LOG.info( "Peer server is shut down" );
    }

    public void dispose() {
        keepRunning = false;
    }

}
