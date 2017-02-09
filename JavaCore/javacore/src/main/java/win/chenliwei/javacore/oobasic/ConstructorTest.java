package win.chenliwei.javacore.oobasic;

import java.util.ArrayList;
import java.util.Random;

/*
 *  This demo demonstrate the kinds of constructors and the order of initialization
 *  Nowadays, an E-business web site can sell products to customer without registration
 *  Let's write a simple one to show how to implement
 */
public class ConstructorTest {

	public static void main(String[] args) {
		Customer zhangpeng = new Customer("13912314322");
		ProductItem condom = new ProductItem("Condom","used to avoid pregnent",10);
		ProductItem viagra = new ProductItem("Viagra","used to avoid ED",10);
		zhangpeng.addAddress("No.888 Century avenue");
		ProductOrder zhangpengOrder = new ProductOrder();
		zhangpengOrder.setCustomer(zhangpeng);
		zhangpengOrder.addProductItems(viagra);
		zhangpengOrder.addProductItems(condom);
		zhangpengOrder.displayOrder();
		
		Customer chenliwei = new Customer("chenliwei","13988888888");
		ProductItem lego = new ProductItem("LEGO","Toy for a boy",100);
		ProductItem flower = new ProductItem("Rose","a gift for a girl",10);
		chenliwei.addAddress("No.999 NanJing rd");
		ProductOrder chenliweiOrder = new ProductOrder();
		chenliweiOrder.setCustomer(chenliwei);
		chenliweiOrder.addProductItems(lego);
		chenliweiOrder.addProductItems(flower);
		chenliweiOrder.displayOrder();
		
	}

}

class Customer{
	
	private static long nextId;
	
	private long id;
	private String name;
	private String phoneNumber;
	private ArrayList<String> address;
	
	//for class variable initialization
	static{
		Random generator = new Random();
		nextId = generator.nextLong();
	}
	
	//for object variable initialization
	{
		id = nextId;
		nextId++;
		address = new ArrayList<>();
	}
	
	public Customer(){
		//do nothing
	}
	
	public Customer(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		this.name = "Customer" + id;
	}


	public Customer(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public ArrayList<String> getAddress() {
		return address;
	}
	public void addAddress(String address) {
		if(!this.address.contains(address))	this.address.add(address);
	}
	
}

class ProductItem{
	private String productName;
	private String description;
	private double price;
	public ProductItem(String productName, String description, double price) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}

class ProductOrder{
	private static Long nextOrderId;
	private String orderNumber;
	private ArrayList<ProductItem> productItems;
	private Customer customer;
	private double amount;
	
	public ArrayList<ProductItem> getProductItems() {
		return productItems;
	}

	public void addProductItems(ProductItem productItem) {
		this.productItems.add(productItem);
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	static{
		nextOrderId = new Random().nextLong();
	}
	{
		orderNumber = nextOrderId.toString();
		nextOrderId++;
		productItems = new ArrayList<>();
	}
	
	public void displayOrder(){
		System.out.println("*******************Order " + orderNumber + "*******************");
		System.out.println("Customer: " + customer.getName());
		System.out.println("Phone Number: " + customer.getPhoneNumber());
		for(ProductItem productItem : productItems){
			System.out.println("Production: " + productItem.getProductName() + ", its price: " + productItem.getPrice());
			amount += productItem.getPrice();
		}
		System.out.println("Total Amount: $" + amount);
		System.out.println("Deliever to " + customer.getAddress().toString());
		
	}
}