/**
 * 
 */
package zendo.playground.sse.cloning;

/**
 * 
 *
 * @author mocanu
 */
public class PrinterChip {

    private String manufacturer;

    private long bandwidth;

    /**
     * @param manufacturer
     * @param bandwidth
     */
    private PrinterChip(String manufacturer, long bandwidth) {
        super();
        this.manufacturer = manufacturer;
        this.bandwidth = bandwidth;
    }

}
