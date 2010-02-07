/*- 
 * Copyright Bogdan Mocanu, 2009
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

import static java.lang.Math.floor;

/**
 * The Alpha algorithm implementation of the Diego player for the sled.
 * 
 * @author mocanu
 */
public class AlphaSledAlgorithm extends AbstractAlgorithm {

    public AlphaAlgorithm parentAlgorithm;

    private int planAbandonCount = -1;

    private boolean currentPlan_wandering_lockedOn = false;
    private double currentPlan_delta = 0;
    private int currentPlan_grays = 0;
    private double currentPlan_radius = 0;
    public RealPoint currentPlan_center = new RealPoint();
    private RealPoint currentPlan_startCoord = new RealPoint( Integer.MAX_VALUE, Integer.MAX_VALUE );

    private int newPlan_grays = 0;
    private double newPlan_radius = 0;
    private RealPoint newPlan_center = new RealPoint();

    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        findRadarCircleRadius();
        reAnalyzeCurrentPlan();

        if ( distance( currentPlan_startCoord, stateManager.friendSled.coord ) < 5 ) {
            // we created a closed area
            if ( Const.DEBUG_SLED ) {
                log.info( "sled", "BOOM! Closed area!" );
            }
            resetCurrentPlan();
        }

        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "CurrentPlan", "grays", currentPlan_grays, "rad", currentPlan_radius );
            log.info( "sled", "NewPlan", "grays", newPlan_grays, "rad", newPlan_radius );
        }

        if ( newPlan_radius != 0 && planAbandonCount < Const.PARAM_RADAR_CIRCLE_ABANDON_LIMIT ) {
            // ok, we have a possible new plan
            // -------------------------------------------------------------------------------------------------
            if ( parentAlgorithm.strategy == AlphaStrategy.OFFENSE ) {
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
            if ( parentAlgorithm.strategy == AlphaStrategy.DEFENSE ) {
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
        } else if ( newPlan_radius == 0 ) {
            // well, if we don't have a plan, just keep wandering on the table
            if ( !currentPlan_wandering_lockedOn ) {
                computeWanderingTarget();
                currentPlan_grays = 0;
                planAbandonCount = -1;
            }
        }

        responseManager.sledDirectionDelta = currentPlan_delta;
    }

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
        double maxRadius = Const.PARAM_TRAIL_SAFE_LIMIT / TWO_PI;
        double radiusStep = 5;
        double reducedSledAngle = sled.direction - floor( sled.direction / TWO_PI ) * TWO_PI;
        RealPoint circleCenter = new RealPoint();
        double bestChoiceRadius = 0;
        int bestChoiceGrays = 0;
        RealPoint bestChoiceCenter = new RealPoint();
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
                if ( enemyPucks == 0 && grayPucks > bestChoiceGrays && friendPucks >= 1 ) {
                    bestChoiceGrays = grayPucks;
                    bestChoiceRadius = currentRadius * sidesForRadar[sideIndex];
                    bestChoiceCenter.x = circleCenter.x;
                    bestChoiceCenter.y = circleCenter.y;
                }
                currentRadius += radiusStep;
            }
        }

        newPlan_radius = bestChoiceRadius;
        newPlan_grays = bestChoiceGrays;
        newPlan_center.x = bestChoiceCenter.x;
        newPlan_center.y = bestChoiceCenter.y;
    }

    private void reAnalyzeCurrentPlan() {
        for ( int puckIndex = 0; puckIndex < stateManager.pucks.length; puckIndex++ ) {
            Puck currentPuck = stateManager.pucks[puckIndex];
            int grayPucks = 0;
            int friendPucks = 0;
            int enemyPucks = 0;

            switch ( currentPuck.type ) {
                case GRAY: {
                    if ( distance( currentPuck.coord, currentPlan_center ) < currentPlan_radius
                                                                             - Const.PARAM_FPUCK_TO_BORDER_DISTANCE_LIMIT ) {
                        grayPucks++;
                    }
                    break;
                }
                case FRIEND: {
                    if ( distance( currentPuck.coord, currentPlan_center ) < currentPlan_radius
                                                                             - Const.PARAM_FPUCK_TO_BORDER_DISTANCE_LIMIT ) {
                        friendPucks++;
                    }
                    break;
                }
                case ENEMY: {
                    if ( distance( currentPuck.coord, currentPlan_center ) < currentPlan_radius
                                                                             - 1
                                                                             - Const.PARAM_EPUCK_TO_BORDER_DISTANCE_LIMIT ) {
                        enemyPucks++;
                    }
                    break;
                }
            }
            
            if ( enemyPucks > 0 || friendPucks <= 0 ) {
                currentPlan_grays = -1;
            } else {
                currentPlan_grays = grayPucks;
            }
        }
    }

    private void adoptNewPlan() {
        planAbandonCount++;

        double sledDelta = upperBound( Const.SLED_SPEED / newPlan_radius, Const.SLED_TURN_LIMIT );
        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "DELTA", sledDelta );
        }

        currentPlan_wandering_lockedOn = false;
        currentPlan_startCoord.x = stateManager.friendSled.coord.x;
        currentPlan_startCoord.y = stateManager.friendSled.coord.y;
        currentPlan_delta = sledDelta;
        currentPlan_radius = newPlan_radius;
        currentPlan_grays = newPlan_grays;
        currentPlan_center.x = newPlan_center.x;
        currentPlan_center.y = newPlan_center.y;
    }

    private void resetCurrentPlan() {
        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "Reset current plan" );
        }

        planAbandonCount = -1;
        currentPlan_delta = 0;
        currentPlan_grays = 0;
        currentPlan_radius = 0;
    }

    private boolean newPlanHasBiggerRadius() {
        return Math.abs( newPlan_radius ) > Math.abs( currentPlan_radius );
    }

    private void computeWanderingTarget() {
        // get the most distanced friend puck
        double maxDistance = 0;
        Puck maxPuck = null;

        for ( int index = 0; index < stateManager.pucksNr; index++ ) {
            Puck currentPuck = stateManager.pucks[index];
            if ( currentPuck.type == PuckType.FRIEND ) {
                double currentDistance = distance( currentPuck.coord, stateManager.friendSled.coord );
                if ( currentDistance > maxDistance ) {
                    maxDistance = currentDistance;
                    maxPuck = currentPuck;
                }
            }
        }

        if ( maxPuck != null ) {
            // get the angle needed to get there
            double targetAngle = angleOf( maxPuck.coord, stateManager.friendSled.coord );
            double reducedSledAngle = stateManager.friendSled.direction
                                      - floor( stateManager.friendSled.direction / TWO_PI ) * TWO_PI;

            if ( targetAngle < reducedSledAngle ) {
                currentPlan_delta = -upperBound( reducedSledAngle - targetAngle, Const.SLED_TURN_LIMIT );
            } else {
                currentPlan_delta = upperBound( targetAngle - reducedSledAngle, Const.SLED_TURN_LIMIT );
            }

            currentPlan_wandering_lockedOn = true;
        } else {
            currentPlan_delta = Const.SLED_TURN_LIMIT * (randomNumberGenerator.nextInt( 20 ) < 14 ? 1 : -1);
        }
    }

}
