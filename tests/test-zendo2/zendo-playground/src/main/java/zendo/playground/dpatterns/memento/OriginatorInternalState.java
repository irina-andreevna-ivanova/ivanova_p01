package zendo.playground.dpatterns.memento;

/**
 * This is the internal wide interface, that the Originators are using in order to change the memento.
 *
 * @author bogdan.mocanu
 */
public interface OriginatorInternalState {

    int getPosX();

    void setPosX( int posX );

    int getPosY();

    void setPosY( int posY );
}
