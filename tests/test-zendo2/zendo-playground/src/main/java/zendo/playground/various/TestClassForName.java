/**
 * 
 */
package zendo.playground.various;


/**
 * 
 *
 * @author mocanu
 */
public class TestClassForName {

    public static void main( String[] args ) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName( "sun.net.www.protocol.mydb.MyDbURLConnection" );
        Object inst = clazz.newInstance();
        System.out.println( "Done: " + inst );
    }
    
}
