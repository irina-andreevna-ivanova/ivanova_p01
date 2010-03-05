/**
 * 
 */
package zendo.playground.dichallenge;



/**
 * 
 *
 * @author mocanu
 */
public class OrderProcessor {
    
    private CMClient cmClient;
    
    public void createOrder( String name, int contractId ) {
        // 1. get the contract
        Contract contract = cmClient.retrieveContract( contractId );

        // 2. set up the order
        Order order = new Order();
        order.id = 100; // some ID generation here 
        order.name = name;
        order.contract = contract;
        
        // 3. further process the order
    }

}
