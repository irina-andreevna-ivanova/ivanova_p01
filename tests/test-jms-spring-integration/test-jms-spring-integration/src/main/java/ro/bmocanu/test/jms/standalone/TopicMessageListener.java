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

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.log4j.Logger;

import ro.bmocanu.test.jms.model.CalculationRequest;
import ro.bmocanu.test.jms.model.CalculationResponse;
import ro.bmocanu.test.jms.model.Operation;
import ro.bmocanu.test.jms.utils.JsonMessageConverter;

/**
 * @author mocanu
 */
public class TopicMessageListener implements MessageListener {
    private static final Logger LOG = Logger.getLogger( TopicMessageListener.class );

    private JsonMessageConverter converter = new JsonMessageConverter();

    private Session session;
    private MessageProducer messageProducer;

    // -------------------------------------------------------------------------------------------------

    /**
     * @param messageProducer
     */
    public TopicMessageListener(Session session, MessageProducer messageProducer) {
        this.session = session;
        this.messageProducer = messageProducer;
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void onMessage( Message message ) {
        LOG.info( "Received message from the topic" );
        try {
            CalculationRequest request = (CalculationRequest) converter.fromMessage( message );
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

            LOG.info( "Sending back calculation response" );
            messageProducer.send( converter.toMessage( response, session ) );

        } catch ( Exception exception ) {
            exception.printStackTrace();
        }
    }

}
