/**
 * 
 */
package zendo.playground.sse.serialization.model;

import java.io.Serializable;

/**
 * 
 * 
 * @author mocanu
 */
public class Wheel implements Serializable {

    private String model;
    private int size;

    /**
     * Returns the model
     * 
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model to the given value.
     * 
     * @param model
     *            the model to set
     */
    public void setModel( String model ) {
        this.model = model;
    }

    /**
     * Returns the size
     * 
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size to the given value.
     * 
     * @param size
     *            the size to set
     */
    public void setSize( int size ) {
        this.size = size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Wheel [model=" );
        builder.append( model ).append( "\n" );
        builder.append( ", size=" );
        builder.append( size ).append( "\n" );
        builder.append( "]" );
        return builder.toString();
    }

}
