/**
 * 
 */
package ro.bmocanu.test.delayproxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * @author mocanu
 */
public class ServerWorkerTransferrer extends Thread {

    private boolean threadShouldStop = false;;

    private Socket socket;

    private InputStream inputStream;

    private OutputStream outputStream;

    private boolean finished = false;

    private String name;

    /**
     * @param inputStream
     * @param outputStream
     */
    public ServerWorkerTransferrer(String name, Socket socket, InputStream inputStream, OutputStream outputStream) {
        super();
        this.name = name;
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        System.out.println( "Worker transferrer " + getId() + ": Starting the communication" );

        try {
            byte[] buffer = new byte[1024];
            int bytesRead = 1024;

            try {
                while ( !threadShouldStop && bytesRead >= 0 && !socket.isClosed() ) {
                    try { 
                        bytesRead = inputStream.read( buffer );
                    } catch ( SocketException exception ) {
                        // this is strange, should be thoroughly investigated
                        System.out.println( "Connection reset :(" );
                        bytesRead = -1;
                    }
                    if ( bytesRead > 0 ) {
                        outputStream.write( buffer, 0, bytesRead );
                    }

                    TimeUnit.MILLISECONDS.sleep( 10 );
                }
            } catch ( SocketException exception ) {
                System.out.println( "Worker transferrer error: " + name );
                exception.printStackTrace();
            }

            // inputStream.close();
            // outputStream.close();
            System.out.println( "Worker transferrer " + getId() + ": Communication is finished" );
            finished = true;

        } catch ( IOException exception ) {
            exception.printStackTrace();
        } catch ( InterruptedException exception ) {
            exception.printStackTrace();
        }
    }

    public void managedStop() {
        if ( finished ) {
            return;
        }

        System.out.println( "Worker transferrer " + getId() + ": Shutting down the worker" );
        threadShouldStop = true;
        try {
            this.join();
        } catch ( InterruptedException exception ) {
            exception.printStackTrace();
        }
    }

    /**
     * Returns the finished
     * 
     * @return the finished
     */
    public boolean isFinished() {
        return finished;
    }

}
