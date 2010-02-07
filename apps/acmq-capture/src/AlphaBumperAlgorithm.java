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

/**
 * The Alpha algorithm implementation of the Diego player for the sled.
 * 
 * @author mocanu
 */
public class AlphaBumperAlgorithm extends AbstractAlgorithm {

    public AlphaAlgorithm parentAlgorithm;
    public AlphaSledAlgorithm sledAlgorithm;

    private AlphaBumperPlan bumperPlans[];

    private boolean caseOfCusteredPucks_detected = false;
    private RealPoint caseOfClusteredPucks_target = new RealPoint();

    private boolean caseOfEnemyPlan_detected = false;
    private RealPoint caseOfEnemyPlan_target = new RealPoint();

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        bumperPlans = new AlphaBumperPlan[Const.BUMPER_NR];
        for ( int bumperPlanIndex = 0; bumperPlanIndex < Const.BUMPER_NR; bumperPlanIndex++ ) {
            bumperPlans[bumperPlanIndex] = new AlphaBumperPlan();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        detectFriendlyPucksClustering( stateManager.friendBumpers[0] );
        detectEnemyPlanAndTargetPuck( stateManager.friendBumpers[1] );

        if ( bumperPlans[0].state == AlphaBumperState.READY ) {
            if ( caseOfCusteredPucks_detected ) {
                startBumperMovement( 0, caseOfClusteredPucks_target );
            } else {
                planBumperMovementToGrayPuck( 0 );
            }
        }

        if ( bumperPlans[1].state == AlphaBumperState.READY ) {
            if ( caseOfEnemyPlan_detected ) {
                startBumperMovement( 1, caseOfEnemyPlan_target );
            } else {
                planBumperMovementToGrayPuck( 1 );
            }
        }

        if ( bumperPlans[0].state != AlphaBumperState.READY ) {
            continueBumperMovement( 0 );
        }
        if ( bumperPlans[1].state != AlphaBumperState.READY ) {
            continueBumperMovement( 1 );
        }
    }

    // -------------------------------------------------------------------------------------------------

    private void startBumperMovement( int index, RealPoint target ) {
        AlphaBumperPlan plan = bumperPlans[index];
        if ( Const.DEBUG_BUMPER ) {
            log.info( "bumper", "LOCKED", target.x, target.y );
        }

        plan.state = AlphaBumperState.LOCKED_ON;
        plan.target.copyFrom( target );
    }

    private void continueBumperMovement( int index ) {
        AlphaBumperPlan plan = bumperPlans[index];
        Bumper bumper = stateManager.friendBumpers[index];
        RealPoint bumperAcceleration = responseManager.bumperAccelerations[index];

        if ( plan.state == AlphaBumperState.READY ) {
            bumperAcceleration.x = -bumper.velocity.x;
            bumperAcceleration.y = -bumper.velocity.y;
            return;
        }

        if ( plan.state == AlphaBumperState.LOCKED_ON && distance( bumper.coord, plan.target ) < 8 ) {
            if ( Const.DEBUG_BUMPER ) {
                log.info( "bumper", "STOPPING" );
            }
            plan.state = AlphaBumperState.STOPPING;
            plan.stopSequence = Const.BUMPER_MAX_STOP_SEQUENCE;
        }
        if ( plan.state == AlphaBumperState.STOPPING && Math.max( bumper.velocity.x, bumper.velocity.y ) <= 0.5 ) {
            if ( Const.DEBUG_BUMPER ) {
                log.info( "bumper", "STOPPING->READY" );
            }
            plan.state = AlphaBumperState.READY;
            bumperAcceleration.x = 0;
            bumperAcceleration.y = 0;
        }

        if ( plan.state == AlphaBumperState.READY ) {
            bumperAcceleration.x = -bumper.velocity.x;
            bumperAcceleration.y = -bumper.velocity.y;
        }
        if ( plan.state == AlphaBumperState.LOCKED_ON ) {
            bumperAcceleration.x = limit( plan.target.x - bumper.coord.x - bumper.velocity.x, -Const.BUMPER_ACCELLERATION_LIMIT, Const.BUMPER_ACCELLERATION_LIMIT );
            bumperAcceleration.y = limit( plan.target.y - bumper.coord.y - bumper.velocity.y, -Const.BUMPER_ACCELLERATION_LIMIT, Const.BUMPER_ACCELLERATION_LIMIT );
        }
        if ( plan.state == AlphaBumperState.STOPPING ) {
            plan.stopSequence--;
            if ( plan.stopSequence <= 0 ) {
                if ( Const.DEBUG_BUMPER ) {
                    log.info( "bumper", "STOPPING->READY (stopSeq)" );
                }
                plan.state = AlphaBumperState.READY;
                bumperAcceleration.x = 0;
                bumperAcceleration.y = 0;
            } else {
                bumperAcceleration.x = -bumper.velocity.x;
                bumperAcceleration.y = -bumper.velocity.y;
            }
        }
    }

