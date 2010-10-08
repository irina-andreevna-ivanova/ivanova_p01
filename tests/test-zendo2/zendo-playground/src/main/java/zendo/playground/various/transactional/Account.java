package zendo.playground.various.transactional;

import java.io.Serializable;

/**
 * @author bogdan.mocanu
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private int value = 0;

    /**
     * @param value
     */
    public Account(int value) {
        this.value = value;
    }

    /**
     * Returns the value
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value to the given value.
     *
     * @param value the value to set
     */
    public void setValue( int value ) {
        this.value = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Account [value=" + value + "]";
    }

}
