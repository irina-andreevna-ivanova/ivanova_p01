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
package ro.bmocanu.trafficproxy.peers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.bmocanu.trafficproxy.Configuration;
import ro.bmocanu.trafficproxy.Constants;
import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * The server connector that waits for incoming connection from another peer. This is started for
 * the peers that work in the &quot;server&quot; mode.
 */
@Component
public class PeerCommunicationServer extends ManageableThread {
    private static final Logger LOG = Logger.getLogger( PeerCommunicationServer.class );

    @Autowired
    private PeerChannel peerChannel;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws Exception {
        LOG.info( "Preparing peer communication server" );
        serverSocket = new ServerSocket( Configuration.corePeerPort );
        serverSocket.setSoTimeout( Constants.PEER_SOCKET_TIMEOUT );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() throws Exception {
        LOG.info( "Peer communication is shutting down" );
        if ( clientSocket != null && !clientSocket.isClosed() ) {
            clientSocket.close();
        }
        if ( serverSocket != null && !serverSocket.isClosed() ) {
            serverSocket.close();
        }
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() throws Exception {
        if ( !clientSocket.isClosed() && peerChannel.isConnectionUp() ) {
            // nothing to do if the connection is still on
            return;
        }

        try {
            LOG.info( "Waiting for incoming peer connections" );
            clientSocket = serverSocket.accept();

            LOG.info( "Peer connection established" );
            peerChannel.setInputStream( new DataInputStream( new BufferedInputStream( clientSocket.getInputStream() ) ) );
            peerChannel.setOutputStream( new DataOutputStream( new BufferedOutputStream( clientSocket.getOutputStream() ) ) );
            peerChannel.setConnectionUp( true );

        } catch ( SocketTimeoutException exception ) {
            // this is ok, it is expected
        }
    }

}
