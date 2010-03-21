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
package ro.bmocanu.trafficproxy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Converter methods from the bytes provided by an input stream and the traffic proxy internal data
 * unit - the packet - an the other way around. The class can either read data from a proxy-enabled
 * stream (that also sends the IDs and the peer commands (for more info see {@link Packet}).
 * 
 * @author mocanu
 */
public final class StreamPackagingUtils {

    /**
     * Creates a package with the given IDs, and with the content filled with data from the given
     * stream.
     * 
     * @param connectorId
     *            the ID of the connector on whose behalf the packet is being created
     * @param workerId
     *            the ID of the worker that requires the packet
     * @param source
     *            the input stream from where data should be read
     * @throws IOException
     *             if an exception is encountered during the packet creation
     */
    public static Packet packageFromRawSource( int connectorId, int workerId, InputStream source ) throws IOException {
        Packet packet = new Packet();
        packet.setConnectorId( connectorId );
        packet.setWorkerId( workerId );
        packet.setCommand( PeerCommand.DATA_TRANSFER );

        byte[] content = new byte[Constants.PEER_PACKET_SIZE];
        int bytesRead = source.read( content );
        packet.setContentLength( bytesRead );

        if ( bytesRead == 0 ) {
            // no packet to send
            return null;
        }

        if ( bytesRead == -1 ) {
            packet.setCommand( PeerCommand.DISCONNECT );
            packet.setContentLength( 0 );
        }

        return packet;
    }

    /**
     * Creates a package fully populated with data from the given peer-enabled source (the input
     * stream coming from the other peer).
     * 
     * @param source
     *            the peer-enabled input stream
     * @return the created packet, fully populated with data from the given peer-enabled source (the
     *         input stream coming from the other peer).
     * @throws IOException
     *             if an exception is encountered during the packet creation
     */
    public static Packet packageFromPeerSource( InputStream source ) throws IOException {
        DataInputStream dataEnabledSource = new DataInputStream( source );
        int connectorId = dataEnabledSource.readInt();
        int workerId = dataEnabledSource.readInt();
        PeerCommand command = PeerCommand.fromCode( dataEnabledSource.readInt() );
        int contentLength = dataEnabledSource.readInt();

        byte[] content = new byte[contentLength];
        if ( contentLength > 0 && command != PeerCommand.END_OF_FILE ) {
            for ( int index = 0; index < contentLength; index++ ) {
                content[index] = (byte) dataEnabledSource.read();
            }
        }

        Packet packet = new Packet();
        packet.setConnectorId( connectorId );
        packet.setWorkerId( workerId );
        packet.setCommand( command );
        packet.setContentLength( contentLength );
        packet.setContent( content );

        return null;
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * Private hidden constructor. No instance makes sense out of this class.
     */
    private StreamPackagingUtils() {
        // this is an utility class; no instance makes sense
    }

}
