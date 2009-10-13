package ro.bmocanu.eval.junit44.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.Timeout;

public class TestGlobalTimeout {
    public static String log;

    @Rule
    public MethodRule globalTimeout = new Timeout( 20 );

    @Test
    public void testInfiniteLoop1() {
        log += "ran1";
        for ( ;; ) {
        }
    }

    @Test
    public void testInfiniteLoop2() {
        log += "ran2";
        for ( ;; ) {
        }
    }
}
