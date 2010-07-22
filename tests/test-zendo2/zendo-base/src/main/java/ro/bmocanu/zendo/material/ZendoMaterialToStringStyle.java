/**
 * 
 */
package ro.bmocanu.zendo.material;

import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 *
 * @author mocanu
 */
public class ZendoMaterialToStringStyle extends ToStringStyle {

    private static final long serialVersionUID = 1L;

    /**
     * <p>
     * Constructor.
     * </p>
     * 
     * <p>
     * Use the static constant rather than instantiating.
     * </p>
     */
    ZendoMaterialToStringStyle() {
        super();
        this.setContentStart( " [ " );
        this.setContentEnd( "]" );
        this.setFieldSeparator( ", " );
        this.setFieldSeparatorAtEnd( true );
        this.setFieldSeparatorAtStart( false );
        this.setUseFieldNames( true );
        this.setUseShortClassName( true );
    }

    /**
     * <p>
     * Ensure <code>Singleton</code> after serialization.
     * </p>
     * 
     * @return the singleton
     */
    private Object readResolve() {
        return ToStringStyle.MULTI_LINE_STYLE;
    }

}
