package zendo.playground.ldap;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.AttributesMapper;
import org.springframework.ldap.LdapTemplate;

import ro.bmocanu.zendo.AbstractZendoTest;
import ro.bmocanu.zendo.ZendoCore;
import ro.bmocanu.zendo.annotations.Capability;
import ro.bmocanu.zendo.capabilities.CapabilityType;

/**
 * @author bogdan.mocanu
 */
public class TestSpringLDAP_Cap extends AbstractZendoTest {

    @Capability( CapabilityType.SPRING_LDAP_TEMPLATE )
    private LdapTemplate ldapTemplate;

    @Override
    @SuppressWarnings( "unchecked" )
    public void test() {
        List results = ldapTemplate.search( "", "(objectClass=*)",
                new AttributesMapper() {
                    public Object mapFromAttributes( Attributes attributes ) throws NamingException {
                        return attributes.get( "objectClass" ).get();
                    }
                }
        );

        for ( Object obj : results ) {
            System.out.println( obj.toString() );
        }
    }

    public static void main( String[] args ) {
        ZendoCore.testThis();
    }
}
