package zendo.playground.dpatterns.memento;

/**
 * @author bogdan.mocanu
 */
public class Client {

    public static void main( String[] args ) {
        Originator orig = new Originator( 100, 200 );
        System.out.println( "State1: " + orig.getPosX() );

        OriginatorState state1 = orig.getState();

        orig.setPosX( 555 );
        System.out.println( "State2: " + orig.getPosX() );

        System.out.println( "Reverting to state1" );
        orig.revertToState( state1 );

        System.out.println( "Current state: " + orig.getPosX() );
    }

}
