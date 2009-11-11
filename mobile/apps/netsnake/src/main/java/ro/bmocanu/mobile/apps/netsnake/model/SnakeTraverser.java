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

package ro.bmocanu.mobile.apps.netsnake.model;

/**
 * Class that is similar to the <code>java.util.Iterator</code> from JDK. It allows classes that are
 * interested in traversing the body of the snake (for collision computing or for rendering) to
 * iterate through each point of the snake.
 * 
 * @author mocanu
 */
public class SnakeTraverser {

    private int[] segmentLengths;
    private int[] segmentDirections;

    private int currentSegmentIndex;
    private int currentPositionInSegment;

    private int currentX;
    private int currentY;
    
    private boolean traverserInitialized;

    /**
     * 
     */
    public SnakeTraverser(int startX, int startY, int[] segmentLengths, int[] segmentDirections, int headIndex) {
        this.segmentLengths = segmentLengths;
        this.segmentDirections = segmentDirections;
        this.currentSegmentIndex = headIndex;
        this.currentPositionInSegment = segmentLengths[currentSegmentIndex];
        this.currentX = startX;
        this.currentY = startY;
        this.traverserInitialized = false;
    }

    public int getX() {
        return currentX;
    }

    public int getY() {
        return currentY;
    }

    public void next() {
        if ( !traverserInitialized ) {
            traverserInitialized = true;
            return;
        }
        
        if ( hasNext() ) {
            int[] directionModificators = SnakeUtils.getDirectionsModificators( segmentDirections[currentSegmentIndex] );
            currentX -= directionModificators[0];
            currentY -= directionModificators[1];
            
            currentPositionInSegment--;
            if ( currentPositionInSegment <= 0 ) {
                if ( currentSegmentIndex > 0 ) {
                    currentSegmentIndex--;
                    currentPositionInSegment = segmentLengths[currentSegmentIndex];
                }
            }
        }
    }

    public boolean hasNext() {
        return currentPositionInSegment > 0 || currentSegmentIndex > 0;
    }

}
