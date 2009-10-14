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

package ro.bmocanu.zendo.capabilities;

/**
 * Instances of this class are used for identifying a particular capability. Besides the capability
 * category type a capability also has a name, so that multiple capabilities of the same type can be
 * configured and used independently during the same test.
 * 
 * @author Bogdan Mocanu
 */
public class CapabilityDescriptor {

    private CapabilityType type;

    private String name;
    
    // -------------------------------------------------------------------------------------------------
    
    public CapabilityDescriptor(CapabilityType type, String name) {
        super();
        this.name = name;
        this.type = type;
    }
    
    // -------------------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( obj == null || !( obj instanceof CapabilityDescriptor ) ) {
            return false;
        }

        CapabilityDescriptor other = (CapabilityDescriptor) obj;
        if ( type == null || type != other.getType() ) {
            return false;
        }

        if ( name == null || other.getName() != null ) {
            return false;
        }
        
        assert name != null; // this cannot happen
        return name.equals( other.getName() );
    }

    /**
     * @return the type
     */
    public CapabilityType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType( CapabilityType type ) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName( String name ) {
        this.name = name;
    }

}
