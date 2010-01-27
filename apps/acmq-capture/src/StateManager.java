import java.util.Scanner;

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
 * Class responsible with keeping the current state of the game, as reported by the Capture game
 * engine.
 * 
 * @author mocanu
 */
public class StateManager {

    private Scanner stateScanner = new Scanner( System.in );

    public int turnNumber;

    public Puck[] pucks;

    public Bumper[] friendBumpers = { new Bumper(), new Bumper() };
    public Bumper[] enemyBumpers = { new Bumper(), new Bumper() };

    public Sled friendSled = new Sled();
    public Sled enemySled = new Sled();

    // -------------------------------------------------------------------------------------------------

    public void readState() {
        // 1. read the turn number. If < 0, game is over ------------------------------
        turnNumber = stateScanner.nextInt();
        if ( turnNumber < 0 ) {
            // game is finished
            return;
        }

        // 2. read the pucks -----------------------------------------------------------
        int puckIndex = 0;
        int nrOfPucks = stateScanner.nextInt();
        if ( pucks == null ) {
            pucks = new Puck[nrOfPucks];
            for ( puckIndex = 0; puckIndex < nrOfPucks; puckIndex++ ) {
                pucks[puckIndex] = new Puck();
            }
        }

        for ( puckIndex = 0; puckIndex < nrOfPucks; puckIndex++ ) {
            readPuckState( pucks[puckIndex] );
        }

        // 3. read the bumpers ----------------------------------------------------------
        stateScanner.nextInt(); // nrOfBumpers - always 4
        int bumperIndex = 0;

        for ( bumperIndex = 0; bumperIndex < friendBumpers.length; bumperIndex++ ) {
            readBumperState( friendBumpers[bumperIndex] );
        }
        for ( bumperIndex = 0; bumperIndex < enemyBumpers.length; bumperIndex++ ) {
            readBumperState( enemyBumpers[bumperIndex] );
        }

        // 4. read the sleds -------------------------------------------------------------
        stateScanner.nextInt(); // nrOfSleds - always 2
        readSledState( friendSled );
        readSledState( enemySled );
    }

    private void readPuckState( Puck currentPuck ) {
        currentPuck.coord.x = stateScanner.nextDouble();
        currentPuck.coord.y = stateScanner.nextDouble();
        currentPuck.velocity.x = stateScanner.nextDouble();
        currentPuck.velocity.y = stateScanner.nextDouble();
        currentPuck.type = PuckType.values()[stateScanner.nextInt()];
    }

    private void readBumperState( Bumper currentBumper ) {
        currentBumper.coord.x = stateScanner.nextDouble();
        currentBumper.coord.y = stateScanner.nextDouble();
        currentBumper.velocity.x = stateScanner.nextDouble();
        currentBumper.velocity.y = stateScanner.nextDouble();
    }

    private void readSledState( Sled currentSled ) {
        // read the sled properties
        currentSled.coord.x = stateScanner.nextDouble();
        currentSled.coord.y = stateScanner.nextDouble();
        currentSled.direction = stateScanner.nextDouble();

        // read the trail coordinates
        currentSled.trailLength = stateScanner.nextInt();
        for ( int trailPointIndex = 0; trailPointIndex < currentSled.trailLength; trailPointIndex++ ) {
            currentSled.trail[trailPointIndex].coord.x = stateScanner.nextDouble();
            currentSled.trail[trailPointIndex].coord.y = stateScanner.nextDouble();
        }
    }
    
    // -------------------------------------------------------------------------------------------------

}
