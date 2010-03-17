/**
 * 
 */
package ro.bmocanu.trafficproxy.base;

/**
 * 
 *
 * @author mocanu
 */
public interface Manageable {
    
    public void setEntityName( String name );
    
    public void managedStart();
    
    public void managedStop();

}
