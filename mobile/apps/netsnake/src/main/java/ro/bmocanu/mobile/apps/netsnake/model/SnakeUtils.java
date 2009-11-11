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

package ro.bmocanu.mobile.apps.netsnake.model;

/**
 * Contains utility methods for computing various elements related to the snake.
 * 
 * @author mocanu
 */
public class SnakeUtils {
    
    /**
     * Returns the modificators for the given direction angle.
     * 
     * @return an array containing the 2 modificators for the given direction angle.
     */
    public static int[] getDirectionsModificators( int directionAngle ) {
        int[] result = new int[2];
        int theX = 0;
        int theY = 1;
        int multiplier = 2;
        switch ( directionAngle ) {
            case 0: { // UP
                result[theX] = 0;
                result[theY] = -multiplier;
                break;
            }
            case 90: { // RIGHT
                result[theX] = multiplier;
                result[theY] = 0;
                break;
            }
            case 180: { // DOWN
                result[theX] = 0;
                result[theY] = multiplier;
                break;
            }
            case 270: { // LEFT
                result[theX] = -multiplier;
                result[theY] = 0;
                break;
            }
        }

        return result;
    }

}
