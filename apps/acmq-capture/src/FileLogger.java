import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;


/**
 * 
 */

/**
 * Simple file logger, used for debugging purposes.
 *
 * @author mocanu
 */
public class FileLogger {
    
    private static final String LOG_FILE = "../target/diego_log_" + System.currentTimeMillis() + ".txt";
    
    private PrintWriter writer;
    
    public void init() throws IOException {
        File logFile = new File( LOG_FILE );
        if ( !logFile.exists() ) {
            if ( !logFile.createNewFile() ) {
                throw new IOException( "Cannot create the file " + LOG_FILE );
            }
        }
        
        OutputStream outputStream = new FileOutputStream( logFile );
        writer = new PrintWriter( outputStream );
    }
    
    public void close() {
        if ( writer != null ) {
            writer.close();
        }
    }
    
    // ------------------------------------------------------------------------------------------------------
    
    public int info( String source, Object ... messages ) {
        StringBuilder builder = new StringBuilder();
        builder.append( "INFO [" ).append( source ).append( "]: " );
        for( Object message : messages ) {
            builder.append( message ).append( " - " );
        }
        
        builder.append( "#\n" );
        writer.write( builder.toString() );
        writer.flush();
        
        return 0;
    }
    
    public void logException( Throwable throwable ) {
        throwable.printStackTrace( writer );
    }

}
