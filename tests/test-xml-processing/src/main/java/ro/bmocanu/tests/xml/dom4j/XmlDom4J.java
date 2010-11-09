package ro.bmocanu.tests.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XmlDom4J {

    public void generateDocument() {
        Document document = DocumentHelper.createDocument();
        Element catalogElement = document.addElement("catalog");
        catalogElement.addComment("An XML Catalog");
        catalogElement.addProcessingInstruction("target", "text");
        Element journalElement = catalogElement.addElement("journal");
        journalElement.addAttribute("title", "XML Zone");
        journalElement.addAttribute("publisher", "IBM developerWorks");

        Element articleElement = journalElement.addElement("article");
        articleElement.addAttribute("level", "Intermediate");
        articleElement.addAttribute("date", "December-2001");
        Element titleElement = articleElement.addElement("title");
        titleElement.setText("Java configuration with XML Schema");
        Element authorElement = articleElement.addElement("author");
        Element firstNameElement = authorElement.addElement("firstname");
        firstNameElement.setText("Marcello");
        Element lastNameElement = authorElement.addElement("lastname");
        lastNameElement.setText("Vitaletti");

        document.addDocType("catalog", null, "file://c:/Dtds/catalog.dtd");
        try {
            XMLWriter output = new XMLWriter(new FileWriter(new File("c:/catalog/catalog.xml")));
            output.write(document);
            output.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] argv) {
        XmlDom4J dom4j = new XmlDom4J();
        dom4j.generateDocument();
    }
}
