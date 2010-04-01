/**
 * 
 */
package ro.bmocanu.test.jms.springint;

/**
 * Simple message to test Spring Integration.
 * 
 * @author mocanu
 */
public class TestMessage {

    private String content;

    /**
     * Returns the content
     * 
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content to the given value.
     * 
     * @param content
     *            the content to set
     */
    public void setContent( String content ) {
        this.content = content;
    }

}
