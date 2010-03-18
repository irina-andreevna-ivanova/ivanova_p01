/**
 * 
 */
package ro.bmocanu.trafficproxy.connectors;

/**
 * 
 * 
 * @author mocanu
 */
public class AbstractConnector implements Connector {

    private String id;
    private int port;

    /**
     * @param id
     * @param port
     */
    public AbstractConnector(String id, int port) {
        super();
        this.id = id;
        this.port = port;
    }

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
