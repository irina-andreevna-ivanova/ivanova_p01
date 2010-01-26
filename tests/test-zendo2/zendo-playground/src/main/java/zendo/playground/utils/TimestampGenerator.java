/**
 *
 */
package zendo.playground.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 * @author mocanu
 */
public class TimestampGenerator {

    public static void main( String[] args ) throws ParseException {
        Date now = new Date();
        System.out.println( "Date     : " + now );
        System.out.println( "Timestamp: " + now.getTime() );

        System.out.println( new SimpleDateFormat( "dd.MM.yyyy" ).parse( "22.05.2015" ).getTime() );

        long timestampSource = 1248186224492L;
        now = new Date( timestampSource );
        System.out.println( now );
        
        System.out.println( "Misc tests-----------" );
        
        System.out.println( new SimpleDateFormat( "dd.MM.yyyy" ).parse( "8.03.2008" ).getTime() );
        System.out.println( new SimpleDateFormat( "dd.MM.yyyy" ).parse( "10.03.2008" ).getTime() );
        System.out.println( new SimpleDateFormat( "dd.MM.yyyy" ).parse( "12.03.2008" ).getTime() );
        System.out.println( new SimpleDateFormat( "dd.MM.yyyy" ).parse( "10.05.2008" ).getTime() );
        
    }

}
