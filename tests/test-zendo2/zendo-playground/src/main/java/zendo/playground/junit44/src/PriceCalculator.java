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

package zendo.playground.junit44.src;

/**
 * Dummy classes for testing features of JUnit 4.4+
 * 
 * @author mocanu
 */
public class PriceCalculator {

    /**
     * Returns the discount percentage for the given number of items ordered.
     * 
     * @param itemsOrdered
     * @return
     */
    public int getDiscountPercentage( int itemsOrdered ) {
        if ( itemsOrdered < 0 ) {
            throw new IllegalArgumentException( "ItemsOrdered cannot be less than 0 (it was " + itemsOrdered + ")" );
        } else if ( itemsOrdered <= 10 ) {
            return 10;
        } else if ( itemsOrdered <= 20 ) {
            return 20;
        } else {
            return 30;
        }
    }

}
