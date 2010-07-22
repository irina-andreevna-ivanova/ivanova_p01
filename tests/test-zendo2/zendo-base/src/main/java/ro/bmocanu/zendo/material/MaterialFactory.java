/**
 * 
 */
package ro.bmocanu.zendo.material;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 
 * @author mocanu
 */
public class MaterialFactory {

    private static final String[] NAMES = { "Abdul", "Aram", "Baksh", "Atalis", "Bakra", "Banaric", "Ghurran", "Hamar",
                                           "Kur", "Hormaz", "Jamal", "Jelal", "Khalid", "Mulai", "Munthassen", "Murad",
                                           "Shapur", "Tavik", "Tureg", "Vardan", "Yezdigerd", "Yildiz", "Zosara" };

    private static final String[] ADDRESS_PREFIXES = { "Str.", "Blvd.", "Cv.", "St.", "Rue" };

    private static final Random RANDOM_GENERATOR = new Random();
    private static final AtomicInteger CONSECUTIVE_INT = new AtomicInteger( 0 );

    public static String randomName() {
        return randomArrayElem( NAMES );
    }
    
    public static String randomAddress() {
        return randomArrayElem( ADDRESS_PREFIXES ) + " " + randomArrayElem( NAMES ) + ", Nr. " + randomNr( 20, 200 );
    }
    
    public static <T> T randomArrayElem( T[] array ) {
        return array[RANDOM_GENERATOR.nextInt( array.length )];
    }

    public static int randomNr( int min, int max ) {
        return RANDOM_GENERATOR.nextInt( max - min ) + min;
    }

    public static String randomSID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll( "-", "" );
    }

    public static int nextConsecutiveInt() {
        return CONSECUTIVE_INT.incrementAndGet();
    }

    public static Date randomDate() {
        int oneYearInMillis = 365 * 86400 * 1000;
        return new Date( System.currentTimeMillis() - randomNr( 1000, oneYearInMillis ) );
    }

}
