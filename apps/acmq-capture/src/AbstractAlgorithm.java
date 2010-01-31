/**
 * Base class for the Diego algorithm implementation.
 * 
 * @author mocanu
 */
public abstract class AbstractAlgorithm {

    public StateManager stateManager;
    public ResponseManager responseManager;
    
    public abstract void execute();
    
    // -------------------------------------------------------------------------------------------------
    
    protected static final double TWO_PI = Math.PI * 2;
    
    public static final double distance( RealPoint p1, RealPoint p2 ) {
        double diffX = p1.x - p2.x;
        double diffY = p1.y - p2.y;
        return Math.sqrt( diffX * diffX + diffY * diffY );
    }
    
    public static final double upperBound( double value, double maxLimit ) {
        return ( value < maxLimit ) ? value : maxLimit;
    }
    
    // -------------------------------------------------------------------------------------------------

}
