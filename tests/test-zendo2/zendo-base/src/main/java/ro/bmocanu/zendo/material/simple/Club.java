/**
 * 
 */
package ro.bmocanu.zendo.material.simple;

import java.util.Set;

/**
 * A club has multiple members.
 * 
 * @author mocanu
 */
public class Club extends Base {

    private String name;

    private String address;

    private Set<Member> members;

    /**
     * Returns the name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the given value.
     * 
     * @param name
     *            the name to set
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Returns the address
     * 
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address to the given value.
     * 
     * @param address
     *            the address to set
     */
    public void setAddress( String address ) {
        this.address = address;
    }

    /**
     * Returns the members
     * 
     * @return the members
     */
    public Set<Member> getMembers() {
        return members;
    }

    /**
     * Sets the members to the given value.
     * 
     * @param members
     *            the members to set
     */
    public void setMembers( Set<Member> members ) {
        this.members = members;
    }

}
