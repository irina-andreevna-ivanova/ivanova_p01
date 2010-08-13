/**
 * 
 */
package zendo.playground.sse.preferencesapi;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

/**
 * 
 *
 * @author mocanu
 */
public class TestApi {

    public static void main( String[] args ) {
        Preferences pref = Preferences.userRoot();
        pref.addPreferenceChangeListener( new PreferenceChangeListener() {
            @Override
            public void preferenceChange( PreferenceChangeEvent evt ) {
                System.out.println( "PropChanged: " + evt.getKey() );
            }
        } );
        pref.putInt( "/myapp/mainframe/size", 500 );

        System.out.println( pref.getInt( "/myapps/mainframe/size", 100 ) );
        // look into Windows Registry ;)
    }

}
