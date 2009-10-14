/**
 * 
 */
package zendo.playground.various;

/**
 * 
 *
 * @author mocanu
 */
public class TestConstructors2 {
    
    public TestConstructors2( String s, Object ... vars ) {
        System.out.println( "meth1" );   
    }
    
    public TestConstructors2( String s, String t, Object ... vars ) {
        System.out.println( "meth2" );
    }
    
    public static void main( String[] args ) {
        TestConstructors2 tc2 = new TestConstructors2( "asdasd", "asdasd");
    }

}
