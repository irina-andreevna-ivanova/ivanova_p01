/**
 * 
 */
package zendo.playground.various;

/**
 * 
 *
 * @author mocanu
 */
public class Class001 {

    public static void main( String[] args ) {
        B b = new B();
        b.print();
        ((A)b).print();
    }

}

class A {
    public static void print() {
        System.out.println( "A" );
    }
}

class B extends A {
    public static void print() {
        System.out.println( "B" );
    }
}
