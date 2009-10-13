/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * OptionsPanel
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
package ro.bmocanu.mobile.apps.metris.gui;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import ro.bmocanu.mobile.apps.metris.ImageRepository;
import ro.bmocanu.mobile.apps.metris.MetrisMIDlet;
import ro.bmocanu.mobile.apps.metris.game.GameSettings;

/**
 * Class <code>OptionsPanel</code> represents the panel that is displayed when the user
 * selects the game options command from the main menu. The panel allows the user to
 * change some of the game's options.
 */
public class OptionsPanel extends Form implements CommandListener {
    
    private MetrisMIDlet parentMIDlet;
    
    private ChoiceGroup gameThemeChoice = new ChoiceGroup( "Game theme", ChoiceGroup.POPUP, 
                                                            new String[] { "Standard", "Extended", "T-Tiles", "Corners", "Alien" }, 
                                                            null );
    
    private ChoiceGroup startSpeedChoice = new ChoiceGroup( "Start speed", ChoiceGroup.POPUP, 
                                                            new String[] { "Very slow", "Slow", "Normal", "Fast", "Very fast" }, 
                                                            null );

    private ChoiceGroup speedIncreasesChoice = new ChoiceGroup( "Speed increases", ChoiceGroup.POPUP, 
                                                            new String[] { "No", "Yes" }, 
                                                            null );

    private ChoiceGroup speedCyclesChoice = new ChoiceGroup( "Speed cycles", ChoiceGroup.POPUP, 
                                                            new String[] { "No", "Yes" }, 
                                                            null );

    private ChoiceGroup colouredPiecesChoice = new ChoiceGroup( "Coloured pieces", ChoiceGroup.POPUP, 
                                                            new String[] { "No", "Yes" }, 
                                                            null );
    
    private ChoiceGroup tablePresetChoice = new ChoiceGroup( "Table preset", ChoiceGroup.POPUP, 
                                                            new String[] { "None", "Tropical", "The Dam", "Underconstr", "Stars" }, 
                                                            null );
    
    private ChoiceGroup tableFillChoice = new ChoiceGroup( "Table fill", ChoiceGroup.POPUP, 
                                                            new String[] { "None", "Low", "Medium", "High" }, 
                                                            null );
    
    private Command applyCommand = new Command( "Apply", Command.OK, 1 );
    
    private Command cancelCommand = new Command( "Cancel", Command.CANCEL, 2 );
    

    /**
     * Creates a new instance of <code>OptionsPanel</code>
     * setting the object's attributes to the given value(s).
     *
     * @param title
     * @param parentMIDlet
     */
    public OptionsPanel( MetrisMIDlet parentMIDlet ) {
        super( "Options" );
        this.parentMIDlet = parentMIDlet;
        
        this.append( ImageRepository.topHeader );
        this.append( gameThemeChoice );
        this.append( startSpeedChoice );
        this.append( speedIncreasesChoice );
        this.append( speedCyclesChoice );
        this.append( colouredPiecesChoice );
        this.append( tablePresetChoice );
        this.append( tableFillChoice );

        this.addCommand( applyCommand );
        this.addCommand( cancelCommand );
        this.setCommandListener( this );
        
        refresh();
    }
    
    public void refresh() {
        GameSettings gameSettings = GameSettings.getInstance();
        gameThemeChoice.setSelectedIndex( gameSettings.getGamePieceTheme(), true );
        startSpeedChoice.setSelectedIndex( ( gameSettings.getGameStartSpeed() + 1 ) / 2 - 1, true );
        speedIncreasesChoice.setSelectedIndex( gameSettings.isSpeedIncreases() ? 1 : 0, true );
        speedCyclesChoice.setSelectedIndex( gameSettings.isSpeedCycles() ? 1 : 0, true );
        colouredPiecesChoice.setSelectedIndex( gameSettings.isColouredPieces() ? 1 : 0, true );
        tablePresetChoice.setSelectedIndex( gameSettings.getGameTablePreset(), true );
        tableFillChoice.setSelectedIndex( gameSettings.getGameTableFill(), true );
    }

    /**
     * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
     */
    public void commandAction( Command c, Displayable d ) {
        if ( c.getCommandType() == Command.OK ) {
            // make the changes permanently
            GameSettings gameSettings = GameSettings.getInstance();
            gameSettings.setGamePieceTheme( gameThemeChoice.getSelectedIndex() );
            gameSettings.setGameStartSpeed( startSpeedChoice.getSelectedIndex() * 2 + 1 );
            gameSettings.setSpeedIncreases( speedIncreasesChoice.getSelectedIndex() == 1 );
            gameSettings.setSpeedCycles( speedCyclesChoice.getSelectedIndex() == 1 );
            gameSettings.setColouredPieces( colouredPiecesChoice.getSelectedIndex() == 1 );
            gameSettings.setGameTablePreset( tablePresetChoice.getSelectedIndex() );
            gameSettings.setGameTableFill( tableFillChoice.getSelectedIndex() );
        }
        
        parentMIDlet.handleChildPanelCommand( this, c );
    } 
}
