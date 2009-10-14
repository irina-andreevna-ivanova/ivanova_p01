package zendo.playground.lang.reflection;

import java.lang.reflect.Field;

/**
 * @author bogdan.mocanu
 */
public class TestPrivateFields {

    public static void main( String[] args ) throws IllegalAccessException {
        MyClass cl = new MyClass();
        Field[] fields = cl.getClass().getDeclaredFields();
        System.out.println( fields.length );
        System.out.println( fields[0].getType().getName() );
        fields[ 0 ].setAccessible( true );
        fields[ 0 ].set( cl, 30 );
        System.out.println( cl.getNumber() );
    }

}

class MyClass {

    private int number = 10;

    public int getNumber() {
        return number;
    }

    public void getNumber( int x ) {
        
    }
}