    private void detectFriendlyPucksClustering( Bumper baseBumper ) {
        caseOfCusteredPucks_detected = false;
        int maxPucks = 0;
        double minDistanceToBase = Integer.MAX_VALUE;
        RealPoint cursorForTarget = new RealPoint();

        for ( int puckIndex1 = 0; puckIndex1 < stateManager.pucksNr - 1; puckIndex1++ ) {
            Puck currentPuck1 = stateManager.pucks[puckIndex1];
            if ( currentPuck1.type == PuckType.FRIEND ) {
                int nrOfClusteredPucks = 1;
                cursorForTarget.x = currentPuck1.coord.x;
                cursorForTarget.y = currentPuck1.coord.y;

                for ( int puckIndex2 = puckIndex1 + 1; puckIndex2 < stateManager.pucksNr; puckIndex2++ ) {
                    Puck currentPuck2 = stateManager.pucks[puckIndex2];
                    if ( currentPuck2.type == PuckType.FRIEND ) {
                        double currentDistance = distance( currentPuck1.coord, currentPuck2.coord );
                        if ( currentDistance < Const.PARAM_BUMPER_CLUSTERING_DISTANCE_LIMIT ) {
                            if ( nrOfClusteredPucks == 1 ) {
                                // first paired puck found
                                middleOf( currentPuck1.coord, currentPuck2.coord, cursorForTarget );
                            }
                            nrOfClusteredPucks++;
                        }
                    }
                }

                if ( nrOfClusteredPucks >= Const.PARAM_BUMPER_CLUSTERING_MINIMUM_NR && nrOfClusteredPucks >= maxPucks ) {
                    if ( Const.DEBUG_BUMPER ) {
                        log.info( "bumper", "Detected clustering, nrOfPucks:", nrOfClusteredPucks );
                    }
                    caseOfCusteredPucks_detected = true;
                    // we have a case of clustering
                    double currentDistance = distance( cursorForTarget, baseBumper.coord );
                    if ( nrOfClusteredPucks > maxPucks || currentDistance < minDistanceToBase ) {
                        maxPucks = nrOfClusteredPucks;
                        minDistanceToBase = currentDistance;
                        caseOfClusteredPucks_target.x = cursorForTarget.x;
                        caseOfClusteredPucks_target.y = cursorForTarget.y;
                    }
                }
            }
        }
    }

