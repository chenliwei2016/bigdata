/**
 * @author: ChenLiwei
 * 2017-02-24
 * QueueTest.java
 * Comments: It is to demonstrate what is separation of interface and implementation
 * This is a important methodology in java, which makes the coding flexible and scalable
 * Suppose we need to implement a Queue, we have two options: 
 * either circular array(ArrayDeque) or linked list(LinkedList)
 * they both implement interface Queue, so which way to choose it is up to you 
 * that means you could choose any class implemented this interface
 */
package win.chenliwei.javacore.setclass;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		//First, we implement the Queue in circular array way
		//Which is high performance and can be resized but cannot be parallel
		//For example, the below Quene's capacity is only 3, the default is 16 elements
		Queue<Customer> customer = new ArrayDeque<Customer>(3);
		
		//Now, suppose months later, for some reason we need change the type of customer
		//So easy, we just change it to another class implemented interface Queue
		//and the below code totally not changed at all
		customer = new LinkedList<Customer>();
		
		customer.add(new Customer("chenliwei","13900011111"));
		customer.add(new Customer("zhangpeng","13900022222"));
		customer.add(new Customer("yaoxiang","13900033333"));
		customer.add(new Customer("zhangwei","13900044444"));
		customer.forEach(item -> {System.out.println(item.getName());});
		Customer head = customer.poll();
		System.out.println(head.getName());
		System.out.println(customer.size());
	}

}

class Customer{
	private String name;
	private String phoneNumber;
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
}