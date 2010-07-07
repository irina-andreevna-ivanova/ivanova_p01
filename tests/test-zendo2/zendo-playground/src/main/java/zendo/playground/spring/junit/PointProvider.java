/**
 * 
 */
package zendo.playground.spring.junit;

/**
 * 
 *
 * @author mocanu
 */
public class PointProvider {

    private static String[] inputPoints = { "Jane", "John", "Mark" };

    public String getPoint( int index ) {
        return inputPoints[index];
    }

}
