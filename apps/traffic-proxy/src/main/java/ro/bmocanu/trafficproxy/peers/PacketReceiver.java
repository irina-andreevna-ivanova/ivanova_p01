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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.bmocanu.trafficproxy.base.ManageableThread;
import ro.bmocanu.trafficproxy.output.PacketDispatcher;
import ro.bmocanu.trafficproxy.utils.StreamPackagingUtils;

/**
 * The thread responsible for reading bytes from the peer channel stream, converting them in
 * packets, and sending them to the {@link PacketDispatcher} for being dispatched to the
 * corresponding clients.
 * 
 * @author mocanu
 */
@Component
public class PacketReceiver extends ManageableThread {
    private static final Logger LOG = Logger.getLogger( PacketReceiver.class );

    @Autowired
    private PeerChannel channel;

    @Autowired
    private PacketDispatcher packetDispatcher;

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() throws Exception {
        if ( !channel.isConnectionUp() ) {
            // no connection available
            return;
        }

        Packet packet = StreamPackagingUtils.packageFromPeerSource( channel.getInputStream() );
        LOG.debug( "Packet dispatcher: conId=" + packet.getConnectorId() + ", workerId=" + packet.getWorkerId()
                   + ", command=" + packet.getCommand() + ", size=" + packet.getContentLength() );

        packetDispatcher.dispatch( packet );
    }

}
