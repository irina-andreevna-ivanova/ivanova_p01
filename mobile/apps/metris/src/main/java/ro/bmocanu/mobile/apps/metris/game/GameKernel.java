/* Java2ME Mobile Tetris
 * Foundation Classes
 * 
 * GameKernel
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

import ro.bmocanu.mobile.apps.metris.AppDataPersister;
import ro.bmocanu.mobile.apps.metris.IDataPersistable;
import ro.bmocanu.mobile.apps.metris.MetrisConstants;
import ro.bmocanu.mobile.apps.metris.MetrisMIDlet;

/**
 * Class <code>GameKernel</code> contains the main items of the game.
 */
public class GameKernel implements Runnable, IDataPersistable {

    /**
     * The types of game actions that can be used when calling the executeAction() method.
     */
    public final static int GAME_ACTION_MOVELEFT = 1;

    public final static int GAME_ACTION_MOVERIGHT = 2;

    public final static int GAME_ACTION_ROTATE_LEFT = 3;

    public final static int GAME_ACTION_ROTATE_RIGHT = 4;

    public final static int GAME_ACTION_DROPDOWN = 5;

    public final static int GAME_ACTION_PAUSE = 6;

    public final static int GAME_ACTION_EXIT = 7;

    private MetrisMIDlet parentMIDlet;

    /**
     * The canvas on which the entire drawing process takes place.
     */
    private GameCanvas gameCanvas;

    /**
     * The object responsible for maintaining the table's blocks.
     */
    private GameTable gameTable;

    /**
     * The piece that is currently falling.
     */
    private GamePiece nextPiece;

    /**
     * The piece that is currently falling.
     */
    private GamePiece currentPiece;

    /**
     * The current score of the player.
     */
    private int score;

    /**
     * The score that must be reached for the next level.
     */
    private int nextLevelScore;

    /**
     * This variable goes along with the score one, but it is reseted at each
     * <code>MetrisConstants.GAME_SPEEDSCORE_STEP</code>
     */
    private int score4Speed;

    /**
     * The current speed of the game (that is the speed of the falling pieces).
     */
    private int speed;

    private GameSettings gameSettings = GameSettings.getInstance();

    /**
     * The thread which sets the entire game in motion. The thread calls, from time to
     * time the <code>run()</code> method of this class.
     */
    private Thread gameThread;

    /**
     * This flag shows whether the game thread should pause for a while or not. The thread
     * is running until this flag becomes true. Then it starts sleeping until the flag
     * becomes false again.
     */
    private boolean gameThreadPaused;

    /**
     * This flag shows whether the game thread should stop or not. The thread is running
     * until this flag becomes true.
     */
    private boolean gameThreadStopped;

    /**
     * This int flag shows whether the game thread is in Rush mode, or it is running in
     * the normal mode. When the thread is in Rush mode, the delay time between two clicks
     * is very short (during this interval the piece falls very quickly).
     */
    private int gameThreadRushTime;

    private int lastGameActionMovement;

    private int lastGameActionMovement_delay;

    private boolean gameThreadInGameOverMode;

    private boolean gameRunning;

    /**
     * Creates a new instance of <code>GameKernel</code>. Since this constructor is
     * private, the only way a client can get an instance of this class is through the use
     * of the <code>getInstance()</code> static method.
     */
    public GameKernel( MetrisMIDlet parentMIDlet ) {
        this.parentMIDlet = parentMIDlet;
        gameRunning = false;

        gameTable = new GameTable();

        gameCanvas = new GameCanvas( this );
        gameCanvas.setFullScreenMode( true );

        chooseNextPiece();
    }

    public void resetKernel() {
        gameTable.reset();
        gameTable.fillTable();
        
        score = 0;
        nextLevelScore = MetrisConstants.GAME_SCORE4SPEED_STEP;
        score4Speed = 0;
        speed = gameSettings.getGameStartSpeed();

        currentPiece = null;
        nextPiece = null;

        chooseNextPiece();

        gameThreadPaused = false;
    }

