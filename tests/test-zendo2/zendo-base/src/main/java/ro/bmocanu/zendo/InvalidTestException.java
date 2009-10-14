package ro.bmocanu.zendo;

/**
 * This exception is thrown when the test passed to the Zendo core for testing is malformed.
 *
 * @author bogdan.mocanu
 */
public class InvalidTestException extends RuntimeException {
    private static final long serialVersionUID = -5308742924158881133L;

    public InvalidTestException() {
    }

    public InvalidTestException( Throwable cause ) {
        super( cause );
    }

    public InvalidTestException( String message ) {
        super( message );
    }

    public InvalidTestException( String message, Throwable cause ) {
        super( message, cause );
    }
}
