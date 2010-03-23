/**
 * 
 */
package ro.bmocanu.test.jms;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import ro.bmocanu.test.jms.model.CalculationRequest;
import ro.bmocanu.test.jms.model.Operation;

/**
 * 
 *
 * @author mocanu
 */
public class Launcher {
    private static final Logger LOG = Logger.getLogger( Launcher.class );
    
    public static void main( String[] args ) {
        String CONTEXT_CONFIGURATION = "context-jms-integration.xml";
        
        ApplicationContext context = new ClassPathXmlApplicationContext( CONTEXT_CONFIGURATION );
        JmsTemplate template = (JmsTemplate) context.getBean( "testJmsTemplate" );

        CalculationRequest request = new CalculationRequest();
        request.setNumberA( 23 );
        request.setNumberB( 40 );
        request.setOperation( Operation.ADDITION );
    
        LOG.info( "Sending calculation request: " + request );
        template.convertAndSend( "topic.calculationRequest", request );
    }
    

}
