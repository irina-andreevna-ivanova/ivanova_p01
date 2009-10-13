/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * GameCanvas
 * 
 * Author : Bogdan Mocanu
 * 
 * Created : 27.08.2006
 *
 * Version : 1.0.0
 *
 * Copyright (C) Bogdan Mocanu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
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
package ro.bmocanu.mobile.apps.metris.game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import ro.bmocanu.mobile.apps.metris.ImageRepository;
import ro.bmocanu.mobile.apps.metris.MetrisConstants;

/**
 * Class <code>GameCanvas</code>
 */
public class GameCanvas extends Canvas implements CommandListener {
    
    private GameKernel gameKernel;
    
    private Font scoreFont;
    
    private Font speedFont;
    
    private Image gameBoardImage;
    
    private Command pauseCommand = new Command( "Pause", Command.SCREEN, 3 );

    private Command restartCommand = new Command( "Restart", Command.SCREEN, 2 );

    private Command quitCommand = new Command( "Quit", Command.BACK, 1 );

    /**
     * Creates a new instance of <code>GameCanvas</code>
     * setting the object's attributes to the given value(s).
     *
     * @param gameKernel
     */
    public GameCanvas( GameKernel gameKernel ) {
        this.gameKernel = gameKernel;
        this.scoreFont = Font.getFont( Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_SMALL );
        this.speedFont = Font.getFont( Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM );
        
        gameBoardImage = Image.createImage( MetrisConstants.GAME_BOARD_WIDTH_PX, MetrisConstants.GAME_BOARD_HEIGHT_PX ); 
        repaintGameBoard();
        
        this.addCommand( pauseCommand );
//        this.addCommand( restartCommand );
        this.addCommand( quitCommand );
        this.setCommandListener( this );
    }
    
    public void repaintGameBoard() {
        Graphics imageGraphics = gameBoardImage.getGraphics();
        imageGraphics.setColor( 0, 0, 0 );
        imageGraphics.fillRect( 0, 0, gameBoardImage.getWidth(), gameBoardImage.getHeight() );
        
        int tableBlocks[][] = gameKernel.getGameTable().getTableBlocks();
        for( int lin = 0; lin < tableBlocks.length; lin++ ) {
            for( int col = 0; col < tableBlocks[ 0 ].length; col++ ) {
                if ( tableBlocks[ lin ][ col ] != GameTable.EMPTY ) {
                    imageGraphics.drawImage( ImageRepository.pieceImages[ tableBlocks[ lin ][ col ] ], 
                                             ( col ) * ( MetrisConstants.GAME_BLOCK_WIDTH + MetrisConstants.GAME_BLOCK_SPACING ), 
                                             ( lin ) * ( MetrisConstants.GAME_BLOCK_HEIGHT + MetrisConstants.GAME_BLOCK_SPACING ), 
                                             Graphics.TOP | Graphics.LEFT ); 
                }
            }
        }
    }
    
    protected void paintTheGameBoard( Graphics graph ) {
        graph.drawImage( gameBoardImage, 
                         MetrisConstants.SCREEN_OFFSET_GAMEBOARD_LEFT,
                         MetrisConstants.SCREEN_OFFSET_GAMEBOARD_TOP,
                         Graphics.TOP | Graphics.LEFT );
    }
    
    protected void paintTheFallingPiece( Graphics graph ) {
        GamePiece currentPiece = gameKernel.getCurrentPiece();
        if ( currentPiece != null ) {
            int pieceBlocks[][] = currentPiece.getBlocks();
            for( int lin = 0; lin < pieceBlocks.length; lin++ ) {
                for( int col = 0; col < pieceBlocks[ 0 ].length; col++ ) {
                    if ( pieceBlocks[ lin ][ col ] == 1 ) {
                        graph.drawImage( ImageRepository.pieceImages[ currentPiece.getType() ], 
                                         MetrisConstants.SCREEN_OFFSET_GAMEBOARD_LEFT + ( currentPiece.getLeft() + col ) * ( MetrisConstants.GAME_BLOCK_WIDTH + MetrisConstants.GAME_BLOCK_SPACING ), 
                                         MetrisConstants.SCREEN_OFFSET_GAMEBOARD_TOP + ( currentPiece.getTop() + lin ) * ( MetrisConstants.GAME_BLOCK_HEIGHT + MetrisConstants.GAME_BLOCK_SPACING ), 
                                         Graphics.TOP | Graphics.LEFT ); 
                    }
                }
            }
        }
    }
    
