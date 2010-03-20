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

package ro.bmocanu.trafficproxy;

/**
 * Central class of the <code>traffic-proxy</code>. It is responsible for assembling the connectors
 * and packet senders, as well as to make sure that the proper combinations are used so that each
 * communication between two connectors (INPUT and OUTPUT) is performed in full duplex mode.
 * <p>
 * This class works as a singleton, and is therefore accessible to any other class of the project.
 * 
 * @author mocanu
 */
public final class ProxyKernel {

    /**
     * The one and only instance of this class.
     */
    private static final ProxyKernel SINGLE_INSTANCE = new ProxyKernel();
    
    public static ProxyKernel getInstance() {
        return SINGLE_INSTANCE;
    }
    
    // -------------------------------------------------------------------------------------------------
    
    

}
