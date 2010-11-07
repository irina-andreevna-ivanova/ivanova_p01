/**
 * 
 */
package ro.bmocanu.test.spring.security.oauth;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecret;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * @author mocanu
 */
public class DummyConsumerDetailsServiceImpl implements ConsumerDetailsService, UserDetailsService {

    private static final Log log = LogFactory.getLog( DummyConsumerDetailsServiceImpl.class );

    private Map<String, ConsumerDetails> consumers;

    public DummyConsumerDetailsServiceImpl() {
        consumers = new HashMap<String, ConsumerDetails>();
        consumers.put( "consumer-k1", createConsumerDetails( "consumer-k1", "consumer-n1", "consumer-secret1" ) );
        consumers.put( "consumer-k2", createConsumerDetails( "consumer-k2", "consumer-n2", "consumer-secret2" ) );
    }

    private ConsumerDetails createConsumerDetails( String consumerKey, String consumerName, String consumerSecret ) {
        SharedConsumerSecret secret = new SharedConsumerSecret( consumerSecret );

        BaseConsumerDetails bcd = new BaseConsumerDetails();
        bcd.setConsumerKey( consumerKey );
        bcd.setConsumerName( consumerName );
        bcd.setSignatureSecret( secret );
        bcd.setAuthorities( new GrantedAuthority[] { new GrantedAuthorityImpl( "ROLE_OAUTH_USER" ) } );

        // set this to false to enable the 2legged OAuth model
        // see http://spring-security-oauth.codehaus.org/twolegged.html
        bcd.setRequiredToObtainAuthenticatedToken( false );

        return bcd;
    }

    @Override
    public ConsumerDetails loadConsumerByConsumerKey( String key ) throws OAuthException {
        log.info( "Request received to find consumer for consumerKey=[" + key + "]" );
        ConsumerDetails consumer = consumers.get( key );
        if ( consumer == null ) {
            log.info( "Result: No consumer found for [" + key + "]"  );
            throw new OAuthException( "No consumer found for key " + key );
        }
        if ( log.isDebugEnabled() ) {
            log.debug( "Result: Found consumer [" + consumer.getConsumerName() + "]" );
        }
        return consumer;
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException, DataAccessException {
        throw new UnsupportedOperationException();
    }

}
