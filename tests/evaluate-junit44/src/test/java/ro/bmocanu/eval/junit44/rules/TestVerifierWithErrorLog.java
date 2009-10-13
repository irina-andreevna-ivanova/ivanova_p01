package ro.bmocanu.eval.junit44.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.Verifier;

public class TestVerifierWithErrorLog {
    private List<Exception> errorLog = new ArrayList<Exception>();

    @Rule
    public MethodRule verifier = new Verifier() {
        @Override
        public void verify() {
            assertThat( errorLog.size(), is( 0 ) );
        }
    };

    @Test
    public void testThatMightWriteErrorLog() {
        // errorLog.add( new Exception( "Message" ) );
    }

}
