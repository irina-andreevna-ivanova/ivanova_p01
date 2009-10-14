/**
 * 
 */
package zendo.playground.quizzes;

public class TestQ12 {
    
    public static void main( String[] args ) {
        String[] valuesOfTruth = new String[] { "true", "true", "true", "false", "true" };
        for( int index = 0; index < valuesOfTruth.length; ) {
            if ( Boolean.getBoolean( valuesOfTruth[ index ] ) ) {
                Systems.out.println( "TRUE" );
                index = index++;
            } else {
                // skip the next value
                index = index++ + 1;
            }
        }
    }

}
