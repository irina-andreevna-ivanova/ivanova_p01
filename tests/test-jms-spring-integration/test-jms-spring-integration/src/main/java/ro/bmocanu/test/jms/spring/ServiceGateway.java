/**
 * 
 */
package ro.bmocanu.test.jms.spring;

import org.springframework.integration.annotation.Gateway;

import ro.bmocanu.test.jms.model.CalculationResponse;

/**
 * 
 * 
 * @author mocanu
 */
public interface ServiceGateway {

    @Gateway( requestChannel = "calculationResponse" )
    void sendCalculationResponse( CalculationResponse response );

}
