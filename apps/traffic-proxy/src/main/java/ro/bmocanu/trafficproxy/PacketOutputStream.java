/**
 * 
 */
package ro.bmocanu.trafficproxy;

import java.io.IOException;
import java.io.OutputStream;

import ro.bmocanu.trafficproxy.connectors.InputConnector;

/**
 * 
 *
 * @author mocanu
 */
public class PacketOutputStream extends OutputStream {
    
    private InputConnector connector;
    private OutputStream decoratedOutputStream;
    private int byteIndex;

    /**
     * @param connector
     * @param decoratedOutputStream
     */
    private PacketOutputStream(InputConnector connector, OutputStream decoratedOutputStream) {
        super();
        this.connector = connector;
        this.decoratedOutputStream = decoratedOutputStream;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write( int b ) throws IOException {
        
    }

}
