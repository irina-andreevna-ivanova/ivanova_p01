/*- 
 * Copyright Bogdan Mocanu, 2009
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

package ro.bmocanu.trafficproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.bmocanu.trafficproxy.base.ConnectorWorker;
import ro.bmocanu.trafficproxy.base.ManageableComposite;
import ro.bmocanu.trafficproxy.output.PacketDispatcher;
import ro.bmocanu.trafficproxy.peers.PacketReceiver;
import ro.bmocanu.trafficproxy.peers.PacketSender;
import ro.bmocanu.trafficproxy.peers.PeerChannel;
import ro.bmocanu.trafficproxy.peers.PeerCommunicationServer;

/**
 * Central class of the <code>traffic-proxy</code>. It is responsible for assembling the connectors
 * and packet senders, as well as to make sure that the proper combinations are used so that each
 * communication between two connectors (INPUT and OUTPUT) is performed in full duplex mode.
 * 
 * @author mocanu
 */
@Component
public class ProxyKernel extends ManageableComposite implements ServiceProvider {

    @Autowired
    private PeerChannel peerChannel;

    @Autowired
    private PacketReceiver packetReceiver;

    @Autowired
    private PacketSender packetSender;

    @Autowired
    private PacketDispatcher packetDispatcher;

    @Autowired
    private PeerCommunicationServer peerServer;

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void managedStart() {
        super.managedStart();

        peerChannel.setConnectionUp( false );

        addManageable( packetReceiver );
        packetReceiver.managedStart();

        addManageable( packetSender );
        packetSender.managedStart();

        addManageable( packetDispatcher );
        packetDispatcher.managedStart();

        // here server or client
        addManageable( peerServer );
        peerServer.managedStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void managedStop() {
        super.managedStop();
    }

    public void registerConnectorWorker( int connectorId, int workerId, ConnectorWorker worker ) {
        //workersMap.put( buildUnifiedID( connectorId, workerId ), worker );
    }

    // -------------------------------------------------------------------------------------------------

    private String buildUnifiedID( int connectorId, int workerId ) {
        return connectorId + "-|-" + workerId;
    }

    /**
     * Returns the packetSender
     * 
     * @return the packetSender
     */
    @Override
    public PacketSender getPacketSender() {
        return packetSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PacketDispatcher getPacketDispatcher() {
        return packetDispatcher;
    }

}
