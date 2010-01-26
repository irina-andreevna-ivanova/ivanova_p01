/**
 * 
 */
package zendo.playground.various;

import java.io.File;
import java.io.IOException;

/**
 * @author mocanu
 */
public class TestFile22 {

    public static void main( String[] args ) throws IOException {
        File f = new File( "G:/My Temp Files/something/txt.txt" );
        System.out.println( f.getParentFile() );
        
        File ff = f.getParentFile();
        ff.mkdirs();
        
        f.createNewFile();
        
    }
}
