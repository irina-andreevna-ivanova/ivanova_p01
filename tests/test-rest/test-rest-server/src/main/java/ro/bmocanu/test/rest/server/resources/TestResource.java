/**
 * 
 */
package ro.bmocanu.test.rest.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author mocanu
 */
@Path( "/hello" )
public class TestResource {

    @GET
    @Produces( "text/plain" )
    public String getClickedMessage() {
        return "Hello world";
    }

}
