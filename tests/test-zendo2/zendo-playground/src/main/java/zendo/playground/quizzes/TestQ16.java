/**
 * 
 */
package zendo.playground.quizzes;

import java.io.IOException;

/**
 * 
 * 
 * @author mocanu
 */
public class TestQ16 {

    public static void main( String[] args ) {
        try {
            if ( 2 > 3 ) {
                throw new IOException();
            }
            doSomething();
        } catch ( IOException exception ) {
            System.out.println( "*" );
        }
    }

    public static void doSomething() {
        class MyClass<T extends Throwable> {
            public void throwT( Throwable t ) {
                new MyClass<Error>().innerThrow( t );
            }

            @SuppressWarnings( "unchecked" )
            private void innerThrow( Throwable t ) throws T {
                throw (T) t;
            }
        }
        MyClass<IOException> thrower = new MyClass<IOException>();
        thrower.throwT( new IOException( "test" ) );
    }

}


// public class TestQ16 {

// public static void main( String[] args ) {
// try {
// if ( 2 > 3 ) {
// throw new IOException();
// }
// doSomething();
// } catch ( IOException exception ) {
// System.out.println( "*" );
// }
// }
//
// public static void doSomething() {
// Thread.currentThread().stop( new IOException( "Boo" ) );
// class MyClass {
// public MyClass() throws IOException{
// throw new IOException();
// }
// }
//
// }

// public static void main( String[] args ) {
// try {
// if ( 2 > 3 ) {
// throw new IOException();
// }
// doSomething();
// } catch ( IOException exception ) {
// System.out.println( "*" );
// }
// }
//    
// public static void doSomething() {
// //
// //
// //
// //
// try {
// MyClass.class.newInstance();
// } catch ( InstantiationException exception ) {
// exception.printStackTrace();
// } catch ( IllegalAccessException exception ) {
// exception.printStackTrace();
// }
// }
//    
// static class MyClass {
//        
// public MyClass() throws IOException {
// throw new IOException();
// }
//        
// }

