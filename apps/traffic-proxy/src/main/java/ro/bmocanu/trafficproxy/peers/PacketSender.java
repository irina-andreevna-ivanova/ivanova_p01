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

import java.io.IOException;

import ro.bmocanu.trafficproxy.base.Manageable;

/**
 * The component responsible with accumulating the packets to send in some internal buffer, and
 * sending them one by one, through the peer channel, to the other party. The class provides an
 * asynchronous mechanism for receiving the packets and sending them one by one.
 * 
 * @author mocanu
 */
public interface PacketSender extends Manageable {

    void send( Packet packet ) throws IOException;

}
