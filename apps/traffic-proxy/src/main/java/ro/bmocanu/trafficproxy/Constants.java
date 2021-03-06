package ro.bmocanu.trafficproxy;

/**
 * Contains global constants used by all the other classes of the application.
 *
 * @author bogdan.mocanu-gmail.com 
 */
public class Constants {

    public static final String CONFIGURATION_FILE = "traffic-proxy.properties";
    public static final String LOG4J_FILE = "traffic-proxy.log4j.properties";

    public static final int PEER_SOCKET_TIMEOUT = 2000;
    public static final int PEER_PACKET_SIZE = 64 * 1024;
    public static final int INPUT_CONNECTOR_SOCKET_TIMEOUT = 2000;
    
    public static final int CORE_THREAD_IDLE_TIMEOUT_MLS = 50;
    public static final String PROTOTYPE = "prototype";

}
