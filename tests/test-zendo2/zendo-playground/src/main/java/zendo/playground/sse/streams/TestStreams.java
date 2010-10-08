package zendo.playground.sse.streams;

import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bogdan.mocanu
 */
public class TestStreams {

    private static final Log log = LogFactory.getLog( TestStreams.class );

    public static void main( String[] args ) throws MoreElevatedException {
        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader( "xanadu.txt" );
            outputStream = new FileWriter( "characteroutput.txt" );

            int c;
            while ( (c = inputStream.read()) != -1 ) {
                outputStream.write( c );
            }
        } catch ( IOException exception ) {
            throw new MoreElevatedException( exception );
        } finally {
            if ( inputStream != null ) {
                try {
                    inputStream.close();
                } catch ( IOException exception ) {
                    log.error( exception );
                }
            }
            if ( outputStream != null ) {
                try {
                    outputStream.close();
                } catch ( IOException exception ) {
                    log.error( exception );
                }
            }
        }
    }

}
