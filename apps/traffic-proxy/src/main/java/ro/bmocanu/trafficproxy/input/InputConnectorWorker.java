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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import ro.bmocanu.trafficproxy.Packet;
import ro.bmocanu.trafficproxy.ProxyKernel;
import ro.bmocanu.trafficproxy.StreamPackagingUtils;
import ro.bmocanu.trafficproxy.base.ConnectorWorker;
import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * The object that handles the work of an input connector. It receives the socket on which the
 * client has been placed, and handles the in-out communication with the client.
 * 
 * @author mocanu
 */
public class InputConnectorWorker extends ManageableThread implements ConnectorWorker {
    private static final Logger LOG = Logger.getLogger( InputConnectorWorker.class );

    /**
     * The connector information, on whose behalf this worker is doing the job.
     */
    private InputConnector connector;

    /**
     * The ID of this worker. The identification number is unique among the workers of an input
     * connector.
     */
    private int workerId;

    /**
     * The socket used for discussing with the client.
     */
    private Socket clientSocket;

    private InputStream clientInputStream;
    private OutputStream clientOutputStream;

    // -------------------------------------------------------------------------------------------------

    /**
     * @param clientSocket
     */
    public InputConnectorWorker(InputConnector connector, int workerId, Socket clientSocket) {
        super();
        this.connector = connector;
        this.workerId = workerId;
        this.clientSocket = clientSocket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() throws Exception {
        clientSocket.close();
        super.dispose();
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() throws Exception {
        Packet packet = StreamPackagingUtils.packageFromRawSource( connector.getId(), workerId, clientInputStream );
        if ( packet == null ) {
            // no data to send yet
            return;
        }

        ProxyKernel.getInstance().getPacketSender().send( packet );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processPacket( Packet packet ) {
        if ( packet.getConnectorId() != connector.getId() || packet.getWorkerId() != workerId ) {
            LOG.error( "Misplaced packet. Packet with connectorId=" + packet.getConnectorId() + ", workerId="
                       + packet.getWorkerId() + " was dispatched to connectorId=" + connector.getId() + ", workerId="
                       + workerId );
            return;
        }
        
        try {
            clientOutputStream.write( packet.getContent(), 0, packet.getContentLength() );
        } catch ( IOException exception ) {
            LOG.error( "Failed to send packet to the client stream", exception );
        }
    }
    
    // -------------------------------------------------------------------------------------------------

    /**
     * Returns the workerId
     *
     * @return the workerId
     */
    public int getWorkerId() {
        return workerId;
    }

}
