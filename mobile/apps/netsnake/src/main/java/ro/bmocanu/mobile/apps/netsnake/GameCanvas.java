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

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import ro.bmocanu.mobile.apps.netsnake.model.Snake;
import ro.bmocanu.mobile.apps.netsnake.model.SnakeConstants;

/**
 * The canvas on which the snakes will dance...
 * 
 * @author mocanu
 */
public class GameCanvas extends Canvas implements CommandListener, Runnable {

    private SnakeRenderer snakeRenderer = new SnakeRenderer();

    private Snake theSnake = new Snake();

    private Thread gameThread;
    
    private boolean dropDead = false;

    /**
     * 
     */
    public GameCanvas() {
        gameThread = new Thread( this );
        gameThread.start();
        
        this.setCommandListener( this );
    }

    /**
     * {@inheritDoc}
     */
    protected void paint( Graphics graph ) {
        graph.setColor( 0x00000000 );
        graph.fillRect( 0, 0, GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT );

        snakeRenderer.render( theSnake, graph );
    }

    /**
     * {@inheritDoc}
     */
    public void commandAction( Command command, Displayable disp ) {
    }

    /**
     * {@inheritDoc}
     */
    protected void keyPressed( int key ) {
        key = getGameAction( key );
        switch( key ) {
            case Canvas.LEFT: {
                theSnake.go( SnakeConstants.DIRECTION_LEFT );
                break;
            }
            case Canvas.RIGHT: {
                theSnake.go( SnakeConstants.DIRECTION_RIGHT );
                break;
            }
            case Canvas.UP: {
                theSnake.go( SnakeConstants.DIRECTION_UP );
                break;
            }
            case Canvas.DOWN: {
                theSnake.go( SnakeConstants.DIRECTION_DOWN );
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        while( !dropDead ) {
            try {
                Thread.sleep( 30 );
            } catch ( InterruptedException exception ) {
                exception.printStackTrace();
            }
            
            theSnake.move();
            repaint();
        }
    }

    /**
     * Sets the dropDead to the given value.
     *
     * @param dropDead the dropDead to set
     */
    public void setDropDead( boolean dropDead ) {
        this.dropDead = dropDead;

    }

    /**
     * @throws InterruptedException  
     * 
     */
    public void waitToDie() {
        try {
            gameThread.join();
        } catch ( InterruptedException exception ) {
            exception.printStackTrace();
        }
    }

}
