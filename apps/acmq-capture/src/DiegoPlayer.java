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

import java.io.IOException;
import java.util.Random;

/**
 * Main class of the Diego player.
 * 
 * @author mocanu
 * @uml.dependency supplier="ResponseManager"
 */
public class DiegoPlayer {

    public static void main( String[] args ) {
        // 1. create the main components of the player -------------------------------
        Random randomNumberGenerator = new Random();
        StateManager stateManager = new StateManager();
        ResponseManager responseManager = new ResponseManager();

        FileLogger logger = new FileLogger();
        if ( Const.DEBUG_ANY ) {
            try {
                logger.init();
            } catch ( IOException exception ) {
                exception.printStackTrace();
            }
        }

        // 2. inject the dependencies -------------------------------------------------
        AbstractAlgorithm algorithm = new AlphaAlgorithm();
        algorithm.randomNumberGenerator = randomNumberGenerator;
        algorithm.stateManager = stateManager;
        algorithm.responseManager = responseManager;
        algorithm.log = logger;

        algorithm.init();

        try {
            stateManager.readState();

            // 3. start the main loop -------------------------------------------------------
            while ( stateManager.turnNumber >= 0 ) {
                algorithm.execute();
                responseManager.sendResponse();
                stateManager.readState();
            }
        } catch ( Throwable exception ) {
            if ( Const.DEBUG_EXCEPTIONS ) {
                logger.logException( exception );
            }
        }

        // 4. cleanup the resources ------------------------------------------------------
        if ( Const.DEBUG_ANY ) {
            logger.close();
        }
    }

}
