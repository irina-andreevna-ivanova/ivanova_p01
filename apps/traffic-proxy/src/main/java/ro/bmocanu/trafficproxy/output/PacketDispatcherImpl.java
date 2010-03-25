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

package ro.bmocanu.trafficproxy.output;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import ro.bmocanu.trafficproxy.base.ManageableThread;
import ro.bmocanu.trafficproxy.peers.Packet;

/**
 * The thread responsible with reading data from the peer channel stream, converting it to packets
 * and distributing the packets to the corresponding connectors.
 * 
 * @author mocanu
 */
@Component
public class PacketDispatcherImpl extends ManageableThread implements PacketDispatcher {
    private static final Logger LOG = Logger.getLogger( PacketDispatcher.class );

    private List<OutputConnectorWrapper> outputConnectorWrappers;

    /**
     * The queue where all the packets scheduled for dispatching are gathered. The internal thread
     * will check from time to time this queue, and will dispatch any thread found inside.
     */
    private LinkedBlockingQueue<Packet> packetQueue = new LinkedBlockingQueue<Packet>();

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispatch( Packet packet ) throws IOException {
        try {
            LOG.debug( "Received one packet to dispatch: conId=" + packet.getConnectorId() + ", command="
                       + packet.getCommand().name() );
            packetQueue.put( packet );
        } catch ( InterruptedException exception ) {
            throw new IOException( exception );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() {
    }

}
