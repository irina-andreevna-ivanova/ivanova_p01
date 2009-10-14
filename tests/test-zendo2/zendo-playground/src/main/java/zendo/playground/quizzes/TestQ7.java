package zendo.playground.quizzes;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @author bogdan.mocanu
 */
public class TestQ7 {

    public static void main( String[] args ) throws Exception {
        Method[] methods = MyDummyClass.class.getMethods();
        for( Method currentMethod : methods ) {
            if ( "secondMethod".equals( currentMethod.getName() ) ) {
                if ( currentMethod.isAnnotationPresent( LoveThisMethod.class ) ) {
                    Annotation theAnnotation = currentMethod.getAnnotation( LoveThisMethod.class );
                    //System.out.println( LoveThisMethod.class.getMethod( "value" ).invoke( theAnnotation ) );
                    Systems.out.println( ((LoveThisMethod) theAnnotation).value() );
                }
            }
        }
    }

}

class MyDummyClass {

    public void firstMethod() {
    }

    @LoveThisMethod( "I really do" )
    public void secondMethod() {
    }

}

@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
@interface LoveThisMethod {
    public String value();
}
