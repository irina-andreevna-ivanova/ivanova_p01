/**
 * 
 */
package zendo.playground.spring.beans;

/**
 * 
 *
 * @author mocanu
 */
public class DoorBean {
    
    private String name = "my door";

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
     * @param name the name to set
     */
    public void setName( String name ) {
        this.name = name;
    }

}
