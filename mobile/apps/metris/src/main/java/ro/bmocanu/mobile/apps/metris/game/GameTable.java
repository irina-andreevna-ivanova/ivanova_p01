/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * GameTable
 * 
 * Author : Bogdan Mocanu
 * 
 * Created : 28.08.2006
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

import ro.bmocanu.mobile.apps.metris.IDataPersistable;
import ro.bmocanu.mobile.apps.metris.MetrisConstants;

/**
 * Class <code>GameTable</code>
 */
public class GameTable implements IDataPersistable {

    public final static int EMPTY = -100;

    private int tableBlocks[][];

    /**
     * These variables are used during the game over animation, for pulling down a certain
     * game table column.
     */
    private int gameOverAnimColumn;
    
    private int gameOverAnimLine;
    
    private boolean gameOverAnimFinished;

    /**
     * Creates a new instance of <code>GameTable</code> setting the object's attributes
     * to the given value(s).
     * 
     */
    public GameTable() {
        tableBlocks = new int[ MetrisConstants.GAME_BOARD_HEIGHT ][ MetrisConstants.GAME_BOARD_WIDTH ];
        gameOverAnimColumn = 0;
        gameOverAnimLine = 0;
        gameOverAnimFinished = false;
        reset();
    }

    public void reset() {
        for ( int lin = 0; lin < tableBlocks.length; lin++ )
            for ( int col = 0; col < tableBlocks[ 0 ].length; col++ )
                tableBlocks[ lin ][ col ] = EMPTY;
    }
    
    public void fillTable() {
        GameSettings gameSettings = GameSettings.getInstance();
        
        if ( gameSettings.getGameTablePreset() != GameTablePresets.TABLE_PRESET_NONE ) {
            String[] preset = (String[])GameTablePresets.TABLE_PRESETS[ gameSettings.getGameTablePreset() - 1 ];
            int presetLength = preset.length;
            
            for( int lin = 0; lin < preset.length; lin++ ) {
                for( int col = 0; col < preset[ lin ].length(); col++ ) {
                    char currentChar = preset[ lin ].charAt( col );
                    if ( currentChar != ' ' ) { 
                        tableBlocks[ tableBlocks.length - presetLength + lin ][ col ] = Integer.parseInt( currentChar + "" );
                    }
                }
            }
            
        } else {
            Random random = new Random(); 
            
            for( int index = 0; index < gameSettings.getGameTableFill() * 5; index++ ) {
                for ( int col = 0; col < tableBlocks[ 0 ].length; col++ ) {
                    int nextValue = random.nextInt( MetrisConstants.IMAGE_PIECES_NR + 5 ) - 5;
                    if ( nextValue < 0 )
                        nextValue = EMPTY;
                    tableBlocks[ tableBlocks.length - index - 1 ][ col ] = nextValue; 
                }
            }
        }
    }

    public void putPiece( GamePiece piece ) {
        int pieceBlocks[][] = piece.getBlocks();
        int pieceLeft = piece.getLeft();
        int pieceTop = piece.getTop();
        int pieceType = piece.getType();

        for ( int lin = 0; lin < pieceBlocks.length; lin++ )
            for ( int col = 0; col < pieceBlocks[ 0 ].length; col++ )
                if ( pieceBlocks[ lin ][ col ] == 1 ) {
                    tableBlocks[ pieceTop + lin ][ pieceLeft + col ] = pieceType;
                }
    }

    /**
     * This method checks for any existing full lines, and returns the number of lines
     * found to be full.
     * 
     * @return the number of full lines found.
     */
    public int checkFullLines() {
        int nrFullLines = 0;

        for ( int lin = 0; lin < tableBlocks.length; lin++ ) {
            boolean fullLine = true;

            for ( int col = 0; col < tableBlocks[ 0 ].length && fullLine; col++ ) {
                if ( tableBlocks[ lin ][ col ] == EMPTY ) {
                    fullLine = false;
                }
            }

            if ( fullLine ) {
                nrFullLines++;

                // pull the items above one line down
                for ( int tempLin = lin; tempLin > 0; tempLin-- )
                    for ( int col = 0; col < tableBlocks[ 0 ].length; col++ )
                        tableBlocks[ tempLin ][ col ] = tableBlocks[ tempLin - 1 ][ col ];

                // the first line always gets empty
                for ( int col = 0; col < tableBlocks[ 0 ].length; col++ )
                    tableBlocks[ 0 ][ col ] = EMPTY;
            }
        }

        return nrFullLines;
    }

