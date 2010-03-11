package ro.bmocanu.trafficproxy;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Set;

/**
 * Class that contains static fields containing the configuration values loaded from the configuration file.
 */
public class Configuration {

    public static final String MODE_CLIENT = "client";
    public static final String MODE_SERVER = "server";

    public static String corePeerMode;
    public static int corePeerPort;
    public static String corePeerAddress;

    // -------------------------------------------------------------------------------------------------
    private static final Logger LOG = Logger.getLogger(Configuration.class);

    public static void loadConfiguration() {
        try {
            LOG.info("Loading the configuration options from " + Constants.CONFIGURATION_FILE);
            Properties properties = new Properties();
            properties.load(Configuration.class.getClassLoader().getResourceAsStream(Constants.CONFIGURATION_FILE));

            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                String name = (String) key;
                String value = properties.getProperty(name);

                String[] parts = StringUtils.split(name, '.');
                StringBuilder nameBuilder = new StringBuilder(parts[0]);

                for (int index = 1; index < parts.length; index++) {
                    parts[index] = StringUtils.capitalize(parts[index]);
                    nameBuilder.append(parts[index]);
                }

                Field field = Configuration.class.getDeclaredField( nameBuilder.toString() );
                if ( field.getType().equals( int.class ) ) {
                    field.setInt( Configuration.class, Integer.parseInt( value ) );
                } else {
                    field.set( Configuration.class, value );
                }
            }

            LOG.info("Finished loading the options");
        } catch (Exception exception) {
            LOG.error("Cannot load the configuration options from " + Constants.CONFIGURATION_FILE, exception);
        }
    }

}
