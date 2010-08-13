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
public class Sheep implements Cloneable {

    private String name;

    private Wool wool;

    /**
     * Returns the name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the given value.
     * 
     * @param name
     *            the name to set
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Returns the wool
     * 
     * @return the wool
     */
    public Wool getWool() {
        return wool;
    }

    /**
     * Sets the wool to the given value.
     * 
     * @param wool
     *            the wool to set
     */
    public void setWool( Wool wool ) {
        this.wool = wool;
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
        Sheep clone = (Sheep) super.clone();
        clone.setWool( (Wool) wool.clone() );
        return clone;
    }

}
