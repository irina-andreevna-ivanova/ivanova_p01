/**
 * 
 */
package zendo.playground.test1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;

/**
 * 
 *
 * @author mocanu
 */
public class TestParamNames {
    
    public void meth() throws RuntimeException {
        
    }
    
    public static void main( String[] args ) {
        Class commonsLog = Log.class;
        Method[] methods = commonsLog.getMethods();
        
        for( Method method : methods ) {
            System.out.println( method.getName() );
            Annotation[][] annsss = method.getParameterAnnotations();
            for( Annotation[] anns : annsss ) {
                System.out.println( ">> " + anns.length );
                for( Annotation ann : anns ) {
                    System.out.println( ">> " + ann.toString() );
                }
            }
        }
    }

}
