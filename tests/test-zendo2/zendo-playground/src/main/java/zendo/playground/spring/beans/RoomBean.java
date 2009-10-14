/**
 * 
 */
package zendo.playground.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 *
 * @author mocanu
 */
public class RoomBean {
    
    @Autowired
    private DoorBean door;
    
    @Autowired
    private SomeMap map;

    // ------------------------------------------------------------------------------------------------------
    
    /**
     * Returns the door
     *
     * @return the door
     */
    public DoorBean getDoor() {
        return door;
    }

    /**
     * Returns the map
     *
     * @return the map
     */
    public SomeMap getMap() {
        return map;
    }

    /**
     * Sets the map to the given value.
     *
     * @param map the map to set
     */
    public void setMap( SomeMap map ) {
        this.map = map;
    }
    
    

}
