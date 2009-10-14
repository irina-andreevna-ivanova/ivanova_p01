package zendo.playground.quizzes;

/**
 * @author bogdan.mocanu
 */
public class TestQ8 {
    public static Integer parseInt( String s ) {
        return (s == null) ? null : Integer.parseInt( s );
        //System.out.println( s == null ? "asdasd" : new Integer( 2 ) );
    }

    public static void main( String[] args ) {
        Systems.out.println( parseInt( "-1" ) + " " +
                            parseInt( null ) + " " +
                            parseInt( "1" ) );
    }
}
