package ro.bmocanu.zendo.capabilities.intf;

import org.springframework.context.ApplicationContext;

import ro.bmocanu.zendo.capabilities.Capability;

/**
 * Capability that allows tests to play with Spring's ApplicationContext configured in a manner similar with
 * the test properties (hierarchical load of beans).
 *
 * @author bogdan.mocanu
 */
public interface SpringCapability extends Capability {

    public ApplicationContext getApplicationContext();

}
