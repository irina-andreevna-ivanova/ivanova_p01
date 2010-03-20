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

package ro.bmocanu.trafficproxy.output;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * @author mocanu
 */
public class PacketDispatcher extends ManageableThread {

    private List<OutputConnectorWrapper> outputConnectorWrappers;
    private InputStream packetInputStream;

    /**
     * 
     */
    public PacketDispatcher(InputStream packetInputStream, List<OutputConnector> outputConnectors) {
        this.packetInputStream = packetInputStream;
        outputConnectorWrappers = new ArrayList<OutputConnectorWrapper>();
        for ( OutputConnector connector : outputConnectors ) {
            outputConnectorWrappers.add( new OutputConnectorWrapper( connector ) );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() {
    }

}
