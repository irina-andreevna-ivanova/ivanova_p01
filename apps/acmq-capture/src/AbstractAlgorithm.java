import static java.lang.Math.*;

/**
 * Base class for the Diego algorithm implementation.
 * 
 * @author mocanu
 */
public abstract class AbstractAlgorithm {

    public FileLogger log;
    public StateManager stateManager;
    public ResponseManager responseManager;

    public abstract void execute();

    // -------------------------------------------------------------------------------------------------

    protected static final double TWO_PI = Math.PI * 2;
    protected static final double PI_PER_2 = Math.PI / 2;

    public static final double distance( RealPoint p1, RealPoint p2 ) {
        double diffX = p1.x - p2.x;
        double diffY = p1.y - p2.y;
        return Math.sqrt( diffX * diffX + diffY * diffY );
    }

    /**
     * @param directionAngle the angle, in radians, of the current direction
     * @param distance the distance where the point should reside
     * @param rotation the rotation to be performed (counterclockwise=1, clockwise=-1)
     */
    public static final void movePointAt90degreesAndDistance( RealPoint point, RealPoint center, double directionAngle, double distance, int rotation ) {
        double finalAngle = directionAngle + PI_PER_2 * rotation;
        point.x = center.x + cos( finalAngle ) * distance; 
        point.y = center.y + sin( finalAngle ) * distance; 
    }

    public static final double upperBound( double value, double maxLimit ) {
        return (value < maxLimit) ? value : maxLimit;
    }

    // -------------------------------------------------------------------------------------------------

}
