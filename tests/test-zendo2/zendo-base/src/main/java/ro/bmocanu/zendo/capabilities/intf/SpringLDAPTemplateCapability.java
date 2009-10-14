package ro.bmocanu.zendo.capabilities.intf;

import org.springframework.ldap.LdapTemplate;

import ro.bmocanu.zendo.capabilities.Capability;

/**
 * Interface for the Spring LDAPTemplate capability. It provides methods for getting the fully configured
 * Spring LdapTemplate instance.
 *
 * @author bogdan.mocanu
 */
public interface SpringLDAPTemplateCapability extends Capability {

    public LdapTemplate getLdapTemplate();

}
