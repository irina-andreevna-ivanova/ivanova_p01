/**
 * 
 */
package zendo.playground.junit47;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.everyItem;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

/**
 * @author mocanu
 */
public class TestEveryItem {

    @Test
    public void testSomething() {
        List<Cat> result = new ArrayList<Cat>();
        result.add( new Cat( "pisi" ) );
        result.add( new Cat( "pisi" ) );
        
        assertThat( (List)result, everyItem( hasProperty( "name", equalTo( "pisi" ) ) ) );
    }

}
