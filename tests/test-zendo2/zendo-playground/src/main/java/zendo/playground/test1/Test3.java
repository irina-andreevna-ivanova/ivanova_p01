package zendo.playground.test1;


/**
 * @author bogdan.mocanu
 */
public class Test3 {

    public static void main( String[] args ) {
        //System.out.println( ManagementFactory.getRuntimeMXBean().getName() );
        MyClass mc = new MyClass();
        System.out.println( ((MyInterfA)mc).LENGTH );
    }

}

interface MyInterfA {
    public static int LENGTH = 100;
}

interface MyInterfB {
    public static int LENGTH = 200;
}

class MyClass implements MyInterfA, MyInterfB {
    
}