    public void startGame() {
        gameRunning = true;
        gameCanvas.repaintGameBoard();

        gameThread = new Thread( this );
        gameThread.start();
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
        gameThreadStopped = false;
        gameThreadRushTime = 0;
        gameThreadInGameOverMode = false;

        while ( !gameThreadStopped ) {
            if ( !gameThreadInGameOverMode ) {
                try {
                    if ( gameThreadRushTime > 0 ) {
                        Thread.sleep( 20 );
                        gameThreadRushTime--;
                    } else {
                        int nrOfClicks = ( MetrisConstants.GAME_SPEED_MAX - speed ) * 2;
                        if ( nrOfClicks <= 0 )
                            nrOfClicks = 1;

                        for ( int index = 0; index < nrOfClicks && gameThreadRushTime == 0; index++ ) {
                            // in order to get a smooth movement, we wait two times for
                            // half of Click Time
                            for ( int internalIndex = 0; internalIndex < 2; internalIndex++ ) {
                                Thread.sleep( MetrisConstants.GAME_SPEED_CLICKTIME / 2 );

                                if ( lastGameActionMovement_delay > 0 ) {
                                    lastGameActionMovement_delay--;
                                }
                                if ( lastGameActionMovement > 0 && lastGameActionMovement_delay == 0 ) {
                                    executeAction( lastGameActionMovement );
                                }
                            }
                        }
                    }
                } catch ( InterruptedException exception ) {
                }

                if ( !gameThreadPaused ) {
                    advancePiece();
                }
            } else {
                try {
                    Thread.sleep( MetrisConstants.GAME_SPEED_CLICKTIME );
                } catch ( InterruptedException exception ) {
                }

                // game over thread mode
                gameTable.gameOverContinueAnimation();
                gameTable.gameOverContinueAnimation();
                gameCanvas.repaintGameBoard();
                gameCanvas.repaint();

                if ( gameTable.isGameOverAnimFinished() ) {
                    gameThreadStopped = true;
                }
            }
        }

        gameRunning = false;
        gameThread = null;
        
        if ( !gameThreadInGameOverMode ) {
            parentMIDlet.setGameResumeAvailable( true );
            AppDataPersister.getInstance().saveData();
        }
        parentMIDlet.signalGameEnded();
    }

    public void waitUntilFinished() {
        gameThreadStopped = true;
        try {
            gameThread.join();
            gameThread = null;
        } catch ( InterruptedException exception ) {
        }
    }

    // Game methods
    // --------------------------------------------------------------------------------

    public void executeAction( int gameKernelAction ) {
        if ( gameThreadPaused || gameThreadStopped || gameThreadInGameOverMode ) {
            return;
        }

        boolean needRepaint = false;

        switch ( gameKernelAction ) {
            case GAME_ACTION_MOVELEFT: {
                if ( !gameTable.checkCollision( currentPiece, currentPiece.getLeft() - 1, currentPiece.getTop() ) ) {
                    currentPiece.setLeft( currentPiece.getLeft() - 1 );
                    needRepaint = true;
                    lastGameActionMovement = gameKernelAction;
                    lastGameActionMovement_delay = 15;
                }
                break;
            }

            case GAME_ACTION_MOVERIGHT: {
                if ( !gameTable.checkCollision( currentPiece, currentPiece.getLeft() + 1, currentPiece.getTop() ) ) {
                    currentPiece.setLeft( currentPiece.getLeft() + 1 );
                    needRepaint = true;
                    lastGameActionMovement = gameKernelAction;
                    lastGameActionMovement_delay = 15;
                }
                break;
            }

            case GAME_ACTION_ROTATE_LEFT: {
                currentPiece.rotate( -1 );
                if ( gameTable.checkCollision( getCurrentPiece() ) ) {
                    currentPiece.rotate( 1 );
                } else {
                    needRepaint = true;
                }

                break;
            }

            case GAME_ACTION_ROTATE_RIGHT: {
                currentPiece.rotate( 1 );
                if ( gameTable.checkCollision( getCurrentPiece() ) ) {
                    currentPiece.rotate( -1 );
                } else {
                    needRepaint = true;
                }

                break;
            }

            case GAME_ACTION_DROPDOWN: {
                gameThreadRushTime = 5;
                break;
            }
        }

        if ( needRepaint ) {
            gameCanvas.repaintPartially();
        }
    }

