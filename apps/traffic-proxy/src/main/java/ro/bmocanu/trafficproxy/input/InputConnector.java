/**
 * 
 */
package ro.bmocanu.trafficproxy.input;

/**
 * 
 * 
 * @author mocanu
 */
public class InputConnector {

    private String id;

    private int port;

    /**
     * Returns the id
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id to the given value.
     * 
     * @param id
     *            the id to set
     */
    public void setId( String id ) {
        this.id = id;
    }

    /**
     * Returns the port
     * 
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the port to the given value.
     * 
     * @param port
     *            the port to set
     */
    public void setPort( int port ) {
        this.port = port;
    }

}
