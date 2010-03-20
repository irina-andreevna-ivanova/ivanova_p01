/**
 * 
 */
package ro.bmocanu.trafficproxy.input;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import ro.bmocanu.trafficproxy.Packet;
import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * @author mocanu
 */
public class PacketSender extends ManageableThread {
    private static final Logger LOG = Logger.getLogger( PacketSender.class );

    private LinkedBlockingQueue<Packet> packetQueue;
    private OutputStream targetOutputStream;

    /**
     * @param packetQueue
     * @param targetOutputStream
     */
    public PacketSender(OutputStream targetOutputStream) {
        this.packetQueue = new LinkedBlockingQueue<Packet>();
        this.targetOutputStream = targetOutputStream;
    }

    public void send( Packet packet ) throws IOException {
        try {
            LOG.debug( "Received one packet: conId=" + packet.getConnectorId() + ", command=" + packet.getCommand().name() );
            packetQueue.put( packet );
        } catch ( InterruptedException exception ) {
            throw new IOException( exception );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() {
        Packet packet = packetQueue.poll();
        if ( packet != null ) {
            try {
                targetOutputStream.write( packet.getConnectorId() );
                targetOutputStream.write( packet.getCommand().getCode() );
                targetOutputStream.write( packet.getContent() );
                targetOutputStream.flush();
            } catch ( IOException exception ) {
                LOG.error( "Missed one packet; command=" + packet.getCommand().name() + ", connectorId="
                           + packet.getConnectorId(), exception );
            }
        }
    }

}
