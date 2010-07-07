/**
 * 
 */
package zendo.playground.spring.junit;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

/**
 * 
 * 
 * @author mocanu
 */
@RunWith( Theories.class )
@ContextConfiguration( locations = "classpath:/zendo/playground/spring/junit/spring-context.xml" )
public class MultipleRunnersTest {

    private TestContextManager testContextManager;

    @Autowired
    private PointProvider provider;

    @DataPoints
    public static int[] inputIndexes = { 0, 1, 2 };

    // ------------------------------------------------------------------------------------------------------

    @Before
    public void setUp() throws Exception {
        this.testContextManager = new TestContextManager( getClass() );
        this.testContextManager.prepareTestInstance( this );
    }

    @Theory
    public void testThatEverythingIsWell( int index ) {
        String point = provider.getPoint( index );
        System.out.println( "Point: " + point );
    }

}
