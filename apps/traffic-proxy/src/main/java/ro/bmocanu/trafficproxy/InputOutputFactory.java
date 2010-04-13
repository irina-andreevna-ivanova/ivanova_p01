/**
 * 
 */
package ro.bmocanu.trafficproxy;

import java.io.Serializable;

import ro.bmocanu.trafficproxy.input.InputConnectorServer;
import ro.bmocanu.trafficproxy.input.InputConnectorWorker;

/**
 * 
 *
 * @author mocanu
 */
public interface InputOutputFactory extends Serializable {
    
    InputConnectorServer getInputConnectorServer();
    
    InputConnectorWorker getInputConnectorWorker();

}
