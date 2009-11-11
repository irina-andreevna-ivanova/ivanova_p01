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

package zendo.playground.trigo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author mocanu
 */
public class TestFrame extends JFrame implements MouseListener, MouseMotionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private int xStart;
    private int yStart;

    private int xEnd = 400;
    private int yEnd = 250;
    private int theAngle = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint( Graphics grp ) {
        Image img = this.createImage( WIDTH, HEIGHT );
        
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        g.drawLine( xStart, yStart, xEnd, yEnd );

        int[] xPoints = new int[] { xEnd + 20, xEnd + 200, xEnd + 200, xEnd + 200, xEnd + 20 };
        int[] yPoints = new int[] { yEnd, yEnd - 200, yEnd, yEnd + 200, yEnd };

        int square = 0;
        if ( xStart < xEnd && yStart < yEnd )
            square = 0; // Top-Left
        if ( xStart > xEnd && yStart < yEnd )
            square = 1; // Top-Right
        if ( xStart < xEnd && yStart > yEnd )
            square = 3; // Bottom-Left
        if ( xStart > xEnd && yStart > yEnd )
            square = 2; // Bottom-Right

        int alphaAdd = 90 * square;
        double alphaRad = alphaAdd * Math.PI / 180;
        double angle = Math.asin( Math.abs( xEnd - xStart )
                                  / (Math.sqrt( (xEnd - xStart) * (xEnd - xStart) + (yEnd - yStart) * (yEnd - yStart) )) );
        if ( square % 2 == 0 ) {
            angle = Math.toRadians( 90 ) - angle;
        }
        angle += alphaRad;

        // rotation
        for ( int index = 0; index < xPoints.length; index++ ) {
            int currentX = xPoints[index];
            int currentY = yPoints[index];
            xPoints[index] = (int) Math.round( xEnd + (xEnd - currentX) * Math.cos( angle ) - (yEnd - currentY)
                                               * Math.sin( angle ) );
            yPoints[index] = (int) Math.round( yEnd + (xEnd - currentX) * Math.sin( angle ) + (yEnd - currentY)
                                               * Math.cos( angle ) );
        }

        g.fillPolygon( xPoints, yPoints, 4 );
        
        grp.drawImage( img, 0, 0, WIDTH, HEIGHT, null );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged( MouseEvent e ) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved( MouseEvent e ) {
        xStart = e.getX();
        yStart = e.getY();
        repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked( MouseEvent e ) {
        theAngle += 10;
        repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered( MouseEvent e ) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited( MouseEvent e ) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed( MouseEvent e ) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased( MouseEvent e ) {

    }

    public static void main( String[] args ) {
        TestFrame frame = new TestFrame();
        frame.addMouseListener( frame );
        frame.addMouseMotionListener( frame );
        frame.setSize( WIDTH, HEIGHT );
        frame.setLocationRelativeTo( null );
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setVisible( true );
    }

}
