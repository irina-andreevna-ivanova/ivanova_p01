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
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author mocanu
 */
public class OutputConnectorWrapper {

    private OutputConnector connector;
    private Socket outputSocket;
    private OutputStream outputStream;

    /**
     * @param connector
     */
    public OutputConnectorWrapper(OutputConnector connector) {
        super();
        this.connector = connector;
    }

    // -------------------------------------------------------------------------------------------------

    public void connect() throws UnknownHostException, IOException {
        outputSocket = new Socket( connector.getAddress(), connector.getPort() );
        outputStream = outputSocket.getOutputStream();

    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void disconnect() throws IOException {
        if ( outputSocket != null && !outputSocket.isClosed() ) {
            outputSocket.close();
        }
    }

}
