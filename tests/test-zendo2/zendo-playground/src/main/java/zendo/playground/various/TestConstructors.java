/**
 * 
 */
package zendo.playground.various;

/**
 * 
 *
 * @author mocanu
 */
public class TestConstructors {
    
    public static void main( String[] args ) {
        B100 a = new B100();
    }

}

class A100 {

    /**
     * 
     */
    public A100() {
        System.out.println( "Inside constructor from A100" );
    }
    
}

class B100 extends A100 {

    public B100() {
        System.out.println( "Inside constructor from B100" );
    }
    
}
