/**
 * 
 */
package ro.bmocanu.trafficproxy.connectors;

/**
 * 
 * 
 * @author mocanu
 */
public class OutputConnector extends AbstractConnector {

    private String address;

    /**
     * @param id
     * @param port
     * @param address
     */
    public OutputConnector(String id, int port, String address) {
        super( id, port );
        this.address = address;
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

}