    /**
     * This method checks for collisions between the given piece, located at the given
     * position (one should not that the actual piece position (identified through the
     * piece's getLeft(), getTop() methods) is not considered) and the table borders or
     * the tables existing blocks.
     * <p>
     * The method returns TRUE if at least one collision is found, and FALSE otherwise.
     * 
     * @param piece
     *            The piece to check (the actual coordinates of the piece are not
     *            considered).
     * @param left
     *            The left coordinate of the piece.
     * @param top
     *            The top coordinate of the piece.
     * @return TRUE if at least one collision is found, and FALSE otherwise.
     */
    public boolean checkCollision( GamePiece piece, int left, int top ) {
        // at the same time check if the piece is out of the table, and if
        // it's blocks overlap with existing table blocks
        int blocks[][] = piece.getBlocks();

        for ( int lin = 0; lin < blocks.length; lin++ )
            for ( int col = 0; col < blocks[ 0 ].length; col++ )
                if ( blocks[ lin ][ col ] == 1 ) {
                    if ( lin + top >= MetrisConstants.GAME_BOARD_HEIGHT || lin + top < 0
                            || col + left >= MetrisConstants.GAME_BOARD_WIDTH || col + left < 0 ) {
                        return true;
                    }

                    if ( tableBlocks[ lin + top ][ col + left ] != EMPTY ) {
                        return true;
                    }
                }

        return false;
    }

    public boolean checkCollision( GamePiece piece ) {
        return checkCollision( piece, piece.getLeft(), piece.getTop() );
    }
    
    private int gameOverChooseNextColumn( int startColumn ) {
        for( int col = startColumn; col < tableBlocks[ 0 ].length; col++ ) {
            boolean emptyColumn = true;
            
            for( int lin = 0; lin < tableBlocks.length; lin++ ) {
                if ( tableBlocks[ lin ][ col ] != EMPTY ) {
                    emptyColumn = false; 
                }
            }
            
            if ( !emptyColumn ) {
                return col;
            }
        }
        
        return -1;
    }
    
    private int gameOverChooseFirstLine( int column ) {
        for( int lin = 0; lin < tableBlocks.length; lin++ ) {
            if ( tableBlocks[ lin ][ column ] != EMPTY ) {
                return lin;
            }
        }
        
        return MetrisConstants.GAME_BOARD_HEIGHT;
    }
    
    public void gameOverStartAnimation() {
        gameOverAnimColumn = gameOverChooseNextColumn( 0 );
        gameOverAnimLine = gameOverChooseFirstLine( gameOverAnimColumn );
        gameOverAnimFinished = false;
    }
    
    public void gameOverContinueAnimation() {
        if ( gameOverAnimFinished ) {
            return;
        }
        
        for( int lin = MetrisConstants.GAME_BOARD_HEIGHT - 1; lin > gameOverAnimLine; lin-- ) {
            tableBlocks[ lin ][ gameOverAnimColumn ] = tableBlocks[ lin - 1 ][ gameOverAnimColumn ]; 
        }
        tableBlocks[ gameOverAnimLine ][ gameOverAnimColumn ] = EMPTY;
        
        gameOverAnimLine++;
        while ( gameOverAnimLine >= MetrisConstants.GAME_BOARD_HEIGHT ) {
            gameOverAnimColumn = gameOverChooseNextColumn( gameOverAnimColumn + 1 );
            
            if ( gameOverAnimColumn > -1 ) {
                gameOverAnimLine = gameOverChooseFirstLine( gameOverAnimColumn );
            } else {
                gameOverAnimFinished = true;
                break;
            }
        }
    }

    /**
     * @see com.sworks.metris.IDataPersistable#loadData(java.io.DataInputStream)
     */
    public void loadData( DataInputStream stream ) throws IOException {
        for ( int lin = 0; lin < tableBlocks.length; lin++ )
            for ( int col = 0; col < tableBlocks[ 0 ].length; col++ )
                tableBlocks[ lin ][ col ] = stream.readByte();
    }

    /**
     * @see com.sworks.metris.IDataPersistable#saveData(java.io.DataOutputStream)
     */
    public void saveData( DataOutputStream stream ) throws IOException {
        for ( int lin = 0; lin < tableBlocks.length; lin++ )
            for ( int col = 0; col < tableBlocks[ 0 ].length; col++ )
                stream.writeByte( tableBlocks[ lin ][ col ] );
    }

    /**
     * Gets the value of the tableBlocks attribute.
     * 
     * @return the tableBlocks
     */
    public int[][] getTableBlocks() {
        return tableBlocks;
    }

    /**
     * Gets the value of the gameOverAnimFinished attribute.
     *
     * @return the gameOverAnimFinished
     */
    public boolean isGameOverAnimFinished() {
        return gameOverAnimFinished;
    }
}
