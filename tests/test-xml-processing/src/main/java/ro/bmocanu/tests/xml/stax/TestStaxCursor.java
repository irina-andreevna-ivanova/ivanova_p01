package ro.bmocanu.tests.xml.stax;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Bogdan
 * Date: Nov 6, 2010
 * Time: 10:52:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestStaxCursor {

    public static void main(String[] args) throws XMLStreamException {
        display();
        create();
    }

    private static void display() throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory.createXMLStreamReader(TestStaxCursor.class.getClassLoader().getResourceAsStream("test.xml"));
        int authorIndex = 0;
        boolean authorFollows = false;
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLEvent.START_ELEMENT: {
                    String elementName = reader.getName().toString();
                    if ("book".equals(elementName)) {
                        System.out.println("Book");
                        System.out.println("    ID=" + reader.getAttributeValue("", "id"));
                        System.out.println("    Name=" + reader.getAttributeValue("", "name"));
                        authorIndex = 0;
                    } else if ("author".equals(elementName)) {
                        authorFollows = true;
                    }
                    break;
                }
                case XMLEvent.CHARACTERS: {
                    String characters = reader.getText();
                    if (authorFollows && characters.trim().length() > 0) {
                        System.out.println("    Author" + (authorIndex++) + "=" + reader.getText());
                    }
                    break;
                }
            }
        }
        reader.close();
    }

    private static void create() throws XMLStreamException {
        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(stringWriter);
        writer.writeStartDocument("utf-8", "1.0");
        {
            writer.writeStartElement("library");
            {
                writer.writeStartElement("books");
                {
                    writer.writeStartElement("book");
                    writer.writeAttribute("id", "180");
                    writer.writeAttribute("name", "Holiday Affair (Zebra Contemporary Romance)");
                    {
                        writer.writeStartElement("author");
                        writer.writeCharacters("Lisa Plumley");
                        //writer.writeEndElement();
                    }
                    //writer.writeEndElement();
                }
                //writer.writeEndElement();
            }
            // writer.writeEndElement();
        }
        writer.writeEndDocument(); // this closes all the opened tags and the document itself
        writer.close();

        System.out.println(stringWriter.getBuffer().toString());
    }
}
