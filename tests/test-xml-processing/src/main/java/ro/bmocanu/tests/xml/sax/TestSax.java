package ro.bmocanu.tests.xml.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 05.11.2010
 * Time: 17:13:04
 * To change this template use File | Settings | File Templates.
 */
public class TestSax {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        display();
    }

    public static void display() throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(false);
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(TestSax.class.getClassLoader().getResourceAsStream("test.xml"), new BookDocumentHandler());
    }

}
