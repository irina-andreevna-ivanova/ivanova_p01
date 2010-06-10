/**
 * 
 */
package zendo.playground.test2;

/**
 * 
 *
 * @author mocanu
 */
public class TestParamArray<T> {
    
    private T[] someArray;

    /**
     * 
     */
    @SuppressWarnings( "unchecked" )
    private TestParamArray() {
        someArray = (T[]) new Object[23];
    }
    
    public void set( int index, T value ) {
        someArray[ index ] = value;
    }

}
