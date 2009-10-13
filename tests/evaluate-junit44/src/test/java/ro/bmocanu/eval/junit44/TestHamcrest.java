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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * Class showcasing the usage of Hamcrest (<a
 * href="http://code.google.com/p/hamcrest/">http://code.google.com/p/hamcrest/</a>) assertions.
 * 
 * @author mocanu
 */
public class TestHamcrest {
    
    private PriceCalculator classUnderTest = new PriceCalculator();
    
    @Test
    public void testGetDiscountPercentages_classic() {
        List<Integer> result = classUnderTest.getDiscountPercentages();
        assertNotNull( result );
        assertEquals( 3, result.size() );
        assertTrue( result.get( 0 ) == 20 );
        assertTrue( result.get( 1 ) == 20 );
        assertTrue( result.get( 2 ) == 30 );
    }
 
    @Test
    public void testGetDiscountPercentages_hamcrest() {
        List<Integer> result = classUnderTest.getDiscountPercentages();
        assertThat( result, is( notNullValue() ) );
        assertThat( result.size(), is( 3 ) );
        assertThat( result.get( 0 ), is( 20 ) );
        assertThat( result.get( 1 ), is( 20 ) );
        assertThat( result.get( 2 ), is( 30 ) );
    }

}
