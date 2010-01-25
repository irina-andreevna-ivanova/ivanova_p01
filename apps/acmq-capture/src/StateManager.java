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
    
    public int turnNumber;
    
    public Puck[] pucks;
    
    public Bumper[] friendBumpers;
    
    public Bumper[] enemyBumpers;
    
    public Sled friendSled;
    
    public Sled enemySled;
    
}
