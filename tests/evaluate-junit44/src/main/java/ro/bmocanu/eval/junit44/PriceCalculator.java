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

package ro.bmocanu.eval.junit44;

import java.util.Arrays;
import java.util.List;

/**
 * Dummy classes for testing features of JUnit 4.4+
 *
 * @author mocanu
 */
public class PriceCalculator {

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

    public List<Integer> getDiscountPercentages() {
        return Arrays.asList( new Integer[] { 10, 20, 30 } );
    }

    public int getShippingPrice( int itemsWeight, Location location ) {
        int price = itemsWeight;

        switch ( location ) {
            case America: {
                price += 50;
                break;
            }
            case Asia: {
                if ( itemsWeight >= 100 ) {
                    price += 100;
                }
                break;
            }
        }
        return price;
    }
}
