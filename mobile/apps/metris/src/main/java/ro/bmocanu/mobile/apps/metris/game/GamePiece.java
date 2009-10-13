/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * GamePiece
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ro.bmocanu.mobile.apps.metris.IDataPersistable;

/**
 * Class <code>GamePiece</code> represents one of the pieces that are falling during the
 * game. The class maintains info regarding the piece blocks composition, piece's position
 * and other elements.
 */
public class GamePiece implements IDataPersistable {

    /**
     * The block composition of this piece.
     */
    private int blocks[][];

    /**
     * The top coordinate of the piece.
     */
    private int top;

    /**
     * The left coordinate of the piece.
     */
    private int left;

    /**
     * The type of the piece. This value dictates which image will be used for drawing
     * each piece block.
     */
    private int type;

    public void rotate( int direction ) {
        // the blocks matrix is not a square, so we should treat it with care 
        int newBlocks[][] = new int[ blocks[ 0 ].length ][ blocks.length ];

        if ( direction < 0 ) {
            // rotate left
            int dim = blocks[ 0 ].length;
            for ( int lin = 0; lin < blocks.length; lin++ )
                for ( int col = 0; col < blocks[ 0 ].length; col++ ) {
                    newBlocks[ dim - col - 1 ][ lin ] = blocks[ lin ][ col ];

                }
        } else {
            // rotate right
            int dim = blocks.length;
            for ( int lin = 0; lin < blocks.length; lin++ )
                for ( int col = 0; col < blocks[ 0 ].length; col++ ) {
                    newBlocks[ col ][ dim - lin - 1 ] = blocks[ lin ][ col ];
                }
        }
        
        blocks = newBlocks;
    }
    
    public void flipHorizontally() {
        int width = blocks[ 0 ].length;
        for ( int lin = 0; lin < blocks.length; lin++ ) {
            for ( int col = 0; col < width / 2; col++ ) {
                int aux = blocks[ lin ][ col ];
                blocks[ lin ][ col ] = blocks[ lin ][ width - col - 1];
                blocks[ lin ][ width - col - 1] = aux;
            }
        }
    }

    /**
     * @see com.sworks.metris.IDataPersistable#loadData(java.io.DataInputStream)
     */
    public void loadData( DataInputStream stream ) throws IOException {
        top = stream.readInt();
        left = stream.readInt();
        type = stream.readByte();
        
        int nrLines = stream.readByte();
        int nrCols = stream.readByte();
        
        blocks = new int[ nrLines ][ nrCols ];
        for ( int lin = 0; lin < blocks.length; lin++ )
            for ( int col = 0; col < blocks[ 0 ].length; col++ )
                blocks[ lin ][ col ] = stream.readByte();
    }

    /**
     * @see com.sworks.metris.IDataPersistable#saveData(java.io.DataOutputStream)
     */
    public void saveData( DataOutputStream stream ) throws IOException {
        stream.writeInt( top );
        stream.writeInt( left );
        stream.writeByte( type );

        stream.writeByte( blocks.length );
        stream.writeByte( blocks[ 0 ].length );

        for ( int lin = 0; lin < blocks.length; lin++ )
            for ( int col = 0; col < blocks[ 0 ].length; col++ )
                stream.writeByte( blocks[ lin ][ col ] );
    }

    /**
     * Gets the value of the left attribute.
     * 
     * @return the left
     */
    public int getLeft() {
        return left;
    }

    /**
     * Sets the left attribute to the given value(s).
     * 
     * @param left
     *            the left to set
     */
    public void setLeft( int left ) {
        this.left = left;
    }

    /**
     * Gets the value of the top attribute.
     * 
     * @return the top
     */
    public int getTop() {
        return top;
    }

    /**
     * Sets the top attribute to the given value(s).
     * 
     * @param top
     *            the top to set
     */
    public void setTop( int top ) {
        this.top = top;
    }

    /**
     * Gets the value of the blocks attribute.
     * 
     * @return the blocks
     */
    public int[][] getBlocks() {
        return blocks;
    }

    /**
     * Sets the blocks attribute to the given value(s).
     * 
     * @param blocks
     *            the blocks to set
     */
    public void setBlocks( int[][] blocks ) {
        this.blocks = blocks;
    }

    /**
     * Gets the value of the type attribute.
     * 
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the type attribute to the given value(s).
     * 
     * @param type
     *            the type to set
     */
    public void setType( int type ) {
        this.type = type;
    }
}
