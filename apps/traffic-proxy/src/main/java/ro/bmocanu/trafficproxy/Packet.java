/**
 * 
 */
package ro.bmocanu.trafficproxy;

/**
 * 
 * 
 * @author mocanu
 */
public class Packet {

    private byte connectorId;

    private PeerCommand command;

    private byte[] content;

    /**
     * Returns the connectorId
     * 
     * @return the connectorId
     */
    public byte getConnectorId() {
        return connectorId;
    }

    /**
     * Sets the connectorId to the given value.
     * 
     * @param connectorId
     *            the connectorId to set
     */
    public void setConnectorId( byte connectorId ) {
        this.connectorId = connectorId;
    }

    /**
     * Returns the command
     * 
     * @return the command
     */
    public PeerCommand getCommand() {
        return command;
    }

    /**
     * Sets the command to the given value.
     * 
     * @param command
     *            the command to set
     */
    public void setCommand( PeerCommand command ) {
        this.command = command;
    }

    /**
     * Returns the content
     * 
     * @return the content
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Sets the content to the given value.
     * 
     * @param content
     *            the content to set
     */
    public void setContent( byte[] content ) {
        this.content = content;
    }

}
