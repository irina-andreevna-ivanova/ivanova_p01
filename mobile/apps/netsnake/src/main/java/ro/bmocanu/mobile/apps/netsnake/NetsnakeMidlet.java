/*- 
 * Copyright Bogdan Mocanu, 2009
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
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

package ro.bmocanu.mobile.apps.netsnake;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * @author mocanu
 */
public class NetsnakeMidlet extends MIDlet {

    private Display currentDisplay;

    private GameCanvas gameCanvas;

    /**
     * {@inheritDoc}
     */
    protected void startApp() throws MIDletStateChangeException {
        if ( currentDisplay == null ) {
            gameCanvas = new GameCanvas();

            currentDisplay = Display.getDisplay( this );
            currentDisplay.setCurrent( gameCanvas );
        }
    }

    /**
     * {@inheritDoc}
     */
    protected void pauseApp() {

    }

    /**
     * {@inheritDoc}
     */
    protected void destroyApp( boolean arg0 ) throws MIDletStateChangeException {
        gameCanvas.setDropDead( true );
        gameCanvas.waitToDie();
    }

}
