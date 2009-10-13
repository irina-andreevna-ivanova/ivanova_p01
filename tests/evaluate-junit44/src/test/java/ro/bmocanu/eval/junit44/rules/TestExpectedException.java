package ro.bmocanu.eval.junit44.rules;

import static org.hamcrest.Matchers.startsWith;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestExpectedException {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsNothing() {
    }

    @Test
    public void throwsNullPointerException() {
        thrown.expect( NullPointerException.class );
        throw new NullPointerException();
    }

    @Test
    public void throwsNullPointerExceptionWithMessage() {
        thrown.expect( NullPointerException.class );
        thrown.expectMessage( "happened?" );
        thrown.expectMessage( startsWith( "What" ) );
        throw new NullPointerException( "What happened?" );
    }
}
