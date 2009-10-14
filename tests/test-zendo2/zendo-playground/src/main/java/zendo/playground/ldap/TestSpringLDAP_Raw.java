package zendo.playground.ldap;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ldap.AttributesMapper;
import org.springframework.ldap.LdapTemplate;

/**
 * @author bogdan.mocanu
 */
@SuppressWarnings( "unchecked" )
public class TestSpringLDAP_Raw {
    private LdapTemplate ldapTemplate;

    public void setLdapTemplate( LdapTemplate ldapTemplate ) {
        this.ldapTemplate = ldapTemplate;
    }

    public List getAllObjects() {
        return ldapTemplate.search( "", "(objectClass=*)",
            new AttributesMapper() {
                public Object mapFromAttributes( Attributes attributes ) throws NamingException {
                    return attributes.get("objectClass").get();
                }
            }
        );
    }

    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"/zendo/playground/ldap/TestSpringLDAP_Raw.xml"});

        TestSpringLDAP_Raw inst = new TestSpringLDAP_Raw();
        inst.setLdapTemplate( (LdapTemplate) context.getBean( "ldapTemplate" ) );

        List results = inst.getAllObjects();

        for( Object obj : results ) {
            System.out.println( obj.toString() );
        }

        ((AbstractApplicationContext)context).registerShutdownHook();
    }
}
