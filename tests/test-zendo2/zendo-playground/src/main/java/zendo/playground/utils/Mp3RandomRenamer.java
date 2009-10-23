package zendo.playground.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Randomly renames MP3 files using {@link UUID} as source for names.
 *
 * @author mocanu
 */
public class Mp3RandomRenamer {
    private static Log log = LogFactory.getLog( Mp3RandomRenamer.class );

    private static final String TARGET_FOLDER = "G:/My Temp Files/mp3player/Spanish Guitar/Spanish Guitar Flamenco";

    public static void main( String[] args ) {
        File targetFolder = new File( TARGET_FOLDER );
        if ( !targetFolder.exists() ) {
            log.error( "Cannot find the TARGET_FOLDER=" + TARGET_FOLDER );
            System.exit( 1 );
        }

        File[] fileArray = targetFolder.listFiles( new FilenameFilter() {
            @Override
            public boolean accept( File dir, String name ) {
                return (name.toUpperCase().endsWith( "MP3" ));
            }
        } );

        for( File file : fileArray ) {
            log.info( "Processing " + file.getAbsolutePath() );
            String newFileName = TARGET_FOLDER + "/" + generateNewRandomName() + ".mp3";
            log.info( "New file name: " + newFileName );

            if ( !file.renameTo( new File( newFileName ) ) ) {
                log.error( "Failed renaming file " + file.getAbsolutePath() );
            }
        }

        log.info( "Done!" );
    }

    private static String generateNewRandomName() {
        String uuidStr = UUID.randomUUID().toString();
        return uuidStr.replaceAll( "-", "" );
    }

}