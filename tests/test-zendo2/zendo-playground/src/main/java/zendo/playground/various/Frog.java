package zendo.playground.various;

/**
 * Extends Amphibian class
 * User: Cristi Lucaci
 * Date: Mar 21, 2009
 * Time: 1:38:50 PM
 */
public class Frog extends Amphibian{

    public Frog(){
        System.out.println("Inside Frog constructor");
    }

    public void move() {
        System.out.println("Frog is moving");
    }

    public void swim(){
        System.out.println("Frog is swimming");
    }



    public static void main(String... args){
        //create instance of Frog
        Frog frog = new Frog();
        //upcast to Amphibian and call methods
        ((Amphibian)frog).move();
        ((Amphibian)frog).swim();
    }
}
