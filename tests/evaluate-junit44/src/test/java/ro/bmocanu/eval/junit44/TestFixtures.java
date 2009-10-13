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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Class showcasing test fixtures (Before, After, BeforeClass, AfterClass)
 * 
 * @author mocanu
 */
@RunWith( JUnit4.class )
public class TestFixtures {

    @BeforeClass
    public static void beforeClass() {
        System.out.println( "TestFixtures.beforeClass()" );
    }

    @Before
    public void before1() {
        System.out.println( "TestFixtures.before()" );
    }

    @Test
    public void test() {
        System.out.println( "TestFixtures.test()" );
    }

    @After
    public void after1() {
        System.out.println( "TestFixtures.after()" );
    }

    @AfterClass
    public static void afterClass() {
        System.out.println( "TestFixtures.afterClass()" );
    }

}
