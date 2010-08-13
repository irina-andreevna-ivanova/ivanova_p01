/**
 * 
 */
package zendo.playground.sse.cloning;

import org.apache.commons.lang.builder.ToStringBuilder;

import ro.bmocanu.zendo.material.MaterialConstants;

/**
 * 
 * 
 * @author mocanu
 */
public class Wool implements Cloneable {

    private long quantity;

    /**
     * Returns the quantity
     * 
     * @return the quantity
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity to the given value.
     * 
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity( long quantity ) {
        this.quantity = quantity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "\n" + ToStringBuilder.reflectionToString( this, MaterialConstants.TO_STRING_STYLE, true );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
