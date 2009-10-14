package ro.bmocanu.zendo.capabilities.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.ldap.LdapTemplate;

import ro.bmocanu.zendo.capabilities.AbstractCapability;
import ro.bmocanu.zendo.capabilities.CapabilityType;
import ro.bmocanu.zendo.capabilities.intf.SpringCapability;
import ro.bmocanu.zendo.capabilities.intf.SpringLDAPTemplateCapability;

/**
 * Default implementation for the Spring LdapTemplate capability.
 *
 * @author bogdan.mocanu
 */
public class SpringLDAPTemplateCapabilityImpl extends AbstractCapability implements SpringLDAPTemplateCapability {

    private LdapTemplate ldapTemplate;

    public void init() {
        SpringCapability springCapability = capabilitiesManager.getCapability( CapabilityType.SPRING );
        ApplicationContext context = springCapability.getApplicationContext();
        // for this version, this is a hard-coded bean name
        ldapTemplate = (LdapTemplate) context.getBean( "ldapTemplate" );
    }

    public void destroy() {
        // no code here, this capability is dependent on Spring capability, so we will let that capability
        // take care of cleaning the Spring context
    }

    public LdapTemplate getLdapTemplate() {
        return ldapTemplate;
    }

    public Object getCapabilityObject() {
        return getLdapTemplate();
    }
}
