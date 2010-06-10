/**
 * 
 */
package zendo.playground.http;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * 
 *
 * @author mocanu
 */
public class TestURLConnection {
    
    public static void main( String[] args ) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        HttpMethod meth = new GetMethod( "http://localhost:8080/test-web" );
        int returnCode = client.executeMethod( meth );
        
        System.out.println( returnCode );
    }
    
    public static void main2( String[] args ) throws IOException {
        //URL url = new URL( "http://www.google.ro/#q=cat maximus" );
        URL url = new URL( "http://localhost:8080/test-web" );
        URLConnection con = url.openConnection();
        con.addRequestProperty( "name1", "value23" );
        con.setDoOutput( true );
        con.getOutputStream().close();
        
        
        
        //InputStream is = con.getInputStream();
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        //IOUtils.copy( is, baos );
        
        //is.close();
        //baos.close();
        
        //System.out.println( baos.toString("ISO-8859-2") );
    }

}
