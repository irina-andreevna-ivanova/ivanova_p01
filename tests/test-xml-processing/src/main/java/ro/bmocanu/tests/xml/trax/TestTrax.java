package ro.bmocanu.tests.xml.trax;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 08.11.2010
 * Time: 16:53:17
 * To change this template use File | Settings | File Templates.
 */
public class TestTrax {

    public static void main(String[] args) throws TransformerException, SAXException, ParserConfigurationException, IOException {
        process();
    }

    private static void process() throws TransformerException, SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document xmlDoc = docBuilder.parse( TestTrax.class.getResourceAsStream("/test.xml") );

        Source xmlSource = new DOMSource( xmlDoc );
        Source stylesheetSource = new StreamSource(TestTrax.class.getResourceAsStream("/test.xsl"));

        StringWriter resultWriter = new StringWriter();
        Result transformerResult = new StreamResult( resultWriter );

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(stylesheetSource);
        transformer.transform( xmlSource, transformerResult );
        
        System.out.println( resultWriter.getBuffer().toString() );
    }

}
