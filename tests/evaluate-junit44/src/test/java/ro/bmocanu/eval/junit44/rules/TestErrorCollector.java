package ro.bmocanu.eval.junit44.rules;

import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class TestErrorCollector {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void example() {
        collector.addError( new Throwable( "first thing went wrong" ) );
        collector.addError( new Throwable( "second thing went wrong" ) );
    }

    @Test
    public void example2() {
        fail( "Something went BOOM!" );
    }

}
