/**
 * 
 */
package zendo.playground.various;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * @author mocanu
 */
public class TestAnnotations {

    public static void main( String[] args ) {
        TestAnnotationsAnnotatedClass obj = new TestAnnotationsAnnotatedClass();
        TestAnnotationsAnnotation annot = obj.getClass().getAnnotation( TestAnnotationsAnnotation.class );
        System.out.println( annot.value() );
    }

}

@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
@interface TestAnnotationsAnnotation {

    public String value();

}

@TestAnnotationsAnnotation( "duffy" )
class TestAnnotationsAnnotatedClass {

}
