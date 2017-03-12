/**
 * @author: ChenLiwei
 * 2017-03-12
 * SQLInXML.java
 * Comments: It is to demonstrate a way to separate the sql content with the executor with XML technology
 * There are 2 ways to parse xml document, DOM and Stream. we place the xml file in the resource directory
 * First Way, we show DOM and XPath
 * 		In the first function getSQLWithDOM to get sqltext with key sqlid
 * 		In the second function getSQLWithXPath to get username ... since it is only one database item there, it is very suitable for this scene
 * 		Then, we connect to the database and insert and print then all
 * We will write a separated java to show the SAX, a way using stream instead of reading a document all
 * which has a better performance
 */
package win.chenliwei.javacore.xml;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SQLInXML {

	public static void main(String[] args) {
		Connection conn = connDerby(getSQLWithXPath("url")+";user="+getSQLWithXPath("username")+ ";password=" + getSQLWithXPath("password"));
		try {
			conn.createStatement().executeUpdate(getSQLWithDOM("S1"));
			conn.createStatement().executeUpdate(getSQLWithDOM("S2"));
			conn.createStatement().executeUpdate(getSQLWithDOM("S3"));
			ResultSet rs = conn.createStatement().executeQuery(getSQLWithDOM("SelectGreetings"));
			while(rs.next()) System.out.println(rs.getString(1));
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection connDerby(String URL){
		Connection conn = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			conn = DriverManager.getConnection(URL);
			conn.setAutoCommit(true);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static String getSQLWithDOM(String SQLID){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		DocumentBuilder builder;
		String returnvalue = null;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("sqls.xml"));
			Element root = doc.getDocumentElement();
			NodeList sqls = root.getChildNodes();
			for(int i=0; i<sqls.getLength();i++){
				Node sql = sqls.item(i);
				Node sqlid = sql.getFirstChild();
				Node sqltext = sql.getLastChild();
				if(sqlid.getTextContent().equals(SQLID)) {
					returnvalue = sqltext.getTextContent();
					break;
				}
			}
			return returnvalue;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return returnvalue;
		} 
	}

	public static String getSQLWithXPath(String element){
		try {
			return XPathFactory.newInstance().newXPath().evaluate("/Configuration/database/"+element,
					DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("sqls.xml")));
		} catch (XPathExpressionException | SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
