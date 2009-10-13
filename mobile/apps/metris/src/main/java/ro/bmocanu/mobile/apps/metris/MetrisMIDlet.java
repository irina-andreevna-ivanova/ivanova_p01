/* Java2ME Mobile Tetris
 * 
 * MetrisMIDlet
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

import ro.bmocanu.mobile.apps.metris.game.GameKernel;
import ro.bmocanu.mobile.apps.metris.game.GameSettings;
import ro.bmocanu.mobile.apps.metris.gui.AboutPanel;
import ro.bmocanu.mobile.apps.metris.gui.BeforeGamePanel;
import ro.bmocanu.mobile.apps.metris.gui.MainMenuBuilder;
import ro.bmocanu.mobile.apps.metris.gui.OptionsPanel;

/**
 * The main class of the midlet. This class contains references to each main screen of the
 * game, and it is reponsible for displaying the right screen at the right moment.
 */
public class MetrisMIDlet extends MIDlet implements IDataPersistable {

    private Display thisDisplay;

    private GameKernel gameKernel;

    private MainMenuBuilder mainMenuBuilder = new MainMenuBuilder( this );

    private BeforeGamePanel beforeGamePanel = new BeforeGamePanel( this );

    private OptionsPanel optionsPanel = new OptionsPanel( this );

    private AboutPanel aboutPanel = new AboutPanel( this );

    private int highScore;

    /**
     * If this flag is true, then the resume game options is displayed in the main menu.
     * Otherwise only the start game option allows the user to play.
     */
    private boolean gameResumeAvailable;

    /**
     * This flag can be used to cancel the saving process performed when the app is
     * closing. This can be useful when the game has just been saved, and the user quits,
     * without changing anything else.
     */
    private boolean cancelDestroyAppSave;

    /**
     * Creates a new instance of <code>MetrisMIDlet</code> setting the object's
     * attributes to the given value(s).
     */
    public MetrisMIDlet() {
        super();
        highScore = 0;
        gameKernel = new GameKernel( this );
        gameResumeAvailable = false;
        cancelDestroyAppSave = true;
    }

    /**
     * Switches the display to the main menu items list.
     */
    protected void showMainMenuPanel() {
        thisDisplay.setCurrent( mainMenuBuilder.buildMainMenuList() );
    }

    /**
     * The <code>main</code> method of the game. When the user starts this game, an
     * instance of this class is created, and this method is called.
     * 
     * @see javax.microedition.midlet.MIDlet#startApp()
     */
    public void startApp() {
        if ( thisDisplay == null ) {
            AppDataPersister appDP = AppDataPersister.getInstance();
            appDP.addPersistentObject( this );
            appDP.addPersistentObject( GameSettings.getInstance() );
            appDP.addPersistentObject( gameKernel );

            if ( appDP.isDataRecordFound() ) {
                appDP.loadData();
            } else {
                appDP.saveData();
            }

            thisDisplay = Display.getDisplay( this );
            showMainMenuPanel();
        }

        if ( gameKernel != null && gameKernel.isGameRunning() ) {
            if ( gameKernel.isGameThreadPaused() ) {
                gameKernel.setGameThreadPaused( false );
            }
        }
    }

    /**
     * It is called when something occurrs and the game must be paused (like someone is
     * calling us, or something like that).
     * 
     * @see javax.microedition.midlet.MIDlet#pauseApp()
     */
    public void pauseApp() {
        if ( gameKernel != null && gameKernel.isGameRunning() ) {
            gameKernel.setGameThreadPaused( true );
        }
    }

    /**
     * Occurrs when the app is destroyed. Some finalization steps are performed here.
     * 
     * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
     */
    public void destroyApp( boolean unconditional ) {
        if ( gameKernel != null && gameKernel.isGameRunning() ) {
            gameResumeAvailable = true;
        }

        if ( !cancelDestroyAppSave )
            AppDataPersister.getInstance().saveData();

        if ( gameKernel != null && gameKernel.isGameRunning() ) {
            gameKernel.setGameThreadStopped( true );
            gameKernel.waitUntilFinished();
        }
    }

    /**
     * Starts a new game. If the <code>withLoad</code> parameter is true, then the game
     * is first loaded from RS and only then the game is actually started.
     * 
     * @param withLoad
     *            This flag indicates whether the game is a fresh one (with no load) or
     *            the game is simply resumed (with load).
     */
    protected void signalGameStarted( boolean withLoad ) {
        if ( withLoad ) {
            AppDataPersister.getInstance().loadData();
        } else {
            gameKernel.resetKernel();
        }

        gameResumeAvailable = true;

        gameKernel.startGame();
        thisDisplay.setCurrent( gameKernel.getDrawingCanvas() );
    }

    /**
     * Game over, so switch to main panel.
     */
    public void signalGameEnded() {
        showMainMenuPanel();
    }

    /**
     * Event triggering method. This method is called by any of the displayed panels, 
     * when some action is performed there. This way, this is the central point for
     * managing which panel is displayed, and when.
     * 
     * @param child
     *          The panel that triggered the action.
     * @param command
     *          The command that was issued in the child panel.
     */
    public void handleChildPanelCommand( Object child, Command command ) {
        if ( child == mainMenuBuilder ) {
            switch ( command.getPriority() ) {
                case 1: { // start the game
                    thisDisplay.setCurrent( beforeGamePanel );
                    break;
                }
                case 2: { // resume the game
                    signalGameStarted( true );
                    break;
                }
                case 3: { // show options
                    optionsPanel.refresh();
                    thisDisplay.setCurrent( optionsPanel );
                    break;
                }
                case 4: { // show the About screen
                    thisDisplay.setCurrent( aboutPanel );
                    break;
                }
                case 5: { // quit game
                    AppDataPersister.getInstance().saveData();
                    cancelDestroyAppSave = true;
                    destroyApp( false );
                    notifyDestroyed();
                    break;
                }
            }
            return;
        }
        if ( child == optionsPanel ) {
            showMainMenuPanel();
            return;
        }
        if ( child == beforeGamePanel ) {
            if ( command.getCommandType() == Command.CANCEL ) {
                showMainMenuPanel();
            } else {
                signalGameStarted( false );
            }
            return;
        }
        if ( child == aboutPanel ) {
            showMainMenuPanel();
            return;
        }
    }

    /**
     * @see com.sworks.metris.IDataPersistable#loadData(java.io.DataInputStream)
     */
    public void loadData( DataInputStream stream ) throws IOException {
        highScore = stream.readInt();
        gameResumeAvailable = stream.readBoolean();
    }

    /**
     * @see com.sworks.metris.IDataPersistable#saveData(java.io.DataOutputStream)
     */
    public void saveData( DataOutputStream stream ) throws IOException {
        stream.writeInt( highScore );
        stream.writeBoolean( gameResumeAvailable );
    }

    /**
     * Gets the value of the highScore attribute.
     * 
     * @return the highScore
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Sets the highScore attribute to the given value(s).
     * 
     * @param highScore
     *            the highScore to set
     */
    public void setHighScore( int highScore ) {
        this.highScore = highScore;
    }

    /**
     * Gets the value of the gameResumeAvailable attribute.
     * 
     * @return the gameResumeAvailable
     */
    public boolean isGameResumeAvailable() {
        return gameResumeAvailable;
    }

    /**
     * Sets the gameResumeAvailable attribute to the given value(s).
     * 
     * @param gameResumeAvailable
     *            the gameResumeAvailable to set
     */
    public void setGameResumeAvailable( boolean gameResumeAvailable ) {
        this.gameResumeAvailable = gameResumeAvailable;
    }
}