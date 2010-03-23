/**
 * 
 */
package ro.bmocanu.test.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mocanu
 */
@Component
public class Configuration {

    @Value( "#{jms.topic.calculationRequest}" )
    private String calculationRequestTopic;

    /**
     * Returns the calculationRequestTopic
     * 
     * @return the calculationRequestTopic
     */
    public String getCalculationRequestTopic() {
        return calculationRequestTopic;
    }

}
