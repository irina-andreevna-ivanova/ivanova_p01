package zendo.playground.sse.classloaders;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 11/30/10
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestLoadingJavaLangItems {

    public static void main(String[] args) {
        TestClass tc = new TestClass();
        System.out.println( tc.met() );
    }

}
