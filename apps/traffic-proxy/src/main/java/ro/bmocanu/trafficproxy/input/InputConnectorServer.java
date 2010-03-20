/**
 * 
 */
package ro.bmocanu.trafficproxy.input;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import ro.bmocanu.trafficproxy.Constants;
import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * 
 *
 * @author mocanu
 */
public class InputConnectorServer extends ManageableThread {
    private static final Logger LOG = Logger.getLogger( InputConnectorServer.class );
    
    private InputConnector connector;
    private ServerSocket serverSocket;

    /**
     * @param connector
     */
    public InputConnectorServer(InputConnector connector) {
        super();
        this.connector = connector;
    }

    /**
     * {@inheritDoc}
     * @throws IOException 
     */
    @Override
    public void init() throws IOException {
       serverSocket = new ServerSocket( connector.getPort() );
       serverSocket.setSoTimeout( Constants.INPUT_CONNECTOR_SOCKET_TIMEOUT );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() {
        try {
            Socket clientSocket = serverSocket.accept();
            InputConnectorWorker newWorker = new InputConnectorWorker( clientSocket );
            startWorker( newWorker );
            
        } catch ( SocketTimeoutException exception ) {
            // this is ok, it is expected
        } catch ( IOException exception ) {
            LOG.error( exception );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() throws Exception {
        if ( serverSocket != null && !serverSocket.isClosed() ) {
            serverSocket.close();
        }
    }

}
