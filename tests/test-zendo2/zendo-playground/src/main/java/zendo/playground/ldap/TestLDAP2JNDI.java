package zendo.playground.ldap;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import ro.bmocanu.zendo.AbstractZendoTest;
import ro.bmocanu.zendo.ZendoCore;

/**
 * @author bogdan.mocanu
 */
public class TestLDAP2JNDI extends AbstractZendoTest {

    // Create root node (root context)
    public void test() {
        Properties env = new Properties();
        env.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory" );
        env.put( Context.PROVIDER_URL, "ldap://" + getString( "test.ldapServerName" ) + "/" );
        env.put( Context.SECURITY_PRINCIPAL, getString( "test.rootDN" ) );
        env.put( Context.SECURITY_CREDENTIALS, getString( "test.rootPass" ) );

        try {
            DirContext ctx = new InitialDirContext( env );
            ctx.createSubcontext( getString( "test.rootContext" ) );

        } catch ( NamingException e ) {
            e.printStackTrace();  
        }

    }

    public void test1() {
        System.out.println( properties.getString( "test.rootDN" ) );
    }

    public static void main( String[] args ) {
        ZendoCore.testThis();
    }

}
