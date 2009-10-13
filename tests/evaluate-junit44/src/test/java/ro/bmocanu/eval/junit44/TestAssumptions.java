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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;

import org.junit.Test;

/**
 * Class showcasing assumptions.
 *
 * @author mocanu
 */
public class TestAssumptions {

    @Test
    public void testGetDiscountPercentage_WindowsXP() {
        assumeThat( System.getProperty( "os.name" ), is( "Windows XP" ) );
        System.out.println( "test something on Windows" );
    }

    @Test
    public void testGetDiscountPercentage_Unix() {
        assumeThat( System.getProperty( "os.name" ), is( "Unix" ) );
        System.out.println( "test something on Unix" );
    }

}
