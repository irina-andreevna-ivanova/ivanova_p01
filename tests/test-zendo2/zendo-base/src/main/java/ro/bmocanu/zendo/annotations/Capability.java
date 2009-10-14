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

package ro.bmocanu.zendo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ro.bmocanu.zendo.capabilities.CapabilityType;

/**
 * Annotations for test attributes that need to be set based on a particular capability.
 * 
 * @author Bogdan Mocanu
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
public @interface Capability {
    
    public CapabilityType value();
    
    public String name() default "";

}
