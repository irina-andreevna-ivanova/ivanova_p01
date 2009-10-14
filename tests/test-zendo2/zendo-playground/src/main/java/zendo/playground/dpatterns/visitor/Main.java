/**
 * 
 */
package zendo.playground.dpatterns.visitor;

/**
 * 
 *
 * @author mocanu
 */
public class Main {
    
    public static void main( String[] args ) {
        Visitor visitor = new ConcreteVisitor2();
        AbstractElement element = new ElementA();
        
        element.accept( visitor );
    }

}
