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

import ro.bmocanu.trafficproxy.base.Manageable;
import ro.bmocanu.trafficproxy.peers.Packet;

/**
 * The component responsible with accumulating the packets in some internal buffer, and then
 * dispatching them one by one to the corresponding connectors for sending through the wires.
 * 
 * @author mocanu
 */
public interface PacketDispatcher extends Manageable {

    void dispatch( Packet packet ) throws IOException;

}
