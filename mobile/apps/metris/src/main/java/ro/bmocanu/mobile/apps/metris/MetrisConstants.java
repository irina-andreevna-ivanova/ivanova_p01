/* Java2ME Mobile Tetris
 *
 * MetrisConstants
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

/**
 * Class <code>MetrisConstants</code> contains globally available constants.
 */
public class MetrisConstants {
    
    public static final String GAME_APP_VERSION = "1.1";
    
    public static final String GAME_APP_LASTUPDATE = "2006.09.30";
    
    public static final String GAME_APP_RECORDSTORE_NAME = "MetrisRS_09";
    
    /**
     * The total number of piece images.
     */
    public static final int IMAGE_PIECES_NR = 8;
    
    public static final int GAME_SPEED_MAX = 10;

    public static final int GAME_SPEED_CYCLE_HIGHLIMIT = 8;
    public static final int GAME_SPEED_CYCLE_LOWLIMIT = 3;
    
    public static final int GAME_SCORE4SPEED_STEP = 750;
    
    public static final int GAME_SPEED_CLICKTIME = 25;
    
    public static final int GAME_RESOLUTION_WIDTH = 132;
    public static final int GAME_RESOLUTION_HEIGHT = 176;
    
    /**
     * The width and height, in piece blocks, of the game board.
     */
    public static final int GAME_BOARD_WIDTH = 14;
    public static final int GAME_BOARD_HEIGHT = 25;
    
    /**
     * The width and height, in pixels, of a piece block.
     */
    public static final int GAME_BLOCK_WIDTH = 5;
    public static final int GAME_BLOCK_HEIGHT = 5;
    
    /**
     * The amount of space, in pixels, between two piece blocks.
     */
    public static final int GAME_BLOCK_SPACING = 1;

    public static final int GAME_BOARD_WIDTH_PX = GAME_BOARD_WIDTH * ( GAME_BLOCK_WIDTH + GAME_BLOCK_SPACING );
    public static final int GAME_BOARD_HEIGHT_PX = GAME_BOARD_HEIGHT * ( GAME_BLOCK_HEIGHT + GAME_BLOCK_SPACING );
    
    /**
     * The width and height, in pixels, of the next piece box.
     */
    public static final int GAME_NEXTPIECEBOX_WIDTH = 29;
    public static final int GAME_NEXTPIECEBOX_HEIGHT = 29;

    /**
     * The top and left offset of the game board. 
     */
    public static final int SCREEN_OFFSET_GAMEBOARD_LEFT = 4;
    public static final int SCREEN_OFFSET_GAMEBOARD_TOP = 23;
    
    /**
     * The top and left offset of the score text. 
     */
    public static final int SCREEN_OFFSET_SCORE_LEFT = 129;
    public static final int SCREEN_OFFSET_SCORE_TOP = 24;
    
    /**
     * The top and left offset of the next level score text. 
     */
    public static final int SCREEN_OFFSET_NEXTLVLSCORE_LEFT = 129;
    public static final int SCREEN_OFFSET_NEXTLVLSCORE_TOP = 51;
    
    /**
     * The top and left offset of the speed digit. 
     */
    public static final int SCREEN_OFFSET_SPEEDDIGIT_LEFT = 122;
    public static final int SCREEN_OFFSET_SPEEDDIGIT_TOP = 108;
    
    /**
     * The top and left offset of the speed digit. 
     */
    public static final int SCREEN_OFFSET_SPEEDGAUGE_LEFT = 91;
    public static final int SCREEN_OFFSET_SPEEDGAUGE_TOP = 94;
    
    /**
     * The top and left offset of the next piece box. 
     */
    public static final int SCREEN_OFFSET_NEXTPIECE_LEFT = 95;
    public static final int SCREEN_OFFSET_NEXTPIECE_TOP = 144;
    
    /**
     * The top and left offset of the PAUSED image. 
     */
    public static final int SCREEN_OFFSET_PAUSEDIMAGE_LEFT = 91;
    public static final int SCREEN_OFFSET_PAUSEDIMAGE_TOP = 69;
    
    /**
     * Various color constants used during game. 
     */
    public static final int COLOR_SCORE = 0x00FFFFFF;

    public static final int COLOR_NEXTLVLSCORE = 0x00AAAAAA;
    
    public static final int COLOR_SPEEDDIGIT = 0x00AAAAAA;
}
