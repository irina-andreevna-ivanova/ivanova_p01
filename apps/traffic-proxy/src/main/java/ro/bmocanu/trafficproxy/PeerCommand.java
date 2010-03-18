/**
 * 
 */
package ro.bmocanu.trafficproxy;

/**
 * 
 *
 * @author mocanu
 */
public enum PeerCommand {
    
    CONNECT( (byte)10 ),
    
    DATA_TRANSFER( (byte)20 ),
    
    DATA_DROP( (byte)30 ),
    
    DISCONNECT( (byte)99 ) 
    ;
    
    byte code;
    
    /**
     * @param code
     */
    private PeerCommand(byte code) {
        this.code = code;
    }


    /**
     * Returns the code
     *
     * @return the code
     */
    public byte getCode() {
        return code;
    }
    
    public static PeerCommand fromCode( byte code ) {
        for( PeerCommand command : PeerCommand.values() ) {
            if ( command.getCode() == code ) {
                return command;
            }
        }
        // TODO Bogdan 2010.03.18: throw here a correct exception
        return null;
    }

}
