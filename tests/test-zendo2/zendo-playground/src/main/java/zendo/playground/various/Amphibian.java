package zendo.playground.various;

/**
 * Defines behavior fo amphibian objects
 * User: Cristian Lucaci
 * Date: Mar 21, 2009
 * Time: 1:26:10 PM
 */
public class Amphibian implements Walker {
    
    private int age;
    
    private int size;

    public Amphibian() {
        System.out.println("Inside Amphibian constructor");
    }

    public void move() {
        System.out.println("Amphibian is moving");
    }

    public void swim(){
        System.out.println("Amphibiam is swimming");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void walk() {
        // TODO Auto-generated method stub
        
    }
    
    public static void main( String[] args ) {
        int s = 0;
        for (int i = 0; i < 10; ++i) {
           s += i; 
        } 
        System.out.println("Result: " + s);
    }
    
}
