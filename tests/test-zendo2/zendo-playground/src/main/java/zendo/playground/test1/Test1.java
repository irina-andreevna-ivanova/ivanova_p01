/* Copyright Bogdan Mocanu, 2008
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
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

package zendo.playground.test1;

import java.util.Date;
import java.util.UUID;

import ro.bmocanu.zendo.AbstractZendoTest;

class MyClassB {
    
    private void meth1( int param ) { };
    
}

class MyClassD extends MyClassB {
    
    public void meth1( int param ) { };
    
}

public class Test1 extends AbstractZendoTest {

    @Override
    public void test() {
        System.out.println( "Some test here" );
        
    }

    public static void main( String[] args ) {
        System.out.println( UUID.randomUUID().toString() );
        System.out.println( new Date() );
    }

}
