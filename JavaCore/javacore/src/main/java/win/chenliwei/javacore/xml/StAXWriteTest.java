/**
 * @author: ChenLiwei
 * 2017-03-12
 * StAXWriteTest.java
 * Comments: It is a simple demo to demonstrate to write objects to a XML file with StAX
 */
package win.chenliwei.javacore.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StAXWriteTest {

	public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
		List<employee> staff = new ArrayList<>();
		staff.add(new employee("chenliwei",50000,new GregorianCalendar(2015,4,1).getTime()));
		staff.add(new employee("zhangpeng",60000,new GregorianCalendar(2014,5,1).getTime()));
		staff.add(new employee("zhangwei1",10000,new GregorianCalendar(2013,1,1).getTime()));
		staff.add(new employee("zhangwei2",30000,new GregorianCalendar(2012,7,1).getTime()));
		
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("employee.xml"));
		String namespace = "www.chenliwei.win";
		writer.setDefaultNamespace(namespace);
		String dtd = "<!DOCTYPE staff [<!ELEMENT staff (employee*)><!ELEMENT employee (name,salary,hireday)><!ELEMENT name (#PCDATA)><!ELEMENT salary (#PCDATA)><!ATTLIST hireday year CDATA \"0\"><!ATTLIST hireday month CDATA \"0\"><!ATTLIST hireday day CDATA \"0\">]>";
		
		try {
			writer.writeStartDocument("UTF-8", "1.0");
			writer.writeDTD(dtd);
			writer.writeStartElement("staff");
			staff.forEach(employee->{
				try {
					writer.writeStartElement("employee");
						writer.writeStartElement("name");
						writer.writeCharacters(employee.getName());
						writer.writeEndElement();
						writer.writeStartElement("salary");
						writer.writeCharacters(employee.getSalary());
						writer.writeEndElement();
						writer.writeStartElement("hireday");
						Calendar cal = Calendar.getInstance();
						cal.setTime(employee.getHireDay());
						writer.writeAttribute("year", new Integer(cal.get(Calendar.YEAR)).toString());
						writer.writeAttribute("month", new Integer(cal.get(Calendar.MONTH)).toString());
						writer.writeAttribute("day", new Integer(cal.get(Calendar.DATE)).toString());
					writer.writeEndElement();
				writer.writeEndElement();
				} catch (XMLStreamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					});
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.close();
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}

class employee{
	private String name;
	private double salary;
	private Date hireDay;
	public employee(String name, double salary, Date hireDay) {
		this.name = name;
		this.salary = salary;
		this.hireDay = hireDay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return new Double(salary).toString();
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getHireDay() {
		return hireDay;
	}
	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	
}
