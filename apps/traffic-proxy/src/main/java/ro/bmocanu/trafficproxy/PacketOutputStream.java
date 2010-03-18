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
public class PacketOutputStream extends OutputStream {
    
    private PacketDispatcher dispatcher;
    
    private byte[] buffer;
    private int bufferIndex;

    /**
     * @param dispatcher
     */
    private PacketOutputStream(PacketDispatcher dispatcher) {
        super();
        this.dispatcher = dispatcher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write( int b ) throws IOException {
        
    }

}
