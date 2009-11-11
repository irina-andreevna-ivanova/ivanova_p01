/*- 
F * Copyright Bogdan Mocanu, 2009
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

package ro.bmocanu.mobile.apps.netsnake.model;

import ro.bmocanu.mobile.apps.netsnake.GameConstants;

/**
 * The main character of the game. The class contains the properties of a snake, including the snake
 * position and the snake segments.
 * 
 * @author mocanu
 */
public class Snake {

    /**
     * The X coordinate of the snake's head.
     */
    private int x;

    /**
     * The Y coordinate of the snake's head.
     */
    private int y;

    /**
     * The length of each segment that composes the snake's body.
     */
    private int[] segmentLengths;

    /**
     * The direction of each segment.
     */
    private int[] segmentDirections;

    /**
     * The position in the <code>segmentLengths</code> and <code>segmentDirections</code> arrays
     * corresponding to the snake's head. The head will vary depending on the changes in the body of
     * the snake.
     */
    private int headIndex;

    // -------------------------------------------------------------------------------------------------

    /**
     * 
     */
    public Snake() {
        x = 5;
        y = 5;
        segmentLengths = new int[GameConstants.SNAKE_SEGMENTS_START];
        segmentDirections = new int[GameConstants.SNAKE_SEGMENTS_START];

        headIndex = 0;
        segmentLengths[0] = 50;
        segmentDirections[0] = 90;
    }

    // -------------------------------------------------------------------------------------------------

    public void move() {
        int[] directionModificators = SnakeUtils.getDirectionsModificators( segmentDirections[headIndex] );
        x = x + directionModificators[0];
        y = y + directionModificators[1];
        if ( headIndex > 0 ) {
            // we have more than one segment, so transfer the length
            segmentLengths[headIndex]++;
            segmentLengths[0]--;

            if ( segmentLengths[0] <= 0 ) {
                // last segment is finished, we need to delete it
                for ( int index = 0; index < headIndex; index++ ) {
                    segmentLengths[index] = segmentLengths[index + 1];
                    segmentDirections[index] = segmentDirections[index + 1];
                }

                headIndex--;
            }
        }
    }

    public void go( int newDirection ) {
        int currentDirection = getDirection();
        if ( currentDirection + newDirection == 180 || currentDirection + newDirection == 360 ) {
            // cannot rotate in that direction
            return;
        }

        headIndex++;
        segmentLengths[headIndex] = 0;
        segmentDirections[headIndex] = newDirection;
    }

    public SnakeTraverser traverser() {
        return new SnakeTraverser( x, y, segmentLengths, segmentDirections, headIndex );
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * Returns the x
     * 
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y
     * 
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the segmentLengths
     * 
     * @return the segmentLengths
     */
    public int[] getSegmentLengths() {
        return segmentLengths;
    }

    /**
     * Returns the segmentDirections
     * 
     * @return the segmentDirections
     */
    public int[] getSegmentDirections() {
        return segmentDirections;
    }

    /**
     * Returns the headIndex
     * 
     * @return the headIndex
     */
    public int getHeadIndex() {
        return headIndex;
    }

    /**
     * Returns the current direction of the snake.
     * 
     * @return
     */
    public int getDirection() {
        return segmentDirections[headIndex];
    }

}
