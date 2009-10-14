/**
 * 
 */
package zendo.playground.dpatterns.visitor;

/**
 * @author mocanu
 */
public class ConcreteVisitor1 implements Visitor {

    /**
     * {@inheritDoc}
     */
    public void visitElementA( ElementA object ) {
        System.out.println( "Visitor 1 : Visiting ElementA" );
    }

    /**
     * {@inheritDoc}
     */
    public void visitElementB( ElementB object ) {
        System.out.println( "Visitor 1 : Visiting ElementA" );
    }

}
