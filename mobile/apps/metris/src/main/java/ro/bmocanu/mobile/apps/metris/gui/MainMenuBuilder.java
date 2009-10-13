/* Java2ME Mobile Tetris
 * 
 * MainMenu
 * 
 * Author : Bogdan Mocanu
 * 
 * Created : 05.10.2006
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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

import ro.bmocanu.mobile.apps.metris.ImageRepository;
import ro.bmocanu.mobile.apps.metris.MetrisMIDlet;

/**
 * Class <code>MainMenu</code>
 */
public class MainMenuBuilder implements CommandListener {

    private static String[] menuStrings = { 
            "Start new game", 
            "Resume game", 
            "Game options", 
            "About Metris",
            "Quit" };

    private MetrisMIDlet parentMIDlet;

    private Command selectCommand = new Command( "Select", Command.OK, 1 );
    
    private List currentList;

    public MainMenuBuilder( MetrisMIDlet parentMIDlet ) {
        this.parentMIDlet = parentMIDlet;
    }

    public List buildMainMenuList() {
        boolean gameResumeAvailable = parentMIDlet.isGameResumeAvailable();
        int nrElements = gameResumeAvailable ? 5 : 4;

        String finalMenuStrings[] = new String[ nrElements ];
        Image finalMenuImages[] = new Image[ nrElements ];

        int index = -1;
        for ( int elemIndex = 0; elemIndex < menuStrings.length; elemIndex++ ) {
            if ( gameResumeAvailable || elemIndex != 1 ) {
                index++;
                finalMenuStrings[ index ] = menuStrings[ elemIndex ];
                finalMenuImages[ index ] = ImageRepository.menuImages[ elemIndex ];
            }
        }

        currentList = new List( "Metris . " + parentMIDlet.getHighScore(), List.IMPLICIT, finalMenuStrings, finalMenuImages );
        currentList.setSelectCommand( selectCommand );
        currentList.setCommandListener( this );
        
        return currentList;
    }

    /**
     * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
     *      javax.microedition.lcdui.Displayable)
     */
    public void commandAction( Command arg0, Displayable arg1 ) {
        int index = currentList.getSelectedIndex() + 1;
        if ( !parentMIDlet.isGameResumeAvailable() && index > 1 ) {
            index++;
        }
        
        parentMIDlet.handleChildPanelCommand( this, new Command( "", Command.OK, index ) );
    }
}
