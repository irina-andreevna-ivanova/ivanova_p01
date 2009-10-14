package zendo.playground.various.ju1;

/**
 * Base class
 * <p/>
 * User: Cristi Lucaci
 * Date: Mar 21, 2009
 * Time: 2:30:29 PM
 */
public class Base {
    
    public void f() {
        System.out.println("Inside f() in Base class");
        g();
        
        Integer i;
    }

    public void g() {
        System.out.println("Inside g() in Base class");
    }
}
