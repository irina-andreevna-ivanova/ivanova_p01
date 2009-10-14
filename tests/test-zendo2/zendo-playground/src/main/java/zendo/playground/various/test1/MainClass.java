/**
 * 
 */
package zendo.playground.various.test1;


/**
 * 
 *
 * @author mocanu
 */
public class MainClass {
    
    public static void doSomethingWithUserInput( Processor proc ) {
        // get user input from console
        String userInput = "test";
        
        // apply the processor
        Object result = proc.process( userInput );
        
        // display the result
        System.out.println( result );
    }
    
    public void doSmt( final StringSplitterProcessor obj, final int age ) {
        obj.setLength( 10 );
        
    }
    
    public static void main( String[] args ) {
        Processor proc = new StringSplitterProcessor();
        doSomethingWithUserInput( proc );
        doSomethingWithUserInput( new CommaRemovedFilterAdapter() );
        //doSomethingWithUserInput( new CommaRemovedFilter() );
        
        B b = new B();
        b.meth();
        
        A a1;
        
        a1 = new A();
        
        a1.meth();
    }

}

class A {
    
    public void meth() {
        
    }
    
}

 class B extends A {
     
     public void meth() {
         
     }
     
 }
