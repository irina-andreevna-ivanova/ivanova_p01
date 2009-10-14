package zendo.playground.dpatterns.memento;

/**
 * @author bogdan.mocanu
 */
public class Originator {

    private int posX;

    private int posY;

    public Originator( int posX, int posY ) {
        this.posX = posX;
        this.posY = posY;
    }

    public OriginatorState getState() {
        OriginatorInternalState state = new OriginatorStateImpl();
        state.setPosX( posX );
        state.setPosY( posY );

        return (OriginatorState)state;
    }

    public void revertToState( OriginatorState state ) {
        OriginatorInternalState internalState = (OriginatorInternalState) state;
        posX = internalState.getPosX();
        posY = internalState.getPosY();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX( int posX ) {
        this.posX = posX;
    }

    public void setPosY( int posY ) {
        this.posY = posY;
    }
}
