/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * GameSettings
 * 
 * Author : Bogdan Mocanu
 * 
 * Created : 31.08.2006
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
 * Class <code>GameSettings</code> contains the set of options used for customizing the
 * game.
 */
public class GameSettings implements IDataPersistable {

    /**
     * The single instance of this singleton class.
     */
    private static GameSettings singleInstance;

    /**
     * The initial speed of the falling pieces.
     */
    private int gameStartSpeed;

    /**
     * If this flag is true, then the speed will increase, depending on the user's score.
     */
    private boolean speedIncreases;

    /**
     * If this flag is true then the speed will be reseted to a low value, once it reaches
     * a certain high level (this flag is meaningful onyl if speedIncreases is true).
     */
    private boolean speedCycles;

    /**
     * If this flag is true, the game will use coloured pieces. Otherwise all the falling
     * pieces will be plain gray.
     */
    private boolean colouredPieces;

    /**
     * The set of game pieces used during the game.
     */
    private int gamePieceTheme;
    
    /**
     * The level of start table fillment.
     */
    private int gameTableFill;
    
    /**
     * The funny preset chosen for the game table.
     */
    private int gameTablePreset;

    /**
     * Creates a new instance of <code>GameSettings</code>. Since this constructor is
     * private, the only way a client can get an instance of this class is through the use
     * of the <code>getInstance()</code> static method.
     */
    private GameSettings() {
        super();
        gamePieceTheme = 0;
        gameStartSpeed = 1;
        speedIncreases = true;
        speedCycles = false;
        colouredPieces = true;
        gameTablePreset = 0;
        gameTableFill = 0;
    }

    /**
     * @see com.sworks.metris.IDataPersistable#loadData(java.io.DataInputStream)
     */
    public void loadData( DataInputStream stream ) throws IOException {
        gamePieceTheme = stream.readInt();
        gameStartSpeed = stream.readInt();
        speedIncreases = stream.readBoolean();
        speedCycles = stream.readBoolean();
        colouredPieces = stream.readBoolean();
        gameTablePreset = stream.readByte();
        gameTableFill = stream.readByte();
    }

    /**
     * @see com.sworks.metris.IDataPersistable#saveData(java.io.DataOutputStream)
     */
    public void saveData( DataOutputStream stream ) throws IOException {
        stream.writeInt( gamePieceTheme );
        stream.writeInt( gameStartSpeed );
        stream.writeBoolean( speedIncreases );
        stream.writeBoolean( speedCycles );
        stream.writeBoolean( colouredPieces );
        stream.writeByte( gameTablePreset );
        stream.writeByte( gameTableFill );
    }

    /**
     * Returns the single instance of this class. If this is the first time when this
     * method is called, it creates a new instance of this class, and then it returns it
     * to the caller.
     * 
     * @return the single instance of this class.
     */
    public static GameSettings getInstance() {
        if ( null == singleInstance ) {
            singleInstance = new GameSettings();
        }

        return singleInstance;
    }

    /**
     * Gets the value of the colouredPieces attribute.
     * 
     * @return the colouredPieces
     */
    public boolean isColouredPieces() {
        return colouredPieces;
    }

    /**
     * Sets the colouredPieces attribute to the given value(s).
     * 
     * @param colouredPieces
     *            the colouredPieces to set
     */
    public void setColouredPieces( boolean colouredPieces ) {
        this.colouredPieces = colouredPieces;
    }

    /**
     * Gets the value of the gameStartSpeed attribute.
     * 
     * @return the gameStartSpeed
     */
    public int getGameStartSpeed() {
        return gameStartSpeed;
    }

    /**
     * Sets the gameStartSpeed attribute to the given value(s).
     * 
     * @param gameStartSpeed
     *            the gameStartSpeed to set
     */
    public void setGameStartSpeed( int gameStartSpeed ) {
        this.gameStartSpeed = gameStartSpeed;
    }

    /**
     * Gets the value of the speedCycles attribute.
     * 
     * @return the speedCycles
     */
    public boolean isSpeedCycles() {
        return speedCycles;
    }

    /**
     * Sets the speedCycles attribute to the given value(s).
     * 
     * @param speedCycles
     *            the speedCycles to set
     */
    public void setSpeedCycles( boolean speedCycles ) {
        this.speedCycles = speedCycles;
    }

    /**
     * Gets the value of the speedIncreases attribute.
     * 
     * @return the speedIncreases
     */
    public boolean isSpeedIncreases() {
        return speedIncreases;
    }

    /**
     * Sets the speedIncreases attribute to the given value(s).
     * 
     * @param speedIncreases
     *            the speedIncreases to set
     */
    public void setSpeedIncreases( boolean speedIncreases ) {
        this.speedIncreases = speedIncreases;
    }

    /**
     * Gets the value of the gamePieceTheme attribute.
     * 
     * @return the gamePieceTheme
     */
    public int getGamePieceTheme() {
        return gamePieceTheme;
    }

    /**
     * Sets the gamePieceTheme attribute to the given value(s).
     * 
     * @param gamePieceTheme
     *            the gamePieceTheme to set
     */
    public void setGamePieceTheme( int gamePieceTheme ) {
        this.gamePieceTheme = gamePieceTheme;
    }

    /**
     * Gets the value of the gameTableFill attribute.
     *
     * @return the gameTableFill
     */
    public int getGameTableFill() {
        return gameTableFill;
    }

    /**
     * Sets the gameTableFill attribute to the given value(s).
     *
     * @param gameTableFill the gameTableFill to set
     */
    public void setGameTableFill( int gameTableFill ) {
        this.gameTableFill = gameTableFill;
    }

    /**
     * Gets the value of the gameTablePreset attribute.
     *
     * @return the gameTablePreset
     */
    public int getGameTablePreset() {
        return gameTablePreset;
    }

    /**
     * Sets the gameTablePreset attribute to the given value(s).
     *
     * @param gameTablePreset the gameTablePreset to set
     */
    public void setGameTablePreset( int gameTablePreset ) {
        this.gameTablePreset = gameTablePreset;
    }

}
