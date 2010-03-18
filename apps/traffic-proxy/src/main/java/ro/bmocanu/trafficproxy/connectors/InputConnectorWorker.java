/**
 * 
 */
package ro.bmocanu.trafficproxy.connectors;

import java.net.Socket;

import ro.bmocanu.trafficproxy.base.ManageableThread;

/**
 * 
 *
 * @author mocanu
 */
public class InputConnectorWorker extends ManageableThread {

    private Socket clientSocket;

    /**
     * @param clientSocket
     */
    public InputConnectorWorker(Socket clientSocket) {
        super();
        this.clientSocket = clientSocket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalRun() {
    }

}
