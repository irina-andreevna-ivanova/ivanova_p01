/**
 * 
 */
package zendo.playground.sse.serialization.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * 
 * @author mocanu
 */
public class ModelFactory {

    public static Wheel createWheel( String model, int size ) {
        Wheel w = new Wheel();
        w.setModel( model );
        w.setSize( size );
        return w;
    }

    public static Car createCar( String model, int age, String color, String licenseNumber ) {
        Car c = new Car();
        c.setModel( model );
        c.setAge( age );
        c.setColor( color );
        c.setLicenseNumber( licenseNumber );
        return c;
    }

    /**
     * @param string
     * @param i
     * @return
     */
    public static Collection<Wheel> createWheels( String model, int size ) {
        Collection<Wheel> result = new ArrayList<Wheel>();
        result.add( createWheel( model, size ) );
        result.add( createWheel( model, size ) );
        result.add( createWheel( model, size ) );
        result.add( createWheel( model, size ) );
        return result;
    }

}
