/**
 * 
 */
package ro.bmocanu.test.delayproxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * @author mocanu
 */
public class Server extends Thread {

    private boolean threadShouldStop = false;
    private List<ServerWorker> serverWorkers = new ArrayList<ServerWorker>();

    /**
     * @param port
     */
    public Server() {
        this.serverWorkers = new ArrayList<ServerWorker>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket( Constants.LISTENING_PORT );
            serverSocket.setSoTimeout( 1000 );

            while ( !threadShouldStop ) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println( "Accepted a new client request" );

                    ServerWorker worker = new ServerWorker( clientSocket );
                    serverWorkers.add( worker );
                    worker.start();
                } catch ( SocketTimeoutException exception ) {
                    // this is ok
                }

                TimeUnit.MILLISECONDS.sleep( 10 );
            }
        } catch ( IOException exception ) {
            exception.printStackTrace();
        } catch ( InterruptedException exception ) {
            exception.printStackTrace();
        }

        for ( ServerWorker worker : serverWorkers ) {
            worker.managedStop();
        }
    }

    public void managedStop() {
        System.out.println( "Shutting down the server" );
        threadShouldStop = true;
        try {
            this.join();
        } catch ( InterruptedException exception ) {
            exception.printStackTrace();
        }
    }

}
