package zendo;



/**
 * @author bogdan.mocanu
 */
public class TestWeirdSendMail {

    protected static void sendMail( String jarFile ) throws Exception {
        //File f = new File( jarFile );
        //String af = f.getAbsolutePath();
        String s = "mailto:bogdan.mocanu@gmail.com?subject=Support&Body=\"Problem Description\"";
        Runtime.getRuntime().exec( "explorer.exe \"" + s + "\"" );
    }

    public static void main( String[] args ) throws Exception {
        sendMail( "package.properties" );
        //Set<String> s = null;
        //s.toArray( new String[ s.size() ] ); 
    }

}
