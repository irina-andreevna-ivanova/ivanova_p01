/**
 * 
 */
package zendo.playground.various;

import ro.bmocanu.zendo.material.simple.Club;
import ro.bmocanu.zendo.material.simple.SimpleMaterialFactory;

/**
 * 
 * 
 * @author mocanu
 */
public class TestMaterial {
    public static void main( String[] args ) {
        Club club = SimpleMaterialFactory.createClubRandom();
        System.out.println( club );

    }
}
