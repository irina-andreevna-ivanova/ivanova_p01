/**
 * 
 */
package ro.bmocanu.test.jms.spring;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import ro.bmocanu.test.jms.model.CalculationResponse;

/**
 * 
 * 
 * @author mocanu
 */
@Component
public class ServiceResponseHandler {
    private static final Logger LOG = Logger.getLogger( ServiceResponseHandler.class );

    @ServiceActivator( inputChannel = "testCalculationResponse" )
    public void handleCalculationRequest( CalculationResponse response ) {
        LOG.info( "CalculationResponse: " + response.getResult() );
    }

}
