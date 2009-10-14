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

package ro.bmocanu.zendo.base;

import ro.bmocanu.zendo.ZendoTest;

/**
 * Factory class for {@link TestDescriptor} objects based on a given instance of
 * a class that implements {@link ZendoTest}.
 *
 * @author Bogdan Mocanu
 */
public class TestDescriptorFactory {

    public TestDescriptor createDescriptor( ZendoTest zendoTest ) {
        TestDescriptor descriptor = new TestDescriptor();
        descriptor.setTestItem( zendoTest );
        descriptor.setClassName( zendoTest.getClass().getName() );
        descriptor.setPackageName( zendoTest.getClass().getPackage().getName() );

        return descriptor;
    }

}
