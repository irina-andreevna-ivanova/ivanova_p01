/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * AboutPanel
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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;

import ro.bmocanu.mobile.apps.metris.ImageRepository;
import ro.bmocanu.mobile.apps.metris.MetrisConstants;
import ro.bmocanu.mobile.apps.metris.MetrisMIDlet;

/**
 * Class <code>AboutPanel</code>
 */
public class AboutPanel extends Form implements CommandListener {

    private Font infoFont = Font.getFont( Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL );
    
    private Command backCommand = new Command( "Cancel", Command.CANCEL, 1 );
    
    private MetrisMIDlet parentMidlet;
    
    private String[] texts = new String[] { 
                                "Created by Bogdan Mocanu", 
                                "(C) SmartWorks, 2006",
                                "- - - - - - - - - -",
                                "Game version: " + MetrisConstants.GAME_APP_VERSION,
                                "Last update: " + MetrisConstants.GAME_APP_LASTUPDATE,
                                "License type: GNU GPL",
                                "Developed with Eclipse",
                                "Special thanks go to my soulmate Anca and my brother Vlad for all the testing and ideas they gave me."};
    
    /**
     * Creates a new instance of <code>BeforeGamePanel</code> setting the object's
     * attributes to the given value(s).
     * 
     */
    public AboutPanel( MetrisMIDlet parentMIDlet ) {
        super( "Starting game" );

        this.parentMidlet = parentMIDlet;

        ImageItem topHeader = new ImageItem( null, ImageRepository.topHeader, Item.LAYOUT_LEFT
                | Item.LAYOUT_NEWLINE_AFTER, null );
        this.append( topHeader );

        // StringItem header = new StringItem( null, "Game keys:\n", Item.PLAIN );
        // header.setLayout( Item.LAYOUT_LEFT | Item.LAYOUT_NEWLINE_AFTER );
        // this.append( header );

        for ( int index = 0; index < texts.length; index++ ) {
            StringItem item = new StringItem( null, texts[ index ] + "\n", Item.PLAIN );
            item.setLayout( Item.LAYOUT_LEFT | Item.LAYOUT_NEWLINE_AFTER );
            item.setFont( infoFont );
            this.append( item );
        }

        this.addCommand( backCommand );
        this.setCommandListener( this );
    }

    /**
     * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
     */
    public void commandAction( Command c, Displayable d ) {
        parentMidlet.handleChildPanelCommand( this, c );
    }
}
