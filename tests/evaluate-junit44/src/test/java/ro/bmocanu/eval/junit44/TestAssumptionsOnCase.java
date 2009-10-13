/**
 *
 */
package ro.bmocanu.eval.junit44;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Class showcasing usage of assumptions, on the toys.
 *
 * @author mocanu
 */
@RunWith( Parameterized.class )
public class TestAssumptionsOnCase {

    @Parameters
    public static Collection<Object[]> generateTestData() {
        return Arrays.asList( new Object[][] {
            { 5, Location.Europe },
            { 10, Location.Europe },
            { 15, Location.Europe },
            { 5, Location.Asia },
            { 10, Location.Asia },
            { 15, Location.Asia } } );
    }

    private int itemsWeight;
    private Location location;

    public TestAssumptionsOnCase(int itemsWeight, Location location) {
        super();
        this.itemsWeight = itemsWeight;
        this.location = location;
    }

    @Test
    public void testGetShippingPrice_Europe() {
        assumeThat( location, is( Location.Europe ) );
        System.out.printf( "itemsWeight=%d, location=%s\n", itemsWeight, location.name() );
    }

}
