package zendo.playground.dpatterns.memento;

/**
 * @author bogdan.mocanu
 */
public class OriginatorStateImpl implements OriginatorState, OriginatorInternalState {

    private int posX;

    private int posY;

    public int getPosX() {
        return posX;
    }

    public void setPosX( int posX ) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY( int posY ) {
        this.posY = posY;
    }
}
