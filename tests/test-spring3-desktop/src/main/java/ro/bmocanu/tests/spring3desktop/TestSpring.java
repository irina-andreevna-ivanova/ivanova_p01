package ro.bmocanu.tests.spring3desktop;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 26.08.2010
 * Time: 14:17:39
 * To change this template use File | Settings | File Templates.
 */
public class TestSpring {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.registerShutdownHook();

        BeanNr1 bean1 = (BeanNr1) context.getBean("bean1");
        System.out.println(bean1.getNr2());

        try {
            meth();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void meth() {
        throw new RuntimeException("Something");

    }

}
