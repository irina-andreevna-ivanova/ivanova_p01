package ro.bmocanu.eval.junit44.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import ro.bmocanu.eval.junit44.rules.adds.Client;
import ro.bmocanu.eval.junit44.rules.adds.Server;

public class TestExternalResource {

    private Server myServer = new Server();

    @Rule
    public ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            myServer.connect();
        };

        @Override
        protected void after() {
            myServer.disconnect();
        };
    };

    @Test
    public void testFoo() {
        new Client().run( myServer );
    }
}
