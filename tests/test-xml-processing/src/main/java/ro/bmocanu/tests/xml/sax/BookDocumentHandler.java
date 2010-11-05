package ro.bmocanu.tests.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by IntelliJ IDEA.
 * User: bogdan.mocanu
 * Date: 05.11.2010
 * Time: 17:14:52
 * To change this template use File | Settings | File Templates.
 */
public class BookDocumentHandler extends DefaultHandler {

    private String bookName;
    private String bookId;
    private boolean authorFollows = false;
    private int authorIndex = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("book".equals(qName)) {
            authorFollows = false;
            authorIndex = 0;
            System.out.println("Book");
            System.out.println("    ID=" + attributes.getValue("id"));
            System.out.println("    Name=" + attributes.getValue("name"));
        } else if ("author".equals(qName)) {
            authorFollows = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (authorFollows) {
            String content = new String(ch, start, length);
            if (content.trim().length() > 0) {
                System.out.println("    Author" + (authorIndex++) + "=" + content);
            }
        }
    }

}
