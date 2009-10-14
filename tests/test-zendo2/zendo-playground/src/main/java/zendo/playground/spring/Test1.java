package zendo.playground.spring;

import ro.bmocanu.zendo.AbstractZendoTest;
import ro.bmocanu.zendo.ZendoCore;
import ro.bmocanu.zendo.capabilities.CapabilityType;
import ro.bmocanu.zendo.capabilities.intf.SpringCapability;

/**
 * @author bogdan.mocanu
 */
public class Test1 extends AbstractZendoTest {
    
    //private int type;

    @Override
    public void test() {
        SpringCapability springCap = capabilities.getCapability( CapabilityType.SPRING );
        System.out.println( springCap.getApplicationContext().getBean( "mybean" ) );
    }

    public static void main( String args[] ) {
        ZendoCore.testThis();
    }

}
