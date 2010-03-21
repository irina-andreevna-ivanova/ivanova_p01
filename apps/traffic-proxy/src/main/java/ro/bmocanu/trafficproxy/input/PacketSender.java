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

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import ro.bmocanu.trafficproxy.Packet;
import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * The thread responsible with accumulating the packets to send, and sending them one by one,
 * through the peer channel, to the other party. The class provides an asynchronous mechanism for
 * receiving the packets and sending them one by one.
 * 
 * @author mocanu
 */
public class PacketSender extends ManageableThread {
    private static final Logger LOG = Logger.getLogger( PacketSender.class );

    /**
     * The queue used for storing the packets before being sent.
     */
    private LinkedBlockingQueue<Packet> packetQueue;

    /**
     * The stream on which the packets should be sent.
     */
    private DataOutputStream targetOutputStream;

    // -------------------------------------------------------------------------------------------------

    /**
     * @param packetQueue
     * @param targetOutputStream
     */
    public PacketSender(OutputStream targetOutputStream) {
        this.packetQueue = new LinkedBlockingQueue<Packet>();
        this.targetOutputStream = new DataOutputStream( targetOutputStream );
    }

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
