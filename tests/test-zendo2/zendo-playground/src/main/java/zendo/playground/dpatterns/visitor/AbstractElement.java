/**
 * 
 */
package zendo.playground.dpatterns.visitor;

/**
 *  
 *
 * @author mocanu
 */
public abstract class AbstractElement {
    
    public abstract void accept( Visitor visitor );

}
