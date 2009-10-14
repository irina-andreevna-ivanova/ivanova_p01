/**
 * 
 */
package zendo.playground.test1;

/**
 * 
 *
 * @author mocanu
 */
public class TestInnerIntf {
    
    interface MyInnerIntf {
        public void meth();
    }

    interface MyInnerIntf2 {
        public void meth();
    }

}

class TestClass111 implements TestInnerIntf.MyInnerIntf {

    /**
     * {@inheritDoc}
     */
    public void meth() {
        // TODO Auto-generated method stub
        
    }
    
}