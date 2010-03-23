/**
 * 
 */
package ro.bmocanu.test.jms.model;

/**
 * The response message for a calculation, containing the response of that calculation.
 * 
 * @author mocanu
 */
public class CalculationResponse {
    
    private int result;

    /**
     * 
     */
    public CalculationResponse() {
        super();
    }

    /**
     * @param result
     */
    public CalculationResponse(int result) {
        super();
        this.result = result;
    }

    /**
     * Returns the result
     *
     * @return the result
     */
    public int getResult() {
        return result;
    }

    /**
     * Sets the result to the given value.
     *
     * @param result the result to set
     */
    public void setResult( int result ) {
        this.result = result;
    }
    
}
