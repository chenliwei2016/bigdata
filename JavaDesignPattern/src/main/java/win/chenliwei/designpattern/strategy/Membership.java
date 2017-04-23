/**
 * @author: ChenLiwei
 * 2017-04-06
 * Membership.java
 * Comments: It is to demonstrate how to use Strategy design pattern to realize the membership
 * of a store such as super market or a bookstore
 */
package win.chenliwei.designpattern.strategy;

public class Membership {

	public static void main(String[] args) {
		VIPMember chenliwei = new VIPMember("chenliwei");
		GoldenMember zhangpeng = new GoldenMember("zhangpeng");
		SilverMember yaoxiang = new SilverMember("yaoxiang");
		Product computer = new Product("computer",4999);
		checkout(chenliwei, computer);
		checkout(zhangpeng, computer);
		checkout(yaoxiang, computer);
	}
	
	private static void checkout(Member member, Product p){
		System.out.println(member.getName() + " bought a " + p.getProductName() + " with price " + member.discount(p));
	}

}

interface Member{
	String getName();
	double discount(Product p);
}

class VIPMember implements Member{
	private String name;
	public VIPMember(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	@Override
	public double discount(Product p) {
		return p.getPrice() * 0.5;
	}
}

class GoldenMember implements Member{
	private String name;
	public GoldenMember(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	@Override
	public double discount(Product p) {
		return p.getPrice() * 0.6;
	}
	
}

class SilverMember implements Member{
	private String name;
	public SilverMember(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	@Override
	public double discount(Product p) {
		return p.getPrice() * 0.75;
	}
	
}

class Product{
	private String productName;
	private double price;
	public Product(String productName, double price) {
		this.productName = productName;
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public double getPrice() {
		return price;
	}
}