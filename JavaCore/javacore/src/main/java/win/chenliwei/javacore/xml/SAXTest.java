/**
 * @author: ChenLiwei
 * 2017-03-12
 * SAXTest.java
 * Comments: It is to demonstrate the second way to parse the XML: SAX stream
 * It has better performance than DOM since DOM needs to build the tree structure until finishes the reading the whole file
 * instead of that, SAX dealing with the XML while reading it, more than that, it can provide the event mechanism to let you write
 * callback function to react the events.
 * Here we use SAX to write a web Crawler to parse XHTML page which is a kind of HTML but more strict than HTML 
 */
package win.chenliwei.javacore.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, MalformedURLException, IOException {
		String url;
		if(args.length == 0) url = "http://www.w3c.org";
		else url = args[0];
		
		DefaultHandler handler = new DefaultHandler(){

			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException {
				System.out.println("uri=" + uri);
				System.out.println("localName=" + localName);
				System.out.println("qName=" + qName);
				if(localName.equalsIgnoreCase("a") && attributes != null){
					for(int i=0; i<attributes.getLength();i++){
						if(attributes.getLocalName(i).equalsIgnoreCase("href")) System.out.println(attributes.getValue(i));
					}
				}
					
			}
			
		};
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		SAXParser saxParser = factory.newSAXParser();
		InputStream in = new URL(url).openStream();
		saxParser.parse(in, handler);
		
		
	}

}
