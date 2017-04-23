/**
 * @author: ChenLiwei
 * @date 2017-04-23
 * Employee.java
 * comments: it is to demonstrate references another class for Spring IOC
 */
package win.chenliwei.spring.simple.bean;

public class Employee {
	private String firstName;
	private String lastName;
	private Address homeAddress;
	public Employee(){}
	public Employee(String firstName, String lastName, Address homeAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.homeAddress = homeAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	

}
