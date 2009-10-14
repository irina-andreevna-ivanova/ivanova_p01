/**
 * 
 */
package zendo.playground.various;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 *
 * @author mocanu
 */
public class DateTests {
    
    public static void main2( String[] args ) {
        
        Date simpleDate = new Date();
        System.out.println( simpleDate.getTime() );
        System.out.println( simpleDate );
        
        //System.out.println( Arrays.toString( TimeZone.getAvailableIDs() ) );

        TimeZone.setDefault( TimeZone.getTimeZone( "UTC" ) );
        
        Calendar c = Calendar.getInstance( TimeZone.getTimeZone( "UTC" ) );
        Date utcDate = c.getTime();
        //System.out.println( SimpleDateFormat.getDateTimeInstance().format( utcDate ) );
        System.out.println( utcDate.getTime() );
        System.out.println( utcDate );
        
        Calendar calendar = Calendar.getInstance( TimeZone.getTimeZone( "UTC" ) );
        calendar.setTime(utcDate);
        System.out.println( utcDate.getTime() );
        System.out.println( calendar.getTimeInMillis() );
        
    }
    
    public static void main( String[] args ) {
        Date simpleDate = new Date();
        System.out.println( simpleDate.getTime() );
        System.out.println( simpleDate );
        
        TimeZone.setDefault( TimeZone.getTimeZone( "UTC" ) );
        
        Date secondDate = new Date();
        System.out.println( secondDate.getTime() );
        System.out.println( secondDate );
        
    }

}
