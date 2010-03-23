/**
 * 
 */
package ro.bmocanu.test.jms.model;

/**
 * The request message sent for a calculation.
 * 
 * @author mocanu
 */
public class CalculationRequest {

    private int numberA;
    private int numberB;
    private Operation operation;

    // ------------------------------------------------------------------------------------------------------

    /**
     * 
     */
    public CalculationRequest() {
        super();
    }

    /**
     * @param numberA
     * @param numberB
     * @param operation
     */
    public CalculationRequest(int numberA, int numberB, Operation operation) {
        super();
        this.numberA = numberA;
        this.numberB = numberB;
        this.operation = operation;
    }

    // ------------------------------------------------------------------------------------------------------s

    /**
     * Returns the numberA
     * 
     * @return the numberA
     */
    public int getNumberA() {
        return numberA;
    }

    /**
     * Sets the numberA to the given value.
     * 
     * @param numberA
     *            the numberA to set
     */
    public void setNumberA( int numberA ) {
        this.numberA = numberA;
    }

    /**
     * Returns the numberB
     * 
     * @return the numberB
     */
    public int getNumberB() {
        return numberB;
    }

    /**
     * Sets the numberB to the given value.
     * 
     * @param numberB
     *            the numberB to set
     */
    public void setNumberB( int numberB ) {
        this.numberB = numberB;
    }

    /**
     * Returns the operation
     * 
     * @return the operation
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Sets the operation to the given value.
     * 
     * @param operation
     *            the operation to set
     */
    public void setOperation( Operation operation ) {
        this.operation = operation;
    }

}
