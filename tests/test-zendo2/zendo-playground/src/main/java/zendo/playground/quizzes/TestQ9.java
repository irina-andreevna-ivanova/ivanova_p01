package zendo.playground.quizzes;

/**
 * @author bogdan.mocanu
 */

interface PacketStream {
    public void open();
    public void write( byte[] packet );
    public void close();
}

interface FlushablePacketStream extends PacketStream {
    public void flush();
}

public class TestQ9 {
}
