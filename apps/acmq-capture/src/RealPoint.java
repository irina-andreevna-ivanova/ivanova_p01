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

/**
 * Simple structure class that joins 2 double coordinates in a single object. Used for representing
 * two-dimensional characteristics of objects.
 * 
 * @author mocanu
 */
public class RealPoint {

    public double x;

    public double y;

    // -------------------------------------------------------------------------------------------------

    public RealPoint() {
    }

    public RealPoint(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    public void copyFrom( RealPoint other ) {
        this.x = other.x;
        this.y = other.y;
    }

}
