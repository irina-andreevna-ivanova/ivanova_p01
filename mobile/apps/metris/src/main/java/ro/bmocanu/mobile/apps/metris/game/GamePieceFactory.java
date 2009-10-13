/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * GamePieceFactory
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

import java.util.Random;

import ro.bmocanu.mobile.apps.metris.MetrisConstants;

/**
 * Class <code>GamePieceFactory</code> is responsible for creating all the pieces from the
 * game. The class contains the piece patterns, and is able to create the different types
 * of pieces.
 */
public class GamePieceFactory {
    
    private GameSettings gameSettings = GameSettings.getInstance();
    
    /**
     * The single instance of this singleton class.
     */
    private static GamePieceFactory singleInstance;

    /**
     * Creates a new instance of <code>GamePieceFactory</code>. Since this 
     * constructor is private, the only way a client can get an instance of
     * this class is through the use of the <code>getInstance()</code> static
     * method.
     */
    private GamePieceFactory() {
        super();
    }
    
    /**
     * Creates a new random piece. The method chooses the new piece's pattern and type
     * and then it assembles the object.
     * 
     * @return a new {@link GamePiece} object representing the newly created piece.
     */
    public GamePiece createRandomPiece() {
        int pieceIndexes[] = PIECE_THEMES[ gameSettings.getGamePieceTheme() ];
        Random randomGen = new Random(); 
        
        int piecePattern = pieceIndexes[ randomGen.nextInt( pieceIndexes.length ) ];
        int pieceType = gameSettings.isColouredPieces() ? randomGen.nextInt( MetrisConstants.IMAGE_PIECES_NR ) : 
                                                          MetrisConstants.IMAGE_PIECES_NR - 1;
        boolean shouldFlipIt = randomGen.nextInt( 100 ) > 50;
        int nrOfRotations = randomGen.nextInt( 4 );
        
        GamePiece newPiece = new GamePiece();
        newPiece.setLeft( 5 );
        newPiece.setTop( 0 );
        newPiece.setType( pieceType );
        
        int[][] pieceBlocks = new int[ PIECE_PATTERNS[ piecePattern ].length ][ PIECE_PATTERNS[ piecePattern ][ 0 ].length ];
        for( int lin = 0; lin < pieceBlocks.length; lin++ )
            for( int col = 0; col < pieceBlocks[ 0 ].length; col++ ) {
                pieceBlocks[ lin ][ col ] = PIECE_PATTERNS[ piecePattern ][ lin ][ col ];
            }
        
        newPiece.setBlocks( pieceBlocks );
        
        if ( shouldFlipIt ) {
            newPiece.flipHorizontally();
        }
        
        for( int rotIndex = 0; rotIndex< nrOfRotations; rotIndex++ ) {
            newPiece.rotate( -1 );
        }
        
        return newPiece;
    }

    /**
     * Returns the single instance of this class. If this is the first time when
     * this method is called, it creates a new instance of this class, and then
     * it returns it to the caller.
     * 
     * @return the single instance of this class.
     */
    public static GamePieceFactory getInstance() {
        if ( null == singleInstance ) {
            singleInstance = new GamePieceFactory();
        }

        return singleInstance;
    }
    
    /**
     * Various game piece themes. The indexes from these arrays correspond to the indexes from the
     * PIECE_PATTERNS matrix array. 
     */
    private final static int PIECE_THEMES[][] = { 
        /* Standard theme */ new int[] { 
            0, 1, 2, 3, 4 
        },
        
        /* Extended theme */ new int[] {
            /* Standard theme */ 0, 1, 2, 3, 4, 
            5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32
        },
    
        /* T-Tiles theme */ new int[] {
            1, 3, 5, 6, 7, 10, 13, 22
        },
    
        /* Corners theme */ new int[] {
            2, 4, 5, 6, 7, 8, 11, 16
        },
    
        /* Alien theme */ new int[] {
            /* Standard theme */ 0, 1, 2, 3, 4, 
            /* Extended theme */ 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32,
            33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49
        } 
    };

