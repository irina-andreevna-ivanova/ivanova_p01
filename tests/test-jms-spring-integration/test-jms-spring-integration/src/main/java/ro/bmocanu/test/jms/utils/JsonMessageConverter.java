/**
 * 
 */
package ro.bmocanu.test.jms.utils;

import java.io.StringReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

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

    private JSONMarshall jsonMarshaller = new JSONMarshall();

    /**
     * {@inheritDoc}
     */
    public Message toMessage( Object object, Session session ) throws JMSException, MessageConversionException {
        try {
            return session.createTextMessage( jsonMarshaller.marshall( object ).render( false ) );
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
