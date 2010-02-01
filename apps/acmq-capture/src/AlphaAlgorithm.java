import static java.lang.Math.floor;

import java.util.Random;

/**
 * A first version of the algorithm for the Diego player.
 * 
 * @author mocanu
 */
public class AlphaAlgorithm extends AbstractAlgorithm {

    private Random randomNumberGenerator = new Random();

    private double currentPlan_delta = 0;
    private int currentPlan_grays = 0;
    private double currentPlan_radius = 0;

    private int newPlan_grays = 0;
    private double newPlan_radius = 0;

    private AlphaStrategy strategy = AlphaStrategy.DEFENSE;

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "-------------------------------------------------" );
            log.info( "sled", "Sled", "trail", stateManager.friendSled.trailLength, "FPucks", stateManager.friendPucks, "EPucks", stateManager.enemyPucks );
        }
        analyzeNextStrategy();
        applyNextStrategy();
        findRadarCircleRadius();

        if ( stateManager.friendSled.trailLength < Const.PARAM_TRAIL_RESET_LIMIT ) {
            resetCurrentPlan();
        }

        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "CurrentPlan", "grays", currentPlan_grays, "rad", currentPlan_radius );
            log.info( "sled", "NewPlan", "grays", newPlan_grays, "rad", newPlan_radius );
        }

        if ( newPlan_radius != 0 ) {
            // ok, we have a possible new plan
            // -------------------------------------------------------------------------------------------------
            if ( strategy == AlphaStrategy.OFFENSE ) {
                if ( newPlan_grays > currentPlan_grays + 1 ) {
                    // the new plan is better than the old one
                    adoptNewPlan();
                } else if ( newPlan_grays == currentPlan_grays ) {
                    // the new plan is just the same
                    if ( newPlanHasBiggerRadius() ) {
                        // let's favor this one
                        adoptNewPlan();
                    } else {
                        // keep the old plan
                    }
                }
            }
            // -------------------------------------------------------------------------------------------------
            if ( strategy == AlphaStrategy.DEFENSE ) {
                if ( newPlan_grays > currentPlan_grays ) {
                    // the new plan is just better than the old one
                    adoptNewPlan();
                } else if ( newPlan_grays == currentPlan_grays ) {
                    // the new plan is just the same
                    if ( newPlanHasBiggerRadius() ) {
                        // let's favor this one
                        adoptNewPlan();
                    } else {
                        // keep the old plan
                    }
                }
            }
            // -------------------------------------------------------------------------------------------------
        } else {
            // no new plan; stick to the old one
            // well, if the trail is that short, just keep wandering on the table
            currentPlan_delta = (Const.SLED_TURN_LIMIT / 2) * (randomNumberGenerator.nextInt( 50 ) > 25 ? 1 : -1);
            currentPlan_grays = 0;
        }

        responseManager.sledDirectionDelta = currentPlan_delta;
    }

    // ------------------------------------------------------------------------------------------------------

    private void analyzeNextStrategy() {
        if ( stateManager.enemyPucks == 0 ) {
            strategy = AlphaStrategy.OFFENSE;
        } else if ( ((double) stateManager.friendPucks) / stateManager.enemyPucks < 1.5 ) {
            // from half points on, we switch to DEFENSE
            if ( Const.DEBUG_SLED && strategy != AlphaStrategy.DEFENSE ) {
                log.info( "sled", "Switching to DEFENSE" );
            }
            strategy = AlphaStrategy.DEFENSE;
        } else {
            if ( Const.DEBUG_SLED && strategy != AlphaStrategy.OFFENSE ) {
                log.info( "sled", "Switching to DEFENSE" );
            }
            strategy = AlphaStrategy.OFFENSE;
        }
    }

    private void applyNextStrategy() {
        if ( strategy == AlphaStrategy.DEFENSE ) {
            Const.PARAM_TRAIL_SAFE_LIMIT = 570;
            Const.PARAM_TRAIL_RESET_LIMIT = 5;
            Const.PARAM_FPUCK_TO_BORDER_DISTANCE_LIMIT = 7;
            Const.PARAM_EPUCK_TO_BORDER_DISTANCE_LIMIT = 1;
        } else {
            Const.PARAM_TRAIL_SAFE_LIMIT = 599;
            Const.PARAM_TRAIL_RESET_LIMIT = 10;
            Const.PARAM_FPUCK_TO_BORDER_DISTANCE_LIMIT = 3;
            Const.PARAM_EPUCK_TO_BORDER_DISTANCE_LIMIT = 3;
        }
    }

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
        double maxRadius = Const.PARAM_TRAIL_SAFE_LIMIT / TWO_PI;
        double radiusStep = 5;
        double reducedSledAngle = sled.direction - floor( sled.direction / TWO_PI ) * TWO_PI;
        RealPoint circleCenter = new RealPoint();
        double bestChoiceRadius = 0;
        int bestChoiceGrays = 0;
        int[] sidesForRadar = new int[] { 1, -1 };

        for ( int sideIndex = 0; sideIndex < sidesForRadar.length; sideIndex++ ) {
            double currentRadius = radiusStep;
            while ( currentRadius <= maxRadius ) {
                movePointAt90degreesAndDistance( circleCenter, sled.coord, reducedSledAngle, currentRadius, sidesForRadar[sideIndex] );

                // compute the situation of pucks inside the circle
                int grayPucks = 0;
                int friendPucks = 0;
                int enemyPucks = 0;

                for ( Puck puck : stateManager.pucks ) {
                    switch ( puck.type ) {
                        case GRAY: {
                            if ( distance( puck.coord, circleCenter ) < currentRadius
                                                                        - Const.PARAM_FPUCK_TO_BORDER_DISTANCE_LIMIT ) {
                                grayPucks++;
                            }
                            break;
                        }
                        case FRIEND: {
                            if ( distance( puck.coord, circleCenter ) < currentRadius
                                                                        - Const.PARAM_FPUCK_TO_BORDER_DISTANCE_LIMIT ) {
                                friendPucks++;
                            }
                            break;
                        }
                        case ENEMY: {
                            if ( distance( puck.coord, circleCenter ) < currentRadius
                                                                        - Const.PARAM_EPUCK_TO_BORDER_DISTANCE_LIMIT ) {
                                enemyPucks++;
                            }
                            break;
                        }
                    }
                }

                if ( enemyPucks == 0 && grayPucks >= bestChoiceGrays && friendPucks >= 1 ) {
                    bestChoiceGrays = grayPucks;
                    bestChoiceRadius = currentRadius * sidesForRadar[sideIndex];
                }

                currentRadius += radiusStep;
            }
        }

        newPlan_radius = bestChoiceRadius;
        newPlan_grays = bestChoiceGrays;
    }

    private void adoptNewPlan() {
        double sledDelta = upperBound( Const.SLED_SPEED / newPlan_radius, Const.SLED_TURN_LIMIT );
        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "DELTA", sledDelta );
        }

        currentPlan_delta = sledDelta;
        currentPlan_radius = newPlan_radius;
        currentPlan_grays = newPlan_grays;
    }

    private void resetCurrentPlan() {
        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "Reset current plan" );
        }

        currentPlan_delta = 0;
        currentPlan_grays = 0;
        currentPlan_radius = 0;
    }

    private boolean newPlanHasBiggerRadius() {
        return Math.abs( newPlan_radius ) > Math.abs( currentPlan_radius );
    }

}
