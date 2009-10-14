/**
 * 
 */
package zendo.playground.quizzes;


/**
 * 
 * 
 * @author mocanu
 */
public class TestQ14 {
    
    class Inner1 extends TestQ14 {
        public void fly() {
            System.out.println( "Flying in Inner1" );
        }
    }

    class Inner2 extends Inner1 {
        public Inner2() {
            TestQ14.this.super();
        }

        public void fly() {
            System.out.println( "Flying in Inner2" );
        }
    }
    
    public void fly() {
        System.out.println( "Flying in Parent" );
    }
    
    public static void main( String[] args ) {
        Inner1 innerObj = new TestQ14().new Inner2();
        innerObj.fly();
    }

}