    /**
     * The various piece patterns that are used for building the pieces.
     */
    private final static int PIECE_PATTERNS[][][] = {
        //////////////////////////////////////////////////////////////////////// Standard theme
        new int[][] {                   // 0
                { 1, 1 },
                { 1, 1 }
        },
        new int[][] {                   // 1
                { 0, 1, 0 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 2
                { 0, 1, 1 },
                { 1, 1, 0 }
        },
        new int[][] {                   // 3
                { 0, 1, 0 },
                { 0, 1, 0 },
                { 0, 1, 0 },
                { 0, 1, 0 }
        },
        new int[][] {                   // 4
                { 1, 0 },
                { 1, 0 },
                { 1, 1 }
        },
        
        //////////////////////////////////////////////////////////////////// Extended theme
        new int[][] {                   // 5 
                { 1 }
        },
        new int[][] {                   // 6
                { 1 },
                { 1 }
        },
        new int[][] {                   // 7
                { 0, 1, 0 },
                { 0, 1, 0 },
                { 0, 1, 0 }
        },
        new int[][] {                   // 8
                { 1, 0 },
                { 1, 1 }
        },
        new int[][] {                   // 9
                { 1, 0, 1 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 10
                { 1, 0, 1 },
                { 1, 1, 1 },
                { 0, 1, 0 }
        },
        new int[][] {                   // 11
                { 1, 1, 1 },
                { 1, 0, 0 },
                { 1, 0, 0 }
        },
        new int[][] {                   // 12
                { 1, 0, 0 },
                { 1, 1, 0 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 13
                { 1, 1, 1 },
                { 0, 1, 0 },
                { 0, 1, 0 }
        },
        new int[][] {                   // 14
                { 1, 1, 0 },
                { 0, 1, 1 },
                { 0, 1, 0 }
        },
        new int[][] {                   // 15
                { 1, 1, 0 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 16
                { 1, 1, 0 },
                { 0, 1, 0 },
                { 0, 1, 1 }
        },
        new int[][] {                   // 17
                { 1, 0, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 18
                { 1, 0, 0 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 19
                { 1, 0, 0 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 20
                { 1, 0, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 21
                { 1, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 22
                { 0, 1, 0 },
                { 1, 1, 1 },
                { 0, 1, 0 }
        },
        new int[][] {                   // 23
                { 0, 1, 1 },
                { 1, 1, 1 },
                { 0, 1, 0 }
        },
        new int[][] {                   // 24
                { 0, 1, 1 },
                { 1, 1, 1 },
                { 0, 1, 1 }
        },
        new int[][] {                   // 25
                { 0, 1, 0 },
                { 1, 1, 0 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 26
                { 0, 1, 1 },
                { 1, 1, 0 },
                { 1, 1, 0 }
        },
        new int[][] {                   // 27
                { 1, 1 },
                { 1, 1 },
                { 1, 1 }
        },
        new int[][] {                   // 28
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 }
        },
        new int[][] {                   // 29
                { 0, 1, 1, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 }
        },
        new int[][] {                   // 30
                { 0, 1, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 }
        },
        new int[][] {                   // 31
                { 0, 1, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 1, 0 }
        },
        new int[][] {                   // 32
                { 1, 1, 1, 1 },
                { 1, 0, 0, 1 }
        },

        /////////////////////////////////////////////////////////////////////////// Alien theme
        new int[][] {                   // 33
                { 0, 1 },
                { 1, 0 }
        },
        new int[][] {                   // 34
                { 1, 0, 1 },
                { 0, 1, 0 },
                { 1, 0, 1 }
        },
        new int[][] {                   // 35
                { 1, 0, 1 },
                { 0, 1, 0 }
        },
        new int[][] {                   // 36
                { 1, 0, 1 },
                { 0, 1, 0 },
                { 1, 0, 0 }
        },
        new int[][] {                   // 37
                { 1, 1, 1 },
                { 0, 1, 0 },
                { 1, 0, 0 }
        },
        new int[][] {                   // 38
                { 1, 0, 1 },
                { 1, 0, 0 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 39
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 40
                { 1, 0, 0 },
                { 0, 1, 0 },
                { 0, 0, 1 }
        },
        new int[][] {                   // 41
                { 1, 1, 0 },
                { 1, 1, 0 },
                { 0, 0, 1 }
        },
        new int[][] {                   // 42
                { 0, 1 },
                { 1, 0 },
                { 1, 0 }
        },
        new int[][] {                   // 43
                { 1, 0, 0 },
                { 0, 1, 1 },
                { 0, 1, 0 }
        },
        new int[][] {                   // 44
                { 1, 0, 1 }
        },
        new int[][] {                   // 45
                { 1, 0, 0 },
                { 0, 0, 0 },
                { 1, 1, 0 }
        },
        new int[][] {                   // 46
                { 1, 0, 1 },
                { 0, 0, 0 },
                { 1, 0, 1 }
        },
        new int[][] {                   // 47
                { 1, 0, 0 },
                { 0, 0, 0 },
                { 1, 1, 1 }
        },
        new int[][] {                   // 48
                { 1, 0, 0 },
                { 0, 0, 1 }
        },
        new int[][] {                   // 49
              { 0, 0, 1 },
              { 1, 1, 0 },
              { 0, 0, 1 }
        }
//        new int[][] {                   // 9
//                { 0, 0, 0, 0 },
//                { 0, 0, 0, 0 },
//                { 0, 0, 0, 0 },
//                { 0, 0, 0, 0 }
//        },
//        new int[][] {                   // 9
//                { 0, 0, 0 },
//                { 0, 0, 0 },
//                { 0, 0, 0 }
//        },
//        new int[][] {                   // 9
//                { 0, 0 },
//                { 0, 0 }
//        }
    };
    
}
