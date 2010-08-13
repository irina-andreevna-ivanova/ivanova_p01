/**
 * 
 */
package zendo.playground.sse.cloning;



/**
 * 
 * 
 * @author mocanu
 */
public class TestCloning {
    public static void main( String[] args ) throws CloneNotSupportedException {
        Wool wool = new Wool();
        wool.setQuantity( 100 );

        BlackSheep sheep = new BlackSheep();
        sheep.setName( "Dolly" );
        sheep.setWool( wool );
        sheep.setDegreeOfBlack( 25 );

        System.out.println( "Before cloning: " + sheep );

        Sheep sheep2 = (Sheep) sheep.clone();
        System.out.println( "Clone: " + sheep2 );
    }
}
