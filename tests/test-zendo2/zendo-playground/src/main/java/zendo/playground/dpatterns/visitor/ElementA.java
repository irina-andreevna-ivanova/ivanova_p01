/**
 * 
 */
package zendo.playground.dpatterns.visitor;

/**
 * 
 *
 * @author mocanu
 */
public class ElementA extends AbstractElement {

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept( Visitor visitor ) {
        System.out.println( "ElementA Accepting Visitor " + visitor.getClass().getName() );
        visitor.visitElementA( this );
    }

}
