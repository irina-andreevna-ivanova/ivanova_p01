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

package ro.bmocanu.mobile.apps.netsnake;

import javax.microedition.lcdui.Graphics;

import ro.bmocanu.mobile.apps.netsnake.model.Snake;
import ro.bmocanu.mobile.apps.netsnake.model.SnakeTraverser;

/**
 * Class that is responsible with rendering a snake on the canvas.
 * 
 * @author mocanu
 */
public class SnakeRenderer {

    int colors[] = new int[] { 0x00909090, 0x00BBBBBB, 0x00FFFFFF };

    public void render( Snake snake, Graphics graph ) {
        for ( int index = 0; index < 3; index++ ) {
            SnakeTraverser traverser = snake.traverser();
            while ( traverser.hasNext() ) {
                traverser.next();
                int x = traverser.getX();
                int y = traverser.getY();
                drawSnakePoint( x, y, index * 2, colors[index], graph );
            }
        }
    }

    private void drawSnakePoint( int x, int y, int offset, int color, Graphics graph ) {
        graph.setColor( color );
        graph.fillRoundRect( x + offset, y + offset, 8 - offset * 2, 8 - offset * 2, 8 - offset * 2, 8 - offset * 2 );
    }

}
