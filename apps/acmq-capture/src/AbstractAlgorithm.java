/**
 * Base class for the Diego algorithm implementation.
 * 
 * @author mocanu
 */
public abstract class AbstractAlgorithm {
    
    public static final double distance( RealPoint p1, RealPoint p2 ) {
        double diffX = p1.x - p2.x;
        double diffY = p1.y - p2.y;
        return Math.sqrt( diffX * diffX + diffY * diffY );
    }

}
