/**
 * 
 */
package zendo.playground.test.collections;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 *
 * @author mocanu
 */
public class TestSet {
    
    public static void main( String[] args ) {
        Set<Car> cars = new HashSet<Car>();

        Car car = createCar( "asdasda" );
        cars.add( car );
        cars.add( car );
        
        System.out.println( "Set: " + cars );
    }
    
    private static Car createCar( String number ) {
        Car car = new Car();
        car.setLicenseNumber( number );
        return car;
    }

}
