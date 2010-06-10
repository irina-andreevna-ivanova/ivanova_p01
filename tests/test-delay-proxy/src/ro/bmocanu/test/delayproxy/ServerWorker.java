/**
 * 
 */
package ro.bmocanu.test.delayproxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * @author mocanu
 */
public class ServerWorker extends Thread {

    private boolean threadShouldStop = false;;

    private Socket clientSocket;

    private ServerWorkerTransferrer clientTransf;
    private ServerWorkerTransferrer remoteTransf;

    /**
     * @param clientSocket
     */
    public ServerWorker(Socket clientSocket) {
        super();
        this.clientSocket = clientSocket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        System.out.println( "Worker " + getId() + ": Starting the communication" );

        try {
            Socket remoteSocket = new Socket( Constants.REMOTE_HOST, Constants.REMOTE_PORT );
            InputStream remoteSocketIS = remoteSocket.getInputStream();
            OutputStream remoteSocketOS = remoteSocket.getOutputStream();
            InputStream clientSocketIS = clientSocket.getInputStream();
            OutputStream clientSocketOS = clientSocket.getOutputStream();

            if ( Constants.START_DELAY > 0 ) {
                System.out.println( "Worker " + getId() + ": Start delay" );
                TimeUnit.MILLISECONDS.sleep( Constants.START_DELAY );
            }

            clientTransf = new ServerWorkerTransferrer( "local", clientSocket, clientSocketIS, remoteSocketOS );
            clientTransf.start();

            remoteTransf = new ServerWorkerTransferrer( "remote", remoteSocket, remoteSocketIS, clientSocketOS );
            remoteTransf.start();

            while ( !threadShouldStop && (!clientTransf.isFinished() || !remoteTransf.isFinished()) ) {
                TimeUnit.MILLISECONDS.sleep( 50 );
            }

            System.out.println( "Worker " + getId() + ": Communication is finished" );
            clientSocket.close();
            remoteSocket.close();

            clientTransf.managedStop();
            remoteTransf.managedStop();

        } catch ( IOException exception ) {
            exception.printStackTrace();
        } catch ( InterruptedException exception ) {
            exception.printStackTrace();
        }
    }

    public void managedStop() {
        System.out.println( "Worker " + getId() + ": Shutting down the worker" );
        threadShouldStop = true;
        try {
            this.join();
        } catch ( InterruptedException exception ) {
            exception.printStackTrace();
        }
    }

}
