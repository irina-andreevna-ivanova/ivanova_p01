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

import static java.lang.Math.*;

import java.util.Random;

/**
 * Base class for the Diego algorithm implementation.
 * 
 * @author mocanu
 */
public abstract class AbstractAlgorithm {

    public Random randomNumberGenerator;
    public FileLogger log;
    public StateManager stateManager;
    public ResponseManager responseManager;

    // -------------------------------------------------------------------------------------------------

    protected static final double TWO_PI = Math.PI * 2;
    protected static final double PI_PER_2 = Math.PI / 2;

    public static final double distance( RealPoint p1, RealPoint p2 ) {
        double diffX = p1.x - p2.x;
        double diffY = p1.y - p2.y;
        return Math.sqrt( diffX * diffX + diffY * diffY );
    }

    /**
     * @param directionAngle
     *            the angle, in radians, of the current direction
     * @param distance
     *            the distance where the point should reside
     * @param rotation
     *            the rotation to be performed (counterclockwise=1, clockwise=-1)
     */
    public static final void movePointAt90degreesAndDistance( RealPoint point, RealPoint center, double directionAngle, double distance, int rotation ) {
        double finalAngle = directionAngle + PI_PER_2 * rotation;
        point.x = center.x + cos( finalAngle ) * distance;
        point.y = center.y + sin( finalAngle ) * distance;
    }

    // public static final double triangleArea( )

    public static final double upperBound( double value, double maxLimit ) {
        return (value < maxLimit) ? value : maxLimit;
    }

    public static final double limit( double value, double minLimit, double maxLimit ) {
        if ( value < minLimit ) {
            return minLimit;
        } else if ( value > maxLimit ) {
            return maxLimit;
        } else {
            return value;
        }
    }

    public static final double angleOf( RealPoint p1, RealPoint p2 ) {
        if ( p1.x == p2.x && p1.y > p2.y ) {
            return PI_PER_2;
        }
        if ( p1.x == p2.x && p1.y < p2.y ) {
            return 3 * PI_PER_2;
        }
        if ( p1.x <= p2.x && p1.y == p2.y ) {
            return 0;
        }
        if ( p1.x > p2.x && p1.y == p2.y ) {
            return PI;
        }
        if ( p1.x < p2.x && p1.y > p2.y ) {
            // Quarter 1
            return Math.atan( (p1.y - p2.y) / (p2.x - p1.x) );
        }
        if ( p1.x > p2.x && p1.y > p2.y ) {
            // Quarter 2
            return Math.atan( (p1.x - p2.x) / (p1.y - p2.y) ) + PI_PER_2;
        }
        if ( p1.x > p2.x && p1.y < p2.y ) {
            // Quarter 3
            return Math.atan( (p2.y - p1.y) / (p1.x - p2.x) ) + PI;
        }
        if ( p1.x < p2.x && p1.y < p2.y ) {
            // Quarter 4
            return Math.atan( (p2.x - p1.x) / (p2.y - p1.y) ) + 3 * PI_PER_2;
        }
        return 0;
    }

    public static void middleOf( RealPoint p1, RealPoint p2, RealPoint middle ) {
        middle.x = (p1.x + p2.x) / 2;
        middle.y = (p1.y + p2.y) / 2;
    }

    // -------------------------------------------------------------------------------------------------

    // public static void main( String[] args ) {
    // RealPoint p1 = new RealPoint( 100, 100 );
    // RealPoint p2 = new RealPoint( 150, 100 );
    //
    // int bigIndex = 0;
    //
    // for ( int index = 0; index < 50; index++ ) {
    // p2.y = p2.y - 1;
    // System.out.println( (bigIndex++) + " # " + angleOf( p1, p2 ) );
    // }
    // for ( int index = 0; index < 100; index++ ) {
    // p2.x = p2.x - 1;
    // System.out.println( (bigIndex++) + " # " + angleOf( p1, p2 ) );
    // }
    // for ( int index = 0; index < 100; index++ ) {
    // p2.y = p2.y + 1;
    // System.out.println( (bigIndex++) + " # " + angleOf( p1, p2 ) );
    // }
    // for ( int index = 0; index < 100; index++ ) {
    // p2.x = p2.x + 1;
    // System.out.println( (bigIndex++) + " # " + angleOf( p1, p2 ) );
    // }
    // for ( int index = 0; index < 50; index++ ) {
    // p2.y = p2.y - 1;
    // System.out.println( (bigIndex++) + " # " + angleOf( p1, p2 ) );
    // }
    // }

    // -------------------------------------------------------------------------------------------------

    public void init() {
    }

    public abstract void execute();

}
