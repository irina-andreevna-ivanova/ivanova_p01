/*- 
 * Copyright Bogdan Mocanu, 2010
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package ro.bmocanu.test.jms.standalone;

import java.io.IOException;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import ro.bmocanu.test.jms.model.CalculationRequest;
import ro.bmocanu.test.jms.model.Operation;
import ro.bmocanu.test.jms.utils.JsonMessageConverter;

/**
 * Standalone launcher for testing the JMS without any support from Spring (core JMS usage).
 * 
 * @author mocanu
 */
public class StandaloneLauncher {
    private static final Logger LOG = Logger.getLogger( StandaloneLauncher.class );

    public static void main( String... args ) throws IOException, JMSException {
        LOG.info( "Loading the configuration" );
        Properties props = new Properties();
        props.load( StandaloneLauncher.class.getClassLoader().getResourceAsStream( "config.properties" ) );

        LOG.info( "Creating connection to ActiveMQ" );
        ConnectionFactory conFactory = new ActiveMQConnectionFactory( props.getProperty( "jms.broker.url" ) );
        Connection con = conFactory.createConnection();

        try {
            LOG.info( "Starting the connection" );
            con.start();

            LOG.info( "Creating the session" );
            Session session = con.createSession( false, Session.AUTO_ACKNOWLEDGE );

            LOG.info( "Creating the message listeners" );
            Destination responseQueue = session.createQueue( "jms.queue.calculationResponse" );
            MessageProducer queueProducer = session.createProducer( responseQueue );
            MessageConsumer queueConsumer = session.createConsumer( responseQueue );
            queueConsumer.setMessageListener( new ResponseQueueMessageListener() );

            Destination requestTopic = session.createTopic( "jms.topic.calculationRequest" );
            MessageConsumer topicConsumer = session.createConsumer( requestTopic );
            //topicConsumer.setMessageListener( new TopicMessageListener( session, queueProducer ) );

            // now produce a message, in order to test the result
            CalculationRequest request = new CalculationRequest();
            request.setNumberA( 105 );
            request.setNumberB( 89 );
            request.setOperation( Operation.SUBSTRACTION );

            JsonMessageConverter converter = new JsonMessageConverter();
            Message message = converter.toMessage( request, session );

            LOG.info( "Sending the calculation request" );
            MessageProducer topicProducer = session.createProducer( requestTopic );
            topicProducer.send( message );

            try {
                Thread.sleep( 2000 );
            } catch ( InterruptedException exception ) {
                exception.printStackTrace();
            }
        } finally {
            if ( con != null ) {
                con.stop();
                con.close();
            }
        }
    }

}
