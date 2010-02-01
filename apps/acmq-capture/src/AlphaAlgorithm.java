import static java.lang.Math.PI;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.floor;

/**
 * A first version of the algorithm for the Diego player.
 * 
 * @author mocanu
 */
public class AlphaAlgorithm extends AbstractAlgorithm {

    /**
     * Finds the radar circle that the sled can describe, in order to get the best combination of
     * RED and GRAY pucks.
     * <p>
     * The absolute value represents the radius of the circle.
     * <p>
     * The sign of the value represents the side of the circle, based on the current direction of
     * the sled. Positive value represents the circle from the &quot;left&quot; side of the
     * direction (counter clockwise) and negative means on the &quot;right&quot; side of the sled's
     * direction.
     */
    private double findRadarCircleRadius() {
        double maxRadius = Const.TRAIL_LIMIT_SAFE / (PI);
        double radiusStep = 50;
        double currentRadius = radiusStep;
        double reducedSledAngle = stateManager.friendSled.direction
                - floor( stateManager.friendSled.direction / TWO_PI );
        RealPoint circleCenter = new RealPoint();
        double bestChoiceRadius = 0;
        int bestChoiceGrays = 0;

        while ( currentRadius <= maxRadius ) {
            double coordDifX = cos( PI - reducedSledAngle ) * currentRadius;
            double coordDifY = sin( PI - reducedSledAngle ) * currentRadius;

            circleCenter.x = stateManager.friendSled.coord.x - coordDifX;
            circleCenter.y = stateManager.friendSled.coord.y - coordDifY;

            // compute the situation of pucks inside the circle
            int grayPucks = 0;
            int friendPucks = 0;
            int enemyPucks = 0;

            for ( Puck puck : stateManager.pucks ) {
                if ( distance( puck.coord, circleCenter ) < currentRadius ) {
                    switch ( puck.type ) {
                        case GRAY: {
                            grayPucks++;
                            break;
                        }
                        case FRIEND: {
                            friendPucks++;
                            break;
                        }
                        case ENEMY: {
                            enemyPucks++;
                            break;
                        }
                    }
                }
            }

            if ( enemyPucks == 0 && grayPucks > bestChoiceGrays && friendPucks >= 1 ) {
                bestChoiceGrays = grayPucks;
                bestChoiceRadius = currentRadius;
            }

            currentRadius += radiusStep;
        }

        return bestChoiceRadius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "Position", stateManager.friendSled.coord.x, stateManager.friendSled.coord.y );
        }

        double radarCircleRadius = findRadarCircleRadius();
        if ( radarCircleRadius != 0 ) {
            double sledDelta = upperBound( (TWO_PI * Const.SLED_SPEED) / radarCircleRadius, Const.SLED_TURN_LIMIT );
            responseManager.sledDirectionDelta = Const.SLED_TURN_LIMIT;
        } else {
            responseManager.sledDirectionDelta = 0;
        }
    }

}
