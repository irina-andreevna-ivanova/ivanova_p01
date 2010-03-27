/**
 * 
 */
package ro.bmocanu.test.jms.utils;

import java.io.StringReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.sdicons.json.model.JSONObject;
import com.sdicons.json.parser.JSONParser;
import com.sdicons.json.serializer.marshall.JSONMarshall;
import com.sdicons.json.serializer.marshall.MarshallException;
import com.sdicons.json.serializer.marshall.MarshallValue;

/**
 * 
 *
 * @author mocanu
 */
public class JsonMessageConverter implements MessageConverter {
    private static final Logger LOG = Logger.getLogger( JsonMessageConverter.class );

    private JSONMarshall jsonMarshaller = new JSONMarshall();

    /**
     * {@inheritDoc}
     */
    public Message toMessage( Object object, Session session ) throws JMSException, MessageConversionException {
        try {
            String message = jsonMarshaller.marshall( object ).render( false );
            LOG.info( "JAVA -> JSON: " + message );
            return session.createTextMessage( message );
        } catch ( MarshallException exception ) {
            throw new MessageConversionException( "Unable to convert the given object to JSON", exception );
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object fromMessage( Message message ) throws JMSException, MessageConversionException {
        // can convert only TextMessages, containing JSON payloads
        if ( message instanceof TextMessage ) {
            TextMessage textMessage = (TextMessage) message;
            String textMessagePayload = textMessage.getText();
            LOG.info( "JSON -> JAVA: " + textMessage.getText() );
            try {
                JSONObject jsonObject = (JSONObject) new JSONParser( new StringReader( textMessagePayload ) ).nextValue();
                MarshallValue marshallValue = jsonMarshaller.unmarshall( jsonObject );
                return marshallValue.getReference();

            } catch ( Exception exception ) {
                throw new MessageConversionException( "Unable to unmarshal a JMS text message from JSON to objects. Message: "
                        + textMessagePayload, exception );
            }
        }
        return message;
    }

}
