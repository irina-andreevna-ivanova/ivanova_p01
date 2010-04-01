/**
 * 
 */
package ro.bmocanu.test.jms.springint;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author mocanu
 */
@Component
public class TestMessageHandler {
    private static final Logger LOG = Logger.getLogger( TestMessageHandler.class );

    @ServiceActivator( inputChannel = "testChannel" )
    public void handleTestMessage( TestMessage message ) {
        LOG.info( "Thread " + Thread.currentThread().getId() + ": received test message: [" + message.getContent()
                + "]" );
    }

}
