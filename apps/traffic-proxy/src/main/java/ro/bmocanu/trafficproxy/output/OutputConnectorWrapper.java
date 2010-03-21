/**
 * 
 */
package ro.bmocanu.trafficproxy.output;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author mocanu
 */
public class OutputConnectorWrapper {

    private OutputConnector connector;
    private Socket outputSocket;
    private OutputStream outputStream;

    /**
     * @param connector
     */
    public OutputConnectorWrapper(OutputConnector connector) {
        super();
        this.connector = connector;
    }

    // -------------------------------------------------------------------------------------------------

    public void connect() throws UnknownHostException, IOException {
        outputSocket = new Socket( connector.getAddress(), connector.getPort() );
        outputStream = outputSocket.getOutputStream();

    }

    public OutputStream getOutputStream() {
        return null;
    }

    public void disconnect() {

    }

}