    /**
     * @see javax.microedition.lcdui.Canvas#paint(javax.microedition.lcdui.Graphics)
     */
    protected void paint( Graphics graph ) {
        if ( graph.getClipX() > 0 ) {
            // paint only the game board and the falling piece
            paintTheGameBoard( graph );
            paintTheFallingPiece( graph );
            return;
        }
        
        graph.drawImage( ImageRepository.tableImage, 0, 0, Graphics.TOP | Graphics.LEFT );
        GamePiece nextPiece = gameKernel.getNextPiece();
        
        // 1. paint the game board
        paintTheGameBoard( graph );
        
        // 2. repaint the falling piece
        paintTheFallingPiece( graph );
        
        // 3. draw the score and next level score texts
        graph.setFont( scoreFont );
        graph.setColor( MetrisConstants.COLOR_SCORE );
        graph.drawString( String.valueOf( gameKernel.getScore() ), 
                          MetrisConstants.SCREEN_OFFSET_SCORE_LEFT, 
                          MetrisConstants.SCREEN_OFFSET_SCORE_TOP, 
                          Graphics.RIGHT | Graphics.TOP );
        
        graph.setColor( MetrisConstants.COLOR_NEXTLVLSCORE );
        graph.drawString( String.valueOf( gameKernel.getNextLevelScore() ), 
                          MetrisConstants.SCREEN_OFFSET_NEXTLVLSCORE_LEFT, 
                          MetrisConstants.SCREEN_OFFSET_NEXTLVLSCORE_TOP, 
                          Graphics.RIGHT | Graphics.TOP );
        
        // 4. draw the speed gauge
        graph.drawRegion( ImageRepository.speedGaugeImage, 
                          0, 
                          ( MetrisConstants.GAME_SPEED_MAX - gameKernel.getSpeed() ) * 4, 
                          ImageRepository.speedGaugeImage.getWidth(), 
                          gameKernel.getSpeed() * 4, 
                          Sprite.TRANS_NONE, 
                          MetrisConstants.SCREEN_OFFSET_SPEEDGAUGE_LEFT, 
                          MetrisConstants.SCREEN_OFFSET_SPEEDGAUGE_TOP + ( MetrisConstants.GAME_SPEED_MAX - gameKernel.getSpeed() ) * 4, 
                          Graphics.LEFT + Graphics.TOP );
        
        // 5. draw the speed digit
        graph.setFont( speedFont );
        graph.setColor( MetrisConstants.COLOR_SPEEDDIGIT );
        graph.drawString( String.valueOf( gameKernel.getSpeed() ), 
                          MetrisConstants.SCREEN_OFFSET_SPEEDDIGIT_LEFT, 
                          MetrisConstants.SCREEN_OFFSET_SPEEDDIGIT_TOP, 
                          Graphics.RIGHT | Graphics.TOP );
        
        // 6. repaint the next piece
        if ( nextPiece != null ) {
            int pieceBlocks[][] = nextPiece.getBlocks();
            int xOffset = ( MetrisConstants.GAME_NEXTPIECEBOX_WIDTH - pieceBlocks[ 0 ].length * ( MetrisConstants.GAME_BLOCK_WIDTH + MetrisConstants.GAME_BLOCK_SPACING ) ) / 2;
            int yOffset = ( MetrisConstants.GAME_NEXTPIECEBOX_HEIGHT - pieceBlocks.length * ( MetrisConstants.GAME_BLOCK_HEIGHT + MetrisConstants.GAME_BLOCK_SPACING ) ) / 2;
            
            for( int lin = 0; lin < pieceBlocks.length; lin++ ) {
                for( int col = 0; col < pieceBlocks[ 0 ].length; col++ ) {
                    if ( pieceBlocks[ lin ][ col ] == 1 ) {
                        graph.drawImage( ImageRepository.pieceImages[ nextPiece.getType() ], 
                                         MetrisConstants.SCREEN_OFFSET_NEXTPIECE_LEFT + xOffset + ( col ) * ( MetrisConstants.GAME_BLOCK_WIDTH + MetrisConstants.GAME_BLOCK_SPACING ), 
                                         MetrisConstants.SCREEN_OFFSET_NEXTPIECE_TOP + yOffset + ( lin ) * ( MetrisConstants.GAME_BLOCK_HEIGHT + MetrisConstants.GAME_BLOCK_SPACING ), 
                                         Graphics.TOP | Graphics.LEFT ); 
                    }
                }
            }
        }
        
        // 7. paint the PAUSED image
        if ( gameKernel.isGameThreadPaused() ) {
            graph.drawImage( ImageRepository.pausedImage, 
                             MetrisConstants.SCREEN_OFFSET_PAUSEDIMAGE_LEFT, 
                             MetrisConstants.SCREEN_OFFSET_PAUSEDIMAGE_TOP, 
                             Graphics.TOP | Graphics.LEFT ); 
        }
    }
    
    public void repaintPartially() {
        repaint( MetrisConstants.SCREEN_OFFSET_GAMEBOARD_LEFT, 
                 MetrisConstants.SCREEN_OFFSET_GAMEBOARD_TOP,
                 MetrisConstants.GAME_BOARD_WIDTH_PX,
                 MetrisConstants.GAME_BOARD_HEIGHT_PX );
    }

    /**
     * @see javax.microedition.lcdui.Canvas#keyPressed(int)
     */
    protected void keyPressed( int keyCode ) {
        if ( keyCode == Canvas.KEY_STAR ) {
            commandAction( pauseCommand, null );
            return;
        }
        if ( keyCode == Canvas.KEY_POUND ) {
            commandAction( quitCommand, null );
            return;
        }
        
        int gameAction = getGameAction( keyCode );
        
        switch( gameAction ) {
            case Canvas.LEFT: {
                gameKernel.executeAction( GameKernel.GAME_ACTION_MOVELEFT );
            } break;
            case Canvas.RIGHT: {
                gameKernel.executeAction( GameKernel.GAME_ACTION_MOVERIGHT );
            } break;
            case Canvas.UP: {
                gameKernel.executeAction( GameKernel.GAME_ACTION_ROTATE_LEFT );
            } break;
            case Canvas.FIRE: {
                gameKernel.executeAction( GameKernel.GAME_ACTION_ROTATE_RIGHT );
            } break;
            case Canvas.DOWN: {
                gameKernel.executeAction( GameKernel.GAME_ACTION_DROPDOWN );
            } break;
        }
    }

    /**
     * @see javax.microedition.lcdui.Canvas#keyReleased(int)
     */
    protected void keyReleased( int keyCode ) {
        gameKernel.setLastGameActionMovement( 0 );
    }

    /**
     * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
     */
    public void commandAction( Command c, Displayable d ) {
        if ( c == quitCommand ) {
            gameKernel.setGameThreadStopped( true );
        } else
            if ( c == pauseCommand ) {
                gameKernel.setGameThreadPaused( !gameKernel.isGameThreadPaused() );
                this.repaint();
            } else
                if ( c == restartCommand ) {
                }
    }
}
