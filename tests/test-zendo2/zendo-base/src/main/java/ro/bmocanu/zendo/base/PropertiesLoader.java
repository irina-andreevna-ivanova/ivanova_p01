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

import java.util.Properties;

/**
 * Intarface for classes that can load test properties for a given test item. The item is given through
 * its test item descriptor (see {@link TestDescriptor}).
 * 
 * @author Bogdan Mocanu
 */
public interface PropertiesLoader {
    
    public void loadProperties( Properties properties, TestDescriptor descriptor );

}
