/**
 * 
 */
package ro.bmocanu.trafficproxy.input;

import java.io.IOException;
import java.net.ServerSocket;

import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * 
 *
 * @author mocanu
 */
public class InputConnectorServer extends ManageableThread {
    
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() {
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
