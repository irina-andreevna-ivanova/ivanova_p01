package ro.bmocanu.tests.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 09.11.2010
 * Time: 13:48:11
 * To change this template use File | Settings | File Templates.
 */
public class TestDom4J {
    public static void main(String[] args) throws DocumentException, IOException {
        display();
        create();
    }

    private static void display() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read( TestDom4J.class.getResourceAsStream("/test.xml") );
        List<Element> books = doc.selectNodes("/library/books/book");

        for( Element book : books ) {
            System.out.println( "Book" );
            System.out.println( "    ID=" + book.attributeValue("id") );
            System.out.println( "    Name=" + book.attributeValue("name") );

            List<Element> authors = book.selectNodes("author");
            int authorIndex = 0;
            for( Element author : authors ) {
                System.out.println( "    Author" + (authorIndex++) + "=" + author.getText() );
            }
        }
    }

    private static void create() throws IOException {
        Document doc = DocumentHelper.createDocument();
        Element books = doc.addElement("library").addElement("books");

        Element book = books.addElement("book");
        book.addAttribute("id", "180");
        book.addAttribute("name", "Holiday Affair (Zebra Contemporary Romance)");

        Element author = book.addElement("author");
        author.setText( "Lisa Plumley" );

        StringWriter stringWriter = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter( stringWriter );
        xmlWriter.write( doc );
        xmlWriter.close();

        System.out.println( stringWriter.getBuffer().toString() );
    }

}
