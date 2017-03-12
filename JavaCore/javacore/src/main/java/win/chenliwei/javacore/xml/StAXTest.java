/**
 * @author: ChenLiwei
 * 2017-03-12
 * StAXTest.java
 * Comments: Different to SAX, StAX has more simply logic to deal with events, it allows you to iterator
 * all the events but not override it. Now we rewrite the SAXTest.java to implement the same requirement 
 */
package win.chenliwei.javacore.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAXTest {

	public static void main(String[] args) throws MalformedURLException, IOException, XMLStreamException {
		String url;
		if(args.length == 0) url = "http://www.w3c.org";
		else url = args[0];
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream in = new URL(url).openStream();
		XMLStreamReader parser = factory.createXMLStreamReader(in);
		while(parser.hasNext()){
			int event = parser.next();
			if(event == XMLStreamConstants.START_ELEMENT){ //Event name
				if(parser.getLocalName().equalsIgnoreCase("a")){
					String href = parser.getAttributeValue(null, "href");
					if(href != null) System.out.println(href);
				}
			}
			if(event == XMLStreamConstants.END_DOCUMENT) System.out.println("Finshed page" + url);
		}

	}

}
