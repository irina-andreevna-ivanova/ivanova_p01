package ro.bmocanu.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan
 * Date: 03.03.2010
 * Time: 23:06:41
 * To change this template use File | Settings | File Templates.
 */
public class Launcher {

    private static final String[] APPLICATION_CONTEXT_FILES = {"applicationContext.xml"};

    public static void main(String[] args) throws JMSException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_FILES);
        ((AbstractApplicationContext) applicationContext).registerShutdownHook();
        ConnectionFactory factory = (ConnectionFactory) applicationContext.getBean("connectionFactory");
        Connection con = factory.createConnection();
    }

}
