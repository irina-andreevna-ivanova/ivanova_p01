/*- 
 * Copyright Bogdan Mocanu, 2009
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

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

    public int info( String source, Object... messages ) {
        StringBuilder builder = new StringBuilder();
        builder.append( "INFO [" ).append( source ).append( "]: " );
        for ( Object message : messages ) {
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
