/**
 * 
 */
package zendo.playground.various.ju1;

import java.lang.reflect.TypeVariable;


/**
 * 
 *
 * @author mocanu
 */
public class TestGenerics {
    
    public static void main( String[] args ) {
        MyClass<Integer, Long> test = new MyClass<Integer, Long>();
        for( TypeVariable tv : test.getClass().getTypeParameters() ) {
            System.out.println( tv.getGenericDeclaration().getTypeParameters() );
        }
    }

}

class MyClass<T, S> {
    
}