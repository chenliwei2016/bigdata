/**
 * @author: ChenLiwei
 * @date 2017-04-23
 * Address.java
 * comments: Used by Employee class
 */
package win.chenliwei.spring.simple.bean;

public class Address {
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	public Address(){}
	public Address(String line1, String line2, String city, String state, String zipCode, String country) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}
	@Override
	public String toString() {
		return "Address [line1=" + line1 + ", line2=" + line2 + ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + ", country=" + country + "]";
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
