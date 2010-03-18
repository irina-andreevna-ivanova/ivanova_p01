/**
 * 
 */
package ro.bmocanu.trafficproxy;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 *
 * @author mocanu
 */
public class PacketSender {
    
    private OutputStream targetOS;
    
    public void send( Packet packet ) throws IOException {
        targetOS.write( packet.getConnectorId() );
        targetOS.write( packet.getCommand().getCode() );
        targetOS.write( packet.getContent() );
        targetOS.flush();
    }

}