    public void chooseNextPiece() {
        if ( nextPiece == null ) {
            nextPiece = GamePieceFactory.getInstance().createRandomPiece();
        }

        currentPiece = nextPiece;
        nextPiece = GamePieceFactory.getInstance().createRandomPiece();
    }

    public synchronized void advancePiece() {
        if ( gameThreadPaused || gameThreadStopped || gameThreadInGameOverMode ) {
            return;
        }

        if ( !gameTable.checkCollision( currentPiece, currentPiece.getLeft(), currentPiece.getTop() + 1 ) ) {
            currentPiece.setTop( currentPiece.getTop() + 1 );
            gameCanvas.repaintPartially();
        } else {
            gameThreadPaused = true;

            gameTable.putPiece( currentPiece );
            computeScoreBonus( gameTable.checkFullLines() );
            gameCanvas.repaintGameBoard();

            chooseNextPiece();
            gameThreadRushTime = 0;

            // check to see if the game is over
            if ( gameTable.checkCollision( currentPiece ) ) {
                parentMIDlet.setGameResumeAvailable( false );

                AppDataPersister.getInstance().saveData();
                
                gameTable.gameOverStartAnimation();
                gameThreadInGameOverMode = true;
                currentPiece = null;
            }

            gameThreadPaused = false;
            gameCanvas.repaint();
        }
    }

    protected void computeScoreBonus( int nrFullLines ) {
        if ( nrFullLines <= 0 ) {
            return;
        }

        nrFullLines = nrFullLines > 4 ? 4 : nrFullLines;
        int scoreBonuses[] = { 10, 30, 50, 100 };
        score += scoreBonuses[ nrFullLines - 1 ];
        
        if ( score > parentMIDlet.getHighScore() ) {
            parentMIDlet.setHighScore( score );
        }
        
        nextLevelScore = ( ( score / MetrisConstants.GAME_SCORE4SPEED_STEP ) + 1 )
                * MetrisConstants.GAME_SCORE4SPEED_STEP;
        score4Speed += scoreBonuses[ nrFullLines - 1 ];

        while ( score4Speed >= MetrisConstants.GAME_SCORE4SPEED_STEP ) {
            if ( gameSettings.isSpeedIncreases() ) {
                if ( speed < MetrisConstants.GAME_SPEED_MAX ) {
                    speed++;
                }

                if ( gameSettings.isSpeedCycles() ) {
                    if ( speed > MetrisConstants.GAME_SPEED_CYCLE_HIGHLIMIT ) {
                        speed = MetrisConstants.GAME_SPEED_CYCLE_LOWLIMIT;
                    }
                }
            }
            score4Speed -= MetrisConstants.GAME_SCORE4SPEED_STEP;
        }
    }

    /**
     * @see com.sworks.metris.IDataPersistable#loadData(java.io.DataInputStream)
     */
    public void loadData( DataInputStream stream ) throws IOException {
        gameTable.loadData( stream );
        nextPiece.loadData( stream );
        currentPiece.loadData( stream );
        score = stream.readInt();
        nextLevelScore = stream.readInt();
        score4Speed = stream.readInt();
        speed = stream.readInt();
        gameThreadPaused = stream.readBoolean();
    }

