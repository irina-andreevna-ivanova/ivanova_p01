/**
 * 
 */
package ro.bmocanu.trafficproxy.peers;

/**
 * The commands that one peer (the master one) can send to the other peer (the slave). The roles
 * between the peers can change depending on the input/output connectors.
 * 
 * @author mocanu
 */
public enum PeerCommand {

    CONNECT(10),

    DATA_TRANSFER(20),

    DATA_DROP(30),

    END_OF_FILE(88),

    DISCONNECT((byte) 99);

    int code;

    /**
     * @param code
     */
    private PeerCommand(int code) {
        this.code = code;
    }

    /**
     * Returns the code
     * 
     * @return the code
     */
    public int getCode() {
        return code;
    }

    public static PeerCommand fromCode( int code ) {
        for ( PeerCommand command : PeerCommand.values() ) {
            if ( command.getCode() == code ) {
                return command;
            }
        }
        // TODO Bogdan 2010.03.18: throw here a correct exception
        return null;
    }

}
