package ro.bmocanu.test.jms.amqweb;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 08.03.2010
 * Time: 16:45:27
 * To change this template use File | Settings | File Templates.
 */
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Context ctx = new InitialContext();
            Topic topic = (Topic) ctx.lookup("jms/test-topic");

            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/test-pool");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession( false, Session.AUTO_ACKNOWLEDGE );
            MessageProducer producer = session.createProducer( topic );

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        resp.getWriter().write("OKaaass");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
