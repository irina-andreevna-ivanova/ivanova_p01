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
import java.util.Collection;
import static ro.bmocanu.eval.junit44.Location.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Class showcasing parameterized tests.
 *
 * @author mocanu
 */
@RunWith( Parameterized.class )
public class TestParameterized {

    @Parameters
    public static Collection<Object[]> generateTestData() {
        return Arrays.asList( new Object[][] {
            {  5, Europe },
            { 10, Europe },
            { 15, Europe },
            {  5, Asia },
            { 10, Asia },
            { 15, Asia } } );
    }

    private int itemsWeight;
    private Location location;

    public TestParameterized(int itemsWeight, Location location) {
        super();
        this.itemsWeight = itemsWeight;
        this.location = location;
    }

    @Test
    public void testGetShippingPrice() {
        System.out.printf( "itemsWeight=%d, location=%s\n", itemsWeight, location.name() );
    }

}
