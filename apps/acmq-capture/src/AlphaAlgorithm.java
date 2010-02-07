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
 * A first version of the algorithm for the Diego player.
 * 
 * @author mocanu
 */
public class AlphaAlgorithm extends AbstractAlgorithm {

    private static final int STRATEGY_SWITCH_DIFFERENCE = 7;
    
    private AlphaSledAlgorithm sledAlgorithm;
    private AlphaBumperAlgorithm bumperAlgorithm;

    public AlphaStrategy strategy = AlphaStrategy.OFFENSE;
    
    // -------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        sledAlgorithm = new AlphaSledAlgorithm();
        sledAlgorithm.parentAlgorithm = this;
        sledAlgorithm.randomNumberGenerator = randomNumberGenerator;
        sledAlgorithm.stateManager = stateManager;
        sledAlgorithm.responseManager = responseManager;
        sledAlgorithm.log = log;
        sledAlgorithm.init();

        bumperAlgorithm = new AlphaBumperAlgorithm();
        bumperAlgorithm.parentAlgorithm = this;
        bumperAlgorithm.sledAlgorithm = sledAlgorithm;
        bumperAlgorithm.randomNumberGenerator = randomNumberGenerator;
        bumperAlgorithm.stateManager = stateManager;
        bumperAlgorithm.responseManager = responseManager;
        bumperAlgorithm.log = log;
        bumperAlgorithm.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        if ( Const.DEBUG_SLED || Const.DEBUG_BUMPER ) {
            log.info( "sled", "-------------------------------------------------" );
        }
        if ( Const.DEBUG_SLED ) {
            log.info( "sled", "Sled", "trail", stateManager.friendSled.trailLength, "FPucks", stateManager.friendPucksNr, "EPucks", stateManager.enemyPucksNr );
        }
        if ( Const.DEBUG_BUMPER ) {
            // no code here
        }
        analyzeNextStrategy();
        applyNextStrategy();
        sledAlgorithm.execute();
        bumperAlgorithm.execute();
    }
    
    // -------------------------------------------------------------------------------------------------

    private void analyzeNextStrategy() {
        if ( stateManager.enemyPucksNr == 0 ) {
            strategy = AlphaStrategy.OFFENSE;
        } else if ( stateManager.friendPucksNr - stateManager.enemyPucksNr < STRATEGY_SWITCH_DIFFERENCE ) {
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
            Const.PARAM_FPUCK_TO_BORDER_DISTANCE_LIMIT = 7;
            Const.PARAM_EPUCK_TO_BORDER_DISTANCE_LIMIT = 1;
            Const.PARAM_RADAR_CIRCLE_ABANDON_LIMIT = 1;
        } else {
            Const.PARAM_TRAIL_SAFE_LIMIT = 599;
            Const.PARAM_FPUCK_TO_BORDER_DISTANCE_LIMIT = 3;
            Const.PARAM_EPUCK_TO_BORDER_DISTANCE_LIMIT = 3;
            Const.PARAM_RADAR_CIRCLE_ABANDON_LIMIT = 2;
        }
    }

}
