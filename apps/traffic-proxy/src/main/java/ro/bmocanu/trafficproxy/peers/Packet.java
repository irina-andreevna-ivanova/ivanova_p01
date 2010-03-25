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

import ro.bmocanu.trafficproxy.Constants;

/**
 * A packet is a single unit of data sent from an input connector worker to the peer channel. The
 * packet is composed of 2 identification numbers (the connector ID and the connector's worker ID),
 * the peer command (that signals to the other party what action should be taken, such as CONNECT,
 * CLOSE, etc; see {@link PeerCommand}) and the content of the packet that is composed of N bytes
 * (dictated by {@link Constants#PEER_PACKET_SIZE}.
 * 
 * @author mocanu
 * @see PeerCommand
 */
public class Packet {

    /**
     * The ID of the connector that produced this package. The same ID belongs to the connector
     * where this packet must be sent.
     */
    private int connectorId;

    /**
     * The ID of the connector's worker that produced this package. The same ID belongs to the
     * worker of the connector from the other party.
     */
    private int workerId;

    /**
     * The commnad to send to other party, for execution.
     */
    private PeerCommand command;

    /**
     * The content of the packet.
     */
    private byte[] content;

    /**
     * The length of the packet's content (the exact number of bytes that compose the information
     * packaged by this object).
     */
    private int contentLength;

    // -------------------------------------------------------------------------------------------------

    /**
     * Returns the connectorId
     * 
     * @return the connectorId
     */
    public int getConnectorId() {
        return connectorId;
    }

    /**
     * Sets the connectorId to the given value.
     * 
     * @param connectorId
     *            the connectorId to set
     */
    public void setConnectorId( int connectorId ) {
        this.connectorId = connectorId;
    }

    /**
     * Returns the command
     * 
     * @return the command
     */
    public PeerCommand getCommand() {
        return command;
    }

    /**
     * Sets the command to the given value.
     * 
     * @param command
     *            the command to set
     */
    public void setCommand( PeerCommand command ) {
        this.command = command;
    }

    /**
     * Returns the content
     * 
     * @return the content
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Sets the content to the given value.
     * 
     * @param content
     *            the content to set
     */
    public void setContent( byte[] content ) {
        this.content = content;
    }

    /**
     * Returns the workerId
     * 
     * @return the workerId
     */
    public int getWorkerId() {
        return workerId;
    }

    /**
     * Sets the workerId to the given value.
     * 
     * @param workerId
     *            the workerId to set
     */
    public void setWorkerId( int workerId ) {
        this.workerId = workerId;
    }

    /**
     * Returns the contentLength
     * 
     * @return the contentLength
     */
    public int getContentLength() {
        return contentLength;
    }

    /**
     * Sets the contentLength to the given value.
     * 
     * @param contentLength
     *            the contentLength to set
     */
    public void setContentLength( int contentLength ) {
        this.contentLength = contentLength;
    }

}
