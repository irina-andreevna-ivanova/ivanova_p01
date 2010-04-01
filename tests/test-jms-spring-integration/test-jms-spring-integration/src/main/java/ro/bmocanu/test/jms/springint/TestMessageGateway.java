/**
 * 
 */
package ro.bmocanu.test.jms.springint;

import org.springframework.integration.annotation.Gateway;

/**
 * 
 *
 * @author mocanu
 */
public interface TestMessageGateway {
    
    @Gateway( requestChannel = "testChannel" )
    void sendTestMessage( TestMessage message );

}
