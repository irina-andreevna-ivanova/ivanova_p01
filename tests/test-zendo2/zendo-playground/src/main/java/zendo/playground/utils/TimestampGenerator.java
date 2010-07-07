/**
 *
 */
package zendo.playground.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

        String date = "22.10.1980";
        System.out.println( date + " = " + new SimpleDateFormat( "dd.MM.yyyy" ).parse( date ).getTime() );

        long timestampSource = 1268044845388L;
        displayTimestamp( timestampSource );

        System.out.println( "Misc tests-----------" );

        System.out.println( new SimpleDateFormat( "dd.MM.yyyy" ).parse( "8.03.2008" ).getTime() );
        System.out.println( new SimpleDateFormat( "dd.MM.yyyy" ).parse( "10.03.2008" ).getTime() );
        System.out.println( new SimpleDateFormat( "dd.MM.yyyy" ).parse( "12.03.2008" ).getTime() );
        System.out.println( new SimpleDateFormat( "dd.MM.yyyy" ).parse( "10.05.2008" ).getTime() );
    }

    // ------------------------------------------------------------------------------------------------------

    public static void displayTimestamp( long timestamp ) {
        Date date = new Date( timestamp );
        Calendar calendarUTC = Calendar.getInstance();
        calendarUTC.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
        calendarUTC.setTime( date );

        Calendar calendarHere = Calendar.getInstance();
        calendarHere.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
        calendarHere.setTime( date );

        SimpleDateFormat dateFormatUTC = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss Z" );
        dateFormatUTC.setTimeZone( TimeZone.getTimeZone( "UTC" ) );

        SimpleDateFormat dateFormatHere = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss Z" );
        // dateFormatHere.setTimeZone( TimeZone.getTimeZone( "UTC" ) );

        System.out.println( "+--------------------------------------------" );
        System.out.println( "  Timestamp : " + timestamp + "\n" );
        System.out.println( "  Date UTC  : " + dateFormatUTC.format( date ) );
        System.out.println( "  Date here : " + dateFormatHere.format( date ) );

    }

}
