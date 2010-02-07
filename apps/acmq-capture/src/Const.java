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
 * @author mocanu
 */
public class Const {

    public static final int TABLE_SIZE = 800;

    public static final boolean DEBUG_EXCEPTIONS = false;
    public static final boolean DEBUG_SLED = false;
    public static final boolean DEBUG_BUMPER = false;
    public static final boolean DEBUG_ANY = DEBUG_EXCEPTIONS || DEBUG_SLED || DEBUG_BUMPER;

    public static final int TRAIL_LIMIT = 600;
    public static final double TRAIL_LIMIT_CIRCLE_RADIUS = 600 / ( 2 * Math.PI );

    /** Maximum turn angle for a sled. */
    public static final double SLED_TURN_LIMIT = 0.5;
    public static final double SLED_SPEED = 15;
    
    public static final int BUMPER_NR = 2;
    public static final double BUMPER_ACCELLERATION_LIMIT = 8;
    public static final int BUMPER_MAX_STOP_SEQUENCE = 10;

    public static int PARAM_TRAIL_SAFE_LIMIT = 598;
    public static int PARAM_FPUCK_TO_BORDER_DISTANCE_LIMIT = 3;
    public static int PARAM_EPUCK_TO_BORDER_DISTANCE_LIMIT = 3;
    public static int PARAM_RADAR_CIRCLE_ABANDON_LIMIT = 2;

    public static int PARAM_BUMPER_CLUSTERING_DISTANCE_LIMIT = 50;
    public static int PARAM_BUMPER_CLUSTERING_MINIMUM_NR = 4;
    public static double PARAM_BUMPER_ENEMY_PLAN_TARGET_PUCK_DISTANCE_LIMIT = TRAIL_LIMIT / Math.PI;
}
