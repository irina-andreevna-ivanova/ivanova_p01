/**
 * 
 */
package zendo.playground.spring;

import org.springframework.context.ApplicationContext;

import ro.bmocanu.zendo.AbstractZendoTest;
import ro.bmocanu.zendo.ZendoCore;
import ro.bmocanu.zendo.annotations.Capability;
import ro.bmocanu.zendo.capabilities.CapabilityType;
import zendo.playground.spring.beans.RoomBean;

/**
 * 
 *
 * @author mocanu
 */
public class TestWeirdMapExtension extends AbstractZendoTest {
    
    @Capability( CapabilityType.SPRING )
    private ApplicationContext context;

    /**
     * {@inheritDoc}
     */
    @Override
    public void test() {
        RoomBean room = (RoomBean)context.getBean( "room" );
        System.out.println( room.getDoor().getName() );
    }
    
    public static void main( String[] args ) {
        ZendoCore.testThis();
    }

}
