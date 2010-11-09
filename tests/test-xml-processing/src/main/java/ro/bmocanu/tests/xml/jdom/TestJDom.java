package ro.bmocanu.tests.xml.jdom;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 09.11.2010
 * Time: 10:57:14
 * To change this template use File | Settings | File Templates.
 */
public class TestJDom {

    public static void main(String[] args) throws JDOMException, IOException {
        display();
        create();

    }

    private static void display() throws JDOMException, IOException {
        SAXBuilder docBuilder = new SAXBuilder();
        Document doc = docBuilder.build(TestJDom.class.getResourceAsStream("/test.xml"));
        Element rootElement = doc.getRootElement();

        List<Element> bookList = rootElement.getChild("books").getChildren();
        for (Element book : bookList) {
            System.out.println("Book");
            System.out.println("    ID=" + book.getAttributeValue("id"));
            System.out.println("    Name=" + book.getAttributeValue("name"));

            int authorIndex = 0;
            for (Element author : (List<Element>) book.getChildren()) {
                System.out.println("    Author" + (authorIndex++) + "=" + author.getText());
            }
        }
    }

    private static void create() {
        Element library = new Element("library");
        Element books = new Element("books");

        Element book = new Element("book");
        book.setAttribute("id", "180");
        book.setAttribute("name", "Holiday Affair (Zebra Contemporary Romance)");

        Element author = new Element("author");
        author.setText("Lisa Plumley");

        book.addContent(author);
        books.addContent(book);
        library.addContent(books);

        Document doc = new Document();
        doc.setRootElement(library);

        // convert the doc to text

        XMLOutputter xmlOutputter = new XMLOutputter();
        System.out.println( xmlOutputter.outputString( doc ) );
    }

}
