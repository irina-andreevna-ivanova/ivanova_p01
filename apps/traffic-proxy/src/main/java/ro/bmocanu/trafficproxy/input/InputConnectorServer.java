/*- 
 * Copyright Bogdan Mocanu, 2010
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package ro.bmocanu.trafficproxy.input;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import ro.bmocanu.trafficproxy.Constants;
import ro.bmocanu.trafficproxy.ProxyKernel;
import ro.bmocanu.trafficproxy.base.Manageable;
import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * A server thread that listens to a specified port and spawns new workers (see
 * {@link InputConnectorWorker}) to serve each new connection that it receives.
 * 
 * @author mocanu
 * @see InputConnector
 * @see InputConnectorWorker
 * @see ManageableThread
 */
public class InputConnectorServer extends ManageableThread {
    private static final Logger LOG = Logger.getLogger( InputConnectorServer.class );

    /**
     * The connector object that describes this server.
     */
    private InputConnector connector;

    /**
     * The socket used for listening for incoming requests.
     */
    private ServerSocket serverSocket;

    // -------------------------------------------------------------------------------------------------

    /**
     * @param connector
     */
    public InputConnectorServer(InputConnector connector) {
        this.connector = connector;
    }

    /**
     * {@inheritDoc}
     * 
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
    public void dispose() throws Exception {
        if ( serverSocket != null && !serverSocket.isClosed() ) {
            serverSocket.close();
        }
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() {
        try {
            Socket clientSocket = serverSocket.accept();
            InputConnectorWorker newWorker = new InputConnectorWorker( connector, generateWorkerId(), clientSocket );
            ProxyKernel.getInstance().registerConnectorWorker( connector.getId(), newWorker.getWorkerId(), newWorker );
            startWorker( newWorker );

        } catch ( SocketTimeoutException exception ) {
            // this is ok, it means that we finished a session of listening
            // return the control to the thread core
        } catch ( IOException exception ) {
            LOG.error( exception );
        }
    }

    // -------------------------------------------------------------------------------------------------

    private int generateWorkerId() throws IOException {
        for ( int possibleId = 0; possibleId < Integer.MAX_VALUE; possibleId++ ) {
            boolean found = false;
            for ( Manageable worker : workers ) {
                if ( ((InputConnectorWorker) worker).getWorkerId() == possibleId ) {
                    found = true;
                    break;
                }
            }

            if ( !found ) {
                return possibleId;
            }
        }

        throw new IOException( "The max number of " + Integer.MAX_VALUE + " of workers for connector id="
                               + connector.getId() + " has been reached. Cannot generate more workers" );
    }

}
