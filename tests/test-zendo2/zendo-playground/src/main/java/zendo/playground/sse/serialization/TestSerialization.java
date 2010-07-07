/**
 * 
 */
package zendo.playground.sse.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import zendo.playground.sse.serialization.model.Car;
import zendo.playground.sse.serialization.model.ModelFactory;

/**
 * 
 *
 * @author mocanu
 */
public class TestSerialization {
    private static final Logger LOG = Logger.getLogger( TestSerialization.class );

    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        Car car = ModelFactory.createCar( "Audi", 10, "red", "BV 06 RTY" );
        car.getWheels().addAll( ModelFactory.createWheels( "Brembo", 21 ) );

        LOG.info( "Serializing:\n" + car );
        byte[] bytes = serialize( car );
        Object obj = deserialize( bytes );
        LOG.info( "Deserialized object:\n" + obj );
    }

    public static byte[] serialize( Object obj ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( obj );
        oos.close();
        baos.close();
        return baos.toByteArray();
    }

    public static Object deserialize( byte[] bytes ) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream( bytes );
        ObjectInputStream ois = new ObjectInputStream( bais );
        Object result = ois.readObject();
        ois.close();
        bais.close();
        return result;
    }

}
