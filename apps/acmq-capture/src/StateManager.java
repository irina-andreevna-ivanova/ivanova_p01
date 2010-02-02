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

    public int pucksNr;
    public Puck[] pucks;

    public Bumper[] friendBumpers = { new Bumper(), new Bumper() };
    public Bumper[] enemyBumpers = { new Bumper(), new Bumper() };

    public Sled friendSled = new Sled();
    public Sled enemySled = new Sled();

    // Calculated fields
    // -------------------------------------------------------------------------------

    public int friendPucks;
    public int enemyPucks;

    // -------------------------------------------------------------------------------------------------

    public void readState() {
        // 0. reset the calculated fields
        friendPucks = 0;
        enemyPucks = 0;

        // 1. read the turn number. If < 0, game is over ------------------------------
        turnNumber = stateScanner.nextInt();
        if ( turnNumber < 0 ) {
            // game is finished
            return;
        }

        // 2. read the pucks -----------------------------------------------------------
        int puckIndex = 0;
        pucksNr = stateScanner.nextInt();
        if ( pucks == null ) {
            pucks = new Puck[pucksNr * 5]; // also count for bordering
            for ( puckIndex = 0; puckIndex < pucks.length; puckIndex++ ) {
                pucks[puckIndex] = new Puck();
            }
        }

        for ( puckIndex = 0; puckIndex < pucksNr; puckIndex++ ) {
            readPuckState( pucks[puckIndex] );
            applyBordering( puckIndex );
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

    /**
     * @param pucks2
     * @param puckIndex
     */
    private void applyBordering( int puckIndex ) {
        Puck currentPuck = pucks[puckIndex];

        pucks[pucksNr * 1 + puckIndex].coord.x = currentPuck.coord.x + Const.TABLE_SIZE;
        pucks[pucksNr * 1 + puckIndex].coord.y = currentPuck.coord.y;
        pucks[pucksNr * 1 + puckIndex].type = currentPuck.type;
        pucks[pucksNr * 1 + puckIndex].velocity.x = currentPuck.velocity.x;
        pucks[pucksNr * 1 + puckIndex].velocity.y = currentPuck.velocity.y;

        pucks[pucksNr * 2 + puckIndex].coord.x = currentPuck.coord.x;
        pucks[pucksNr * 2 + puckIndex].coord.y = currentPuck.coord.y - Const.TABLE_SIZE;
        pucks[pucksNr * 2 + puckIndex].type = currentPuck.type;
        pucks[pucksNr * 2 + puckIndex].velocity.x = currentPuck.velocity.x;
        pucks[pucksNr * 2 + puckIndex].velocity.y = currentPuck.velocity.y;

        pucks[pucksNr * 3 + puckIndex].coord.x = currentPuck.coord.x - Const.TABLE_SIZE;
        pucks[pucksNr * 3 + puckIndex].coord.y = currentPuck.coord.y;
        pucks[pucksNr * 3 + puckIndex].type = currentPuck.type;
        pucks[pucksNr * 3 + puckIndex].velocity.x = currentPuck.velocity.x;
        pucks[pucksNr * 3 + puckIndex].velocity.y = currentPuck.velocity.y;

        pucks[pucksNr * 4 + puckIndex].coord.x = currentPuck.coord.x;
        pucks[pucksNr * 4 + puckIndex].coord.y = currentPuck.coord.y + Const.TABLE_SIZE;
        pucks[pucksNr * 4 + puckIndex].type = currentPuck.type;
        pucks[pucksNr * 4 + puckIndex].velocity.x = currentPuck.velocity.x;
        pucks[pucksNr * 4 + puckIndex].velocity.y = currentPuck.velocity.y;
    }

    private void readPuckState( Puck currentPuck ) {
        currentPuck.coord.x = stateScanner.nextDouble();
        currentPuck.coord.y = stateScanner.nextDouble();
        currentPuck.velocity.x = stateScanner.nextDouble();
        currentPuck.velocity.y = stateScanner.nextDouble();
        currentPuck.type = PuckType.values()[stateScanner.nextInt()];

        switch ( currentPuck.type ) {
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
