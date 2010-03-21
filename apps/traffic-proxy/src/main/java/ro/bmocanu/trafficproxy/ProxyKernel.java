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

import java.util.HashMap;
import java.util.Map;

import ro.bmocanu.trafficproxy.base.ConnectorWorker;
import ro.bmocanu.trafficproxy.base.ManageableComposite;
import ro.bmocanu.trafficproxy.input.PacketSender;

/**
 * Central class of the <code>traffic-proxy</code>. It is responsible for assembling the connectors
 * and packet senders, as well as to make sure that the proper combinations are used so that each
 * communication between two connectors (INPUT and OUTPUT) is performed in full duplex mode.
 * <p>
 * This class works as a singleton, and is therefore accessible to any other class of the project.
 * 
 * @author mocanu
 */
public final class ProxyKernel extends ManageableComposite {

    /**
     * The one and only instance of this class.
     */
    private static final ProxyKernel SINGLE_INSTANCE = new ProxyKernel();

    public static ProxyKernel getInstance() {
        return SINGLE_INSTANCE;
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * The map that keeps all the connector output streams under the keys composed of the connector
     * and the worker IDs. This map is used when an outgoing packet is received from the other
     * party, and needs to be dispatched to the correct output stream.
     */
    private Map<String, ConnectorWorker> workersMap = new HashMap<String, ConnectorWorker>();

    /**
     * The object responsible with sending the packets to the other party.
     */
    private PacketSender packetSender;

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void managedStart() {
        super.managedStart();

        packetSender = new PacketSender( null );
        addManageable( packetSender );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void managedStop() {
        super.managedStop();
    }

    public void registerConnectorWorker( int connectorId, int workerId, ConnectorWorker worker ) {
        workersMap.put( buildUnifiedID( connectorId, workerId ), worker );
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
    public PacketSender getPacketSender() {
        return packetSender;
    }

}
