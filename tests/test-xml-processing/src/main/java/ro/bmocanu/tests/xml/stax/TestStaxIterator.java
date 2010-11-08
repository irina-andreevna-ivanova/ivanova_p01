package ro.bmocanu.tests.xml.stax;

import com.sun.xml.internal.stream.events.CharacterEvent;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Bogdan
 * Date: Nov 7, 2010
 * Time: 2:15:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestStaxIterator {

    public static void main(String[] args) throws XMLStreamException {
        display();
    }

    private static void display() throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = inputFactory.createXMLEventReader(TestStaxIterator.class.getResourceAsStream("/test.xml"));
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            int authorIndex = 0;
            boolean authorFollows = true;

            switch (event.getEventType()) {
                case XMLEvent.START_ELEMENT: {
                    StartElement seEvent = (StartElement) event;
                    String elementName = seEvent.getName().toString();
                    if ("book".equals(elementName)) {
                        System.out.println("Book");
                        System.out.println("    ID=" + seEvent.getAttributeByName(new QName("id")).getValue());
                        System.out.println("    Name=" + seEvent.getAttributeByName(new QName("name")).getValue());
                        authorIndex = 0;
                    } else if ("author".equals(elementName)) {
                        authorFollows = true;
                    }
                    break;
                }
                case XMLEvent.CHARACTERS: {
                    CharacterEvent chEvent = (CharacterEvent) event;
                    String characters = chEvent.getData();
                    if (authorFollows && characters.trim().length() > 0) {
                        System.out.println("    Author" + (authorIndex++) + "=" + characters);
                    }
                    break;
                }
            }
        }

        eventReader.close();
    }
}
