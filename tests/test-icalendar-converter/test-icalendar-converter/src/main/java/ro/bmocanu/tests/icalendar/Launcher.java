/*- 
 * Copyright Bogdan Mocanu, 2010
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package ro.bmocanu.tests.icalendar;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Simple converter for my old iCalendar application XML format to the ICS format.
 * 
 * @author mocanu
 */
public class Launcher {

    public static void main( String[] args ) throws ParserConfigurationException, SAXException, IOException {
        StringBuilder icsBuilder = new StringBuilder();
        icsBuilder.append( "BEGIN:VCALENDAR" ).append( "\r\n" );
        icsBuilder.append( "PRODID:-//Mozilla.org/NONSGML Mozilla Calendar V1.1//EN" ).append( "\r\n" );
        icsBuilder.append( "VERSION:2.0" ).append( "\r\n" );
        icsBuilder.append( "X-WR-CALNAME:Main Calendar" ).append( "\r\n" );
        icsBuilder.append( "X-WR-TIMEZONE:Europe/Bucharest" ).append( "\r\n" );

        Calendar referenceCal = Calendar.getInstance( TimeZone.getTimeZone( "UTC" ) );
        referenceCal.set( 1899, 11, 30 );
        long referenceTimestamp = referenceCal.getTime().getTime();

        System.out.println( "Testing converter" );
        InputStream xmlStream = Launcher.class.getClassLoader().getResourceAsStream( "InCalendar.xml" );

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse( xmlStream );
        document.getDocumentElement().normalize();

        Element xmlDateEvents = (Element) document.getDocumentElement().getElementsByTagName( "dateEvents" ).item( 0 );
        NodeList xmlDateEventList = xmlDateEvents.getElementsByTagName( "dateEvent" );
        for ( int index = 0; index < xmlDateEventList.getLength(); index++ ) {
            Element xmlDateEvent = (Element) xmlDateEventList.item( index );
            String name = xmlDateEvent.getAttribute( "name" );
            double startDateTime = Double.parseDouble( xmlDateEvent.getAttribute( "startDate" ).replace( ',', '.' ) );
            long startDate = ((long) Math.round( Math.floor( startDateTime ) )) * 86400 * 1000 + referenceTimestamp;
            Date startDateObject = new Date( startDate );

            System.out.println( "Creating event for [" + xmlDateEvent.getAttribute( "name" ) + "], date=["
                                + startDateObject + "]" );
            addICSEvent( name, startDateObject, icsBuilder );
        }

        icsBuilder.append( "END:VCALENDAR" ).append( "\r\n" );

        // output the ICS content
        System.out.println( "Writing the content to the ICS file" );

        Writer writer = new FileWriter( "Main Calendar.ics" );
        writer.write( icsBuilder.toString() );
        writer.close();

        System.out.println( "DONE!" );
    }

    private static void addICSEvent( String name, Date date, StringBuilder builder ) {
        String newUUID = UUID.randomUUID().toString();
        boolean addAlarm = false;

        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyyMMdd" );
        String dtStart = dateFormat.format( date );
        String dtEnd = dateFormat.format( new Date( date.getTime() + 86400 * 1000 ) );

        String category = "Miscellaneous";
        if ( name.startsWith( "Birthday" ) ) {
            category = "Birthday";
            name = "BD " + name.substring( "Birthday".length() + 1 );
            addAlarm = true;
        }
        if ( name.startsWith( "RA" ) ) {
            category = "Religious";
        }

        builder.append( "BEGIN:VEVENT" ).append( "\r\n" );
        builder.append( "CREATED:20100410T113207Z" ).append( "\r\n" );
        builder.append( "LAST-MODIFIED:20100410T113352Z" ).append( "\r\n" );
        builder.append( "DTSTAMP:20100410T113352Z" ).append( "\r\n" );
        builder.append( "UID:" ).append( newUUID ).append( "\r\n" );
        builder.append( "SUMMARY:" ).append( name ).append( "\r\n" );
        builder.append( "CATEGORIES:" ).append( category ).append( "\r\n" );
        builder.append( "DTSTART;VALUE=DATE:" ).append( dtStart ).append( "\r\n" );
        builder.append( "DTEND;VALUE=DATE:" ).append( dtEnd ).append( "\r\n" );
        builder.append( "TRANSP:TRANSPARENT" ).append( "\r\n" );
        builder.append( "X-MOZ-GENERATION:1" ).append( "\r\n" );

        if ( addAlarm ) {
            builder.append( "BEGIN:VALARM" ).append( "\r\n" );
            builder.append( "ACTION:DISPLAY" ).append( "\r\n" );
            builder.append( "TRIGGER;VALUE=DURATION:-P1W" ).append( "\r\n" );
            builder.append( "DESCRIPTION:Default Mozilla Description" ).append( "\r\n" );
            builder.append( "END:VALARM" ).append( "\r\n" );
        }
        builder.append( "END:VEVENT" ).append( "\r\n" );
    }

}
