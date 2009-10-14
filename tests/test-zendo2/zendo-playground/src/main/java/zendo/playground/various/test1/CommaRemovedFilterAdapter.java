/**
 * 
 */
package zendo.playground.various.test1;

import zendo.playground.various.test2.CommaRemovedFilter;

/**
 * 
 *
 * @author mocanu
 */
public class CommaRemovedFilterAdapter implements Processor {
    
    private CommaRemovedFilter commaRemovedFilter = new CommaRemovedFilter();

    /**
     * {@inheritDoc}
     */
    public Object process( Object input ) {
        return commaRemovedFilter.removeCommas( (String) input );
    }

}
