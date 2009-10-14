/**
 * 
 */
package zendo.playground.quizzes;

/**
 * 
 *
 * @author mocanu
 */
public class TestQ13 {
    
    public static void method1( float nr ) {
        System.out.println( "method1: " + nr );
        method2( nr / 2D );
    }

    public static void method1( double nr ) {
        System.out.println( "method111: " + nr );
    }

    public static void method2( double nr ) {
        System.out.println( "method2: " + nr );
        method1( (float)(nr + 1) );
    }
    
    public static void main( String[] args ) {
        //method2( Math.PI );
        System.out.println( ((Object)( 3F / 4D )).getClass() );
    }

}
