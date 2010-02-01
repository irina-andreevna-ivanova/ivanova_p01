import static java.lang.Math.floor;

/**
 * A first version of the algorithm for the Diego player.
 * 
 * @author mocanu
 */
public class AlphaAlgorithm extends AbstractAlgorithm {
    
    private double currentPlan_delta = 0;
    private int currentPlan_grays = 0;
    
    private int newPlan_grays = 0;
    private double newPlan_radius = 0;
    
    // ------------------------------------------------------------------------------------------------------

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
    private void findRadarCircleRadius() {
        Sled sled = stateManager.friendSled;
        double maxRadius = Const.TRAIL_LIMIT_SAFE / TWO_PI;
        double radiusStep = 10;
        double reducedSledAngle = sled.direction - floor( sled.direction / TWO_PI ) * TWO_PI;
        RealPoint circleCenter = new RealPoint();
        double bestChoiceRadius = 0;
        int bestChoiceGrays = 0;
        int[] sidesForRadar = new int[] { 1, -1 };

        for ( int sideIndex = 0; sideIndex < sidesForRadar.length; sideIndex++ ) {
            double currentRadius = radiusStep;
            while ( currentRadius <= maxRadius ) {
                movePointAt90degreesAndDistance( circleCenter, sled.coord, reducedSledAngle, currentRadius, sidesForRadar[ sideIndex ] );

                if ( Const.DEBUG_SLED ) {
                    log.info( "sled", "ReducedSledAngle", reducedSledAngle );
                    log.info( "sled", "Position", sled.coord.x, sled.coord.y, sled.direction );
                    log.info( "sled", "CircleX", circleCenter.x, "CircleY", circleCenter.y );
                }

                // compute the situation of pucks inside the circle
                int grayPucks = 0;
                int friendPucks = 0;
                int enemyPucks = 0;

                for ( Puck puck : stateManager.pucks ) {
                    if ( distance( puck.coord, circleCenter ) < currentRadius - 5 ) {
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

                if ( enemyPucks == 0 && grayPucks >= bestChoiceGrays && friendPucks >= 1 ) {
                    bestChoiceGrays = grayPucks;
                    bestChoiceRadius = currentRadius * sidesForRadar[ sideIndex ];
                }

                currentRadius += radiusStep;
            }
        }

        newPlan_radius = bestChoiceRadius;
        newPlan_grays = bestChoiceGrays;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "-------------------------------------------------" );
        }

        findRadarCircleRadius();
        if ( newPlan_radius != 0 && newPlan_grays > currentPlan_grays ) {
            double sledDelta = upperBound( Const.SLED_SPEED / newPlan_radius, Const.SLED_TURN_LIMIT );
            if ( Const.DEBUG_SLED ) {
                log.info( "sled", "DELTA", sledDelta );
            }
            responseManager.sledDirectionDelta = sledDelta;
            currentPlan_delta = sledDelta;
            currentPlan_grays = newPlan_grays;
        } else {
            responseManager.sledDirectionDelta = currentPlan_delta;
            currentPlan_grays = 0;
        }
    }

}
