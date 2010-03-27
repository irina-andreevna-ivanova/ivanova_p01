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

import org.apache.log4j.Logger;

import ro.bmocanu.test.jms.model.CalculationResponse;
import ro.bmocanu.test.jms.utils.JsonMessageConverter;

/**
 * Listener for the calculation response queue.
 * 
 * @author mocanu
 */
public class ResponseQueueMessageListener implements MessageListener {
    private static final Logger LOG = Logger.getLogger( ResponseQueueMessageListener.class );

    private JsonMessageConverter converter = new JsonMessageConverter();

    /**
     * {@inheritDoc}
     */
    @Override
    public void onMessage( Message message ) {
        LOG.info( "Received a calculation response" );
        try {
            CalculationResponse response = (CalculationResponse) converter.fromMessage( message );
            LOG.info( "Result=" + response.getResult() );
        } catch ( Exception exception ) {
            LOG.error( exception );
        }
    }

}
