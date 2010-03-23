/**
 * 
 */
package ro.bmocanu.test.jms.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import ro.bmocanu.test.jms.model.CalculationRequest;
import ro.bmocanu.test.jms.model.CalculationResponse;
import ro.bmocanu.test.jms.model.Operation;

/**
 * 
 *
 * @author mocanu
 */
@Component
public class ServiceRequestHandler {
    private static final Logger LOG = Logger.getLogger( ServiceRequestHandler.class );
    
    @Autowired
    private ServiceGateway gateway;
    
    @ServiceActivator( inputChannel="calculationRequest" )
    public void handleCalculationRequest( CalculationRequest request ) {
        LOG.info( "CalculationRequest: " + request );
        
        int result = 0;
        if ( request.getOperation() == Operation.ADDITION ) {
            result = request.getNumberA() + request.getNumberB();
        } else if ( request.getOperation() == Operation.SUBSTRACTION ) {
            result = request.getNumberA() - request.getNumberB();
        } else {
            throw new UnsupportedOperationException( "Cannot perform operation named " + request.getOperation() );
        }
        
        CalculationResponse response = new CalculationResponse();
        response.setResult( result );
        gateway.sendCalculationResponse( response );
    }

}
