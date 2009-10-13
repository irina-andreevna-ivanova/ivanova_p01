package ro.bmocanu.eval.junit44.rules;

import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

public class TestTestWatchman {

    @Rule
    public MethodRule watchman = new TestWatchman() {
        @Override
        public void failed( Throwable e, FrameworkMethod method ) {
            System.out.println( method.getName() + " " + e.getClass().getSimpleName() );
        }

        @Override
        public void succeeded( FrameworkMethod method ) {
            System.out.println( method.getName() + " " + "success!" );
        }
    };

    @Test
    public void failingMethod() {
        fail( "XFiles-like reason" );
    }

    @Test
    public void okMethod() {
    }
}
