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

package ro.bmocanu.trafficproxy.base;

import ro.bmocanu.trafficproxy.Packet;

/**
 * Interface for the workers spawned for the configured workers. A worker is both used for input
 * connectors as well as the output connectors. This interface defines the common methods for both
 * parts.
 * 
 * @author mocanu
 */
public interface ConnectorWorker {
    
    public void processPacket( Packet packet );

}
