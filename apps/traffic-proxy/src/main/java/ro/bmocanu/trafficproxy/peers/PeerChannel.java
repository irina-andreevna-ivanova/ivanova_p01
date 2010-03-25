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

package ro.bmocanu.trafficproxy.peers;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.springframework.stereotype.Component;

/**
 * A channel between 2 peers that contains both pipes: input and output.
 * 
 * @author mocanu
 */
@Component
public class PeerChannel {

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    /**
     * Flag that indicates whether the connection between peers is up or down.
     */
    private boolean connectionUp = false;

    // -------------------------------------------------------------------------------------------------

    /**
     * Returns the inputStream
     * 
     * @return the inputStream
     */
    public DataInputStream getInputStream() {
        return inputStream;
    }

    /**
     * Returns the outputStream
     * 
     * @return the outputStream
     */
    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Sets the inputStream to the given value.
     * 
     * @param inputStream
     *            the inputStream to set
     */
    public void setInputStream( DataInputStream inputStream ) {
        this.inputStream = inputStream;
    }

    /**
     * Sets the outputStream to the given value.
     * 
     * @param outputStream
     *            the outputStream to set
     */
    public void setOutputStream( DataOutputStream outputStream ) {
        this.outputStream = outputStream;
    }

    /**
     * Returns the connectionUp
     * 
     * @return the connectionUp
     */
    public boolean isConnectionUp() {
        return connectionUp;
    }

    /**
     * Sets the connectionUp to the given value.
     * 
     * @param connectionUp
     *            the connectionUp to set
     */
    public void setConnectionUp( boolean connectionUp ) {
        this.connectionUp = connectionUp;
    }

}
