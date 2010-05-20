/**
 * 
 */
package ro.bmocanu.test.spring.security.oauth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.Authentication;
import org.springframework.security.oauth.provider.ConsumerAuthentication;
import org.springframework.security.oauth.provider.OAuthAuthenticationHandler;
import org.springframework.security.oauth.provider.token.OAuthAccessProviderToken;

/**
 * 
 * 
 * @author mocanu
 */
public class DummyOAuthAuthenticationHandler implements OAuthAuthenticationHandler {

    public Authentication createAuthentication( HttpServletRequest request, ConsumerAuthentication authentication, OAuthAccessProviderToken authToken ) {
        return authentication;
    }
}
