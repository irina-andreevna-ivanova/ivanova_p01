/**
 * 
 */
package ro.bmocanu.zendo.tools;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Reads some data from the console. 
 *
 * @author mocanu
 */
public class ConsoleReader {
    
    public static int readInt( String message ) {
        System.out.print( message );
        DataInputStream dis = new DataInputStream( System.in );
        try {
            return dis.readInt();
        } catch ( IOException exception ) {
            exception.printStackTrace();
            return 0;
        }
    }

}