    private void detectEnemyPlanAndTargetPuck( Bumper baseBumper ) {
        caseOfEnemyPlan_detected = false;
        double minDistance = Double.MAX_VALUE;

        for ( int puckIndex = 0; puckIndex < stateManager.pucksNr; puckIndex++ ) {
            Puck currentPuck = stateManager.pucks[puckIndex];
            if ( currentPuck.type == PuckType.ENEMY ) {
                double distanceToSled = distance( currentPuck.coord, stateManager.enemySled.coord );
                if ( distanceToSled < Const.PARAM_BUMPER_ENEMY_PLAN_TARGET_PUCK_DISTANCE_LIMIT
                     && distanceToSled < minDistance ) {
                    if ( Const.DEBUG_BUMPER ) {
                        log.info( "bumper", "Detected ENEMY plan; distance", distanceToSled );
                    }
                    caseOfEnemyPlan_detected = true;
                    minDistance = distanceToSled;
                    caseOfEnemyPlan_target.x = currentPuck.coord.x;
                    caseOfEnemyPlan_target.y = currentPuck.coord.y;
                }
            }
        }
    }

    private void planBumperMovementToGrayPuck( int index ) {
        Bumper bumper = stateManager.friendBumpers[index];
        double minDistance = Double.MAX_VALUE;
        Puck targetPuck = null;

        for ( int puckIndex = 0; puckIndex < stateManager.pucksNr; puckIndex++ ) {
            Puck currentPuck = stateManager.pucks[puckIndex];
            if ( currentPuck.type == PuckType.GRAY ) {
                double distanceToPlanCenter = distance( currentPuck.coord, sledAlgorithm.currentPlan_center );
                if ( distanceToPlanCenter > Const.TRAIL_LIMIT_CIRCLE_RADIUS && ( index == 1 || distanceToPlanCenter < minDistance ) ) {
                    if ( Const.DEBUG_BUMPER ) {
                        log.info( "bumper", "Detected GRAY puck; distance", distanceToPlanCenter );
                    }
                    minDistance = distanceToPlanCenter;
                    targetPuck = currentPuck;
                }
            }
        }

        if ( targetPuck == null ) {
            stopBumper( index );
            return;
        }

        double angleOfBumperAndPuck = angleOf( bumper.coord, targetPuck.coord );
        double angleOfPuckAndCenter = angleOf( targetPuck.coord, sledAlgorithm.currentPlan_center );
        if ( Math.abs( angleOfPuckAndCenter - angleOfBumperAndPuck ) < 0.5 ) {
            // we are on the same line (or close) with the puck and the center of the plan
            planBumperMovement( index, targetPuck.coord );
        } else {
            // we are not on the same line, must get behind the puck
            RealPoint pointBehind = new RealPoint();
            pointBehind.x = (4 / 3F) * targetPuck.coord.x - sledAlgorithm.currentPlan_center.x;
            pointBehind.y = (4 / 3F) * targetPuck.coord.y - sledAlgorithm.currentPlan_center.y;

            // RealPoint closeToPointBehind = new RealPoint();
            // closeToPointBehind.x = limit( (3 / 4F) * (bumper.coord.x + pointBehind.x), 5,
            // Const.TABLE_SIZE - 5 );
            // closeToPointBehind.y = limit( (3 / 4F) * (bumper.coord.y + pointBehind.y), 5,
            // Const.TABLE_SIZE - 5 );
            planBumperMovement( index, pointBehind );
        }
    }

    private void stopBumper( int index ) {
        responseManager.bumperAccelerations[index].x = -stateManager.friendBumpers[index].velocity.x;
        responseManager.bumperAccelerations[index].y = -stateManager.friendBumpers[index].velocity.y;
    }

    private void planBumperMovement( int index, RealPoint targetPoint ) {
        Bumper bumper = stateManager.friendBumpers[index];
        responseManager.bumperAccelerations[index].x = limit( targetPoint.x - bumper.coord.x, -Const.BUMPER_ACCELLERATION_LIMIT, Const.BUMPER_ACCELLERATION_LIMIT );
        responseManager.bumperAccelerations[index].y = limit( targetPoint.y - bumper.coord.y, -Const.BUMPER_ACCELLERATION_LIMIT, Const.BUMPER_ACCELLERATION_LIMIT );
    }

}
