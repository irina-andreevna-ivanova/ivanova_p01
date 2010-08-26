/**
 * 
 */
package zendo.playground.junit47;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mocanu
 */
public class TestEveryItem {

    @Test
    public void testSomething() {
        List<Cat> result = new ArrayList<Cat>();
        result.add( new Cat( "pisi" ) );
        result.add( new Cat( "pisi" ) );
        
//        assertThat( (List)result, everyItem( hasProperty( "name", equalTo( "pisi" ) ) ) );
    }

}
