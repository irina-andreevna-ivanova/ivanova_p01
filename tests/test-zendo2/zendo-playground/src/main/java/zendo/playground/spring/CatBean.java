/**
 * 
 */
package zendo.playground.spring;

/**
 * 
 *
 * @author mocanu
 */
public class CatBean {
    
    public String getMessage() {
        System.out.println( "Inside CatBean.getMessage()" );
        return "some nice text message";
    }
    
    public void doSmtPhase1() {
        System.out.println( "Inside CatBean.doSmtPhase1()" );
    }

    public void doSmtPhase2() {
        System.out.println( "Inside CatBean.doSmtPhase2()" );
    }

    public void doSmtPhase3() {
        System.out.println( "Inside CatBean.doSmtPhase3()" );
    }

}