    /**
     * @see com.sworks.metris.IDataPersistable#saveData(java.io.DataOutputStream)
     */
    public void saveData( DataOutputStream stream ) throws IOException {
        gameTable.saveData( stream );
        nextPiece.saveData( stream );
        currentPiece.saveData( stream );
        stream.writeInt( score );
        stream.writeInt( nextLevelScore );
        stream.writeInt( score4Speed );
        stream.writeInt( speed );
        stream.writeBoolean( gameThreadPaused );
    }

    // Getters & setters
    // ---------------------------------------------------------------------------

    /**
     * Gets the value of the currentPiece attribute.
     * 
     * @return the currentPiece
     */
    public GamePiece getCurrentPiece() {
        return currentPiece;
    }

    /**
     * Sets the currentPiece attribute to the given value(s).
     * 
     * @param currentPiece
     *            the currentPiece to set
     */
    public void setCurrentPiece( GamePiece currentPiece ) {
        this.currentPiece = currentPiece;
    }

    /**
     * Gets the value of the score attribute.
     * 
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score attribute to the given value(s).
     * 
     * @param score
     *            the score to set
     */
    public void setScore( int score ) {
        this.score = score;
    }

    /**
     * Gets the value of the gameCanvas attribute.
     * 
     * @return the gameCanvas
     */
    public GameCanvas getDrawingCanvas() {
        return gameCanvas;
    }

    /**
     * Sets the gameCanvas attribute to the given value(s).
     * 
     * @param gameCanvas
     *            the gameCanvas to set
     */
    public void setDrawingCanvas( GameCanvas gameCanvas ) {
        this.gameCanvas = gameCanvas;
    }

    /**
     * Gets the value of the gameThreadPaused attribute.
     * 
     * @return the gameThreadPaused
     */
    public boolean isGameThreadPaused() {
        return gameThreadPaused;
    }

    /**
     * Sets the gameThreadPaused attribute to the given value(s).
     * 
     * @param gameThreadPaused
     *            the gameThreadPaused to set
     */
    public void setGameThreadPaused( boolean gameThreadPaused ) {
        this.gameThreadPaused = gameThreadPaused;
    }

    /**
     * Gets the value of the gameThreadStopped attribute.
     * 
     * @return the gameThreadStopped
     */
    public boolean isGameThreadStopped() {
        return gameThreadStopped;
    }

    /**
     * Sets the gameThreadStopped attribute to the given value(s).
     * 
     * @param gameThreadStopped
     *            the gameThreadStopped to set
     */
    public void setGameThreadStopped( boolean gameThreadStopped ) {
        this.gameThreadStopped = gameThreadStopped;
    }

    /**
     * Gets the value of the gameTable attribute.
     * 
     * @return the gameTable
     */
    public GameTable getGameTable() {
        return gameTable;
    }

    /**
     * Gets the value of the nextPiece attribute.
     * 
     * @return the nextPiece
     */
    public GamePiece getNextPiece() {
        return nextPiece;
    }

    /**
     * Gets the value of the speed attribute.
     * 
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets the value of the lastGameActionMovement attribute.
     * 
     * @return the lastGameActionMovement
     */
    public int getLastGameActionMovement() {
        return lastGameActionMovement;
    }

    /**
     * Sets the lastGameActionMovement attribute to the given value(s).
     * 
     * @param lastGameActionMovement
     *            the lastGameActionMovement to set
     */
    public void setLastGameActionMovement( int lastGameActionMovement ) {
        this.lastGameActionMovement = lastGameActionMovement;
    }

    /**
     * Gets the value of the nextLevelScore attribute.
     * 
     * @return the nextLevelScore
     */
    public int getNextLevelScore() {
        return nextLevelScore;
    }

    /**
     * Gets the value of the gameRunning attribute.
     * 
     * @return the gameRunning
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Gets the value of the gameThreadInGameOverMode attribute.
     *
     * @return the gameThreadInGameOverMode
     */
    public boolean isGameThreadInGameOverMode() {
        return gameThreadInGameOverMode;
    }
}
