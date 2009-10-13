/**
 *
 */
package ro.bmocanu.eval.junit44;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import static ro.bmocanu.eval.junit44.Location.America;
import static ro.bmocanu.eval.junit44.Location.Asia;
import static ro.bmocanu.eval.junit44.Location.Europe;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Class showcasing theories.
 *
 * @author mocanu
 */
@RunWith( Theories.class )
public class TestTheories {

    @DataPoints
    public static int[] ITEM_WEIGHTS = { 10, 20, 30, 40, 50, 100, 200, 300 };

    @DataPoint
    public static Location LOC_EUROPE = Europe;
    @DataPoint
    public static Location LOC_ASIA = Asia;
    @DataPoint
    public static Location LOC_AMERICA = America;

    private PriceCalculator classUnderTest = new PriceCalculator();

    @Theory
    public void clientsInEurope_have0ShippingTax( int itemsWeight, Location location ) {
        assumeThat( location, is( Europe ) );

        int shippingAdd = classUnderTest.getShippingPrice( itemsWeight, location ) - itemsWeight;
        assertThat( shippingAdd, is( 0 ) );
    }

    @Theory
    public void clientsInAsia_taxedWith100_onlyOver100( int itemsWeight, Location location ) {
        assumeThat( location, is( Asia ) );
        assumeThat( itemsWeight, greaterThanOrEqualTo( 100 ) );

        int shippingAdd = classUnderTest.getShippingPrice( itemsWeight, location ) - itemsWeight;
        assertThat( shippingAdd, is( 100 ) );
    }

    @Theory
    public void clientsInAsia_taxedWith0_onlyBelow100( int itemsWeight, Location location ) {
        assumeThat( location, is( Asia ) );
        assumeThat( itemsWeight, lessThan( 100 ) );

        int shippingAdd = classUnderTest.getShippingPrice( itemsWeight, location ) - itemsWeight;
        assertThat( shippingAdd, is( 0 ) );
    }

    @Theory
    public void clientsInAmerica_taxedWith50( int itemsWeight, Location location ) {
        assumeThat( location, is( America ) );

        int shippingAdd = classUnderTest.getShippingPrice( itemsWeight, location ) - itemsWeight;
        assertThat( shippingAdd, is( 50 ) );
    }

}
