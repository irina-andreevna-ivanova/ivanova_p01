/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * ImageRepository
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
package ro.bmocanu.mobile.apps.metris;

import java.io.IOException;

import javax.microedition.lcdui.Image;

/**
 * Class <code>ImageRepository</code> caches the images used throughout the game.
 */
public class ImageRepository {

    /**
     * The images that compose the game pieces.
     */
    public static Image pieceImages[];

    public static Image tableImage;

    public static Image speedGaugeImage;

    public static Image topHeader;

    public static Image pausedImage;

    public static Image[] menuImages = new Image[ 5 ];

    static {
        pieceImages = new Image[ MetrisConstants.IMAGE_PIECES_NR ];
        for ( int index = 0; index < pieceImages.length; index++ ) {
            try {
                pieceImages[ index ] = Image.createImage( "/images/piece" + index + ".jpg" );
            } catch ( IOException exception ) {
            }
        }

        try {
            tableImage = Image.createImage( "/images/table.jpg" );
            speedGaugeImage = Image.createImage( "/images/speedgauge.jpg" );
            topHeader = Image.createImage( "/images/topheader.jpg" );
            pausedImage = Image.createImage( "/images/paused.jpg" );

            menuImages[ 0 ] = Image.createImage( "/images/icon_startgame.png" );
            menuImages[ 1 ] = Image.createImage( "/images/icon_resume.png" );
            menuImages[ 2 ] = Image.createImage( "/images/icon_options.png" );
            menuImages[ 3 ] = Image.createImage( "/images/icon_about.png" );
            menuImages[ 4 ] = Image.createImage( "/images/icon_exit.png" );
        } catch ( IOException exception ) {
        }
    }
}
