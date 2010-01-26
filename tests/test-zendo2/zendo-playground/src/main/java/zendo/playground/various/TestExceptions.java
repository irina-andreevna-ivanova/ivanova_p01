/**
 * 
 */
package zendo.playground.various;

/**
 * @author mocanu
 */
public class TestExceptions {

    public static void meth2() throws MyException2 {
        try {
            throw new NullPointerException( "assddddddd" );
        } catch ( Exception e ) {
            throw new MyException2( "asdasd", e );
        }
    }

    public static void meth1() throws MyException1 {
        try {
            meth2();
        } catch ( MyException2 e ) {
            throw new MyException1( "asdasd", e );
        }
    }

    public static void main( String[] args ) {
        try {
            meth1();
        } catch ( MyException1 e ) {
            Throwable cursor = e;
            while ( cursor != null ) {
                System.out.println( cursor.getClass().getName() );
                cursor = cursor.getCause();
            }
        }
    }
}

// ------------------------------------------------------------------------------------------------------

class MyException1 extends Exception {

    /**
     * 
     */
    public MyException1() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public MyException1(String message, Throwable cause) {
        super( message, cause );
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public MyException1(String message) {
        super( message );
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public MyException1(Throwable cause) {
        super( cause );
        // TODO Auto-generated constructor stub
    }

}

class MyException2 extends Exception {

    /**
     * 
     */
    public MyException2() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public MyException2(String message, Throwable cause) {
        super( message, cause );
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public MyException2(String message) {
        super( message );
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public MyException2(Throwable cause) {
        super( cause );
        // TODO Auto-generated constructor stub
    }

}
