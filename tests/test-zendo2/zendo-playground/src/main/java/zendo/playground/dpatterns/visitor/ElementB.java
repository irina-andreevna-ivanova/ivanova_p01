/**
 * 
 */
package zendo.playground.dpatterns.visitor;

/**
 * 
 *
 * @author mocanu
 */
public class ElementB extends AbstractElement {

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept( Visitor visitor ) {
        System.out.println( "ElementB Accepting Visitor " + visitor.getClass().getName() );
        visitor.visitElementB( this );
    }

}
