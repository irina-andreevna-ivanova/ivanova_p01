/**
 * 
 */
package zendo.playground.quizzes;


/**
 * 
 *
 * @author mocanu
 */
public class TestQ16Final {
    
    public static void main( String[] args ) {
        ExtendedClass ec = new ExtendedClass();
    }

}

// not changeable
abstract class BaseClass {
    public BaseClass( int i ) {
        System.out.println( "BaseClass: i=" + i );
    }
}

class ExtendedClass extends BaseClass {
    private static int arg;

    // required constructor
    public ExtendedClass() {
        super( arg = SomeClass.getValue() );
        //arg = SomeClass.getValue();
        System.out.println( "ExtendedClass: arg=" + arg );
    }
    
}

class SomeClass {
    public static int getValue() {
        return 2;/* something random each time */
    }
}

//class BaseClass {
//    public BaseClass( int i ) {
//        System.out.println( "BaseClass: i=" + i );
//    }
//}
//
//class ExtendedClass extends BaseClass {
//    private final int arg;
//
//    public ExtendedClass() {
//        this( SomeClass.getValue() );
//        System.out.println( "ExtendedClass: arg=" + arg );
//    }
//    
//    public ExtendedClass( int i ) {
//        super( i );
//        arg = i;
//    }
//}
//
//class SomeClass {
//    public static int getValue() {
//        return 2;/* something random each time */
//    }
//}
