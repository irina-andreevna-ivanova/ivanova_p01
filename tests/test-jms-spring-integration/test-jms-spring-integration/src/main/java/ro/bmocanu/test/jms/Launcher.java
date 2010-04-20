/**
 * 
 */
package ro.bmocanu.test.jms;

import javax.jms.Destination;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import ro.bmocanu.test.jms.model.CalculationRequest;
import ro.bmocanu.test.jms.model.Operation;
import ro.bmocanu.test.jms.springint.TestMessage;
import ro.bmocanu.test.jms.springint.TestMessageGateway;

/**
 * 
 * 
 * @author mocanu
 */
public class Launcher {
    private static final Logger LOG = Logger.getLogger( Launcher.class );

    // ------------------------------------------------------------------------------------------------------

    public static void main( String... args ) {
        String CONTEXT_CONFIGURATION = "context-jms-integration.xml";

        CalculationRequest request = new CalculationRequest();
        request.setNumberA( 23 );
        request.setNumberB( 40 );
        request.setOperation( Operation.ADDITION );

        ApplicationContext context = new ClassPathXmlApplicationContext( CONTEXT_CONFIGURATION );
        ((AbstractApplicationContext) context).registerShutdownHook();
        JmsTemplate template = (JmsTemplate) context.getBean( "jmsTemplate" );
        Destination topic = (Destination) context.getBean( "calculationRequestTopic" );

        LOG.info( "Sending calculation request: " + request );
        template.convertAndSend( topic, request );
    }

    // ------------------------------------------------------------------------------------------------------

    public static void main2( String... args ) {
        String CONTEXT_CONFIGURATION = "context-springint.xml";

        TestMessage msg = new TestMessage();
        msg.setContent( "oranges" );

        ApplicationContext context = new ClassPathXmlApplicationContext( CONTEXT_CONFIGURATION );
        ((AbstractApplicationContext) context).registerShutdownHook();

        LOG.info( "Thread " + Thread.currentThread().getId() + ": sending test message: [" + msg.getContent() + "]" );
        ((TestMessageGateway)context.getBean( "testMessageGateway" )).sendTestMessage( msg );
    }

}
