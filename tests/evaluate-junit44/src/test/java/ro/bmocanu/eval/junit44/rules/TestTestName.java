package ro.bmocanu.eval.junit44.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class TestTestName {
    @Rule
    public TestName name = new TestName();

    @Test
    public void testA() {
        System.out.println( name.getMethodName() );
        // do some testing
    }

    @Test
    public void testB() {
        System.out.println( name.getMethodName() );
        // do some testing
    }
}
