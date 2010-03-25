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

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * Default implementation for {@link PacketSender}.
 * 
 * @author mocanu
 * @see PacketSender
 */
@Component
public class PacketSenderImpl extends ManageableThread implements PacketSender {
    private static final Logger LOG = Logger.getLogger( PacketSender.class );

    @Autowired
    private PeerChannel peerChannel;

    /**
     * The queue used for storing the packets before being sent.
     */
    private LinkedBlockingQueue<Packet> packetQueue = new LinkedBlockingQueue<Packet>();

    // -------------------------------------------------------------------------------------------------

    public void send( Packet packet ) throws IOException {
        try {
            LOG.debug( "Received one packet: conId=" + packet.getConnectorId() + ", command="
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
        DataOutputStream targetOutputStream = peerChannel.getOutputStream();

        Packet packet = packetQueue.poll();
        if ( packet != null ) {
            try {
                targetOutputStream.writeInt( packet.getConnectorId() );
                targetOutputStream.writeInt( packet.getWorkerId() );
                targetOutputStream.writeInt( packet.getCommand().getCode() );
                targetOutputStream.writeInt( packet.getContentLength() );
                targetOutputStream.write( packet.getContent() );
                targetOutputStream.flush();
            } catch ( IOException exception ) {
                LOG.error( "Missed one packet; command=" + packet.getCommand().name() + ", connectorId="
                           + packet.getConnectorId(), exception );
            }
        }
    }

}
