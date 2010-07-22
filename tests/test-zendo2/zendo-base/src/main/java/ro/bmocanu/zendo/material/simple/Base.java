/**
 * 
 */
package ro.bmocanu.zendo.material.simple;

import org.apache.commons.lang.builder.ToStringBuilder;

import ro.bmocanu.zendo.material.MaterialConstants;

/**
 * Base class for all simple material classes.
 * 
 * @author mocanu
 */
public class Base {

    private long id;

    /**
     * Returns the id
     * 
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id to the given value.
     * 
     * @param id
     *            the id to set
     */
    public void setId( long id ) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "\n" + ToStringBuilder.reflectionToString( this, MaterialConstants.TO_STRING_STYLE, true );
    }

}
