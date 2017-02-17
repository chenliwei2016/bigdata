/**
 * @author: ChenLiwei
 * 2017-02-17
 * CallBackTest.java
 * Comments: It's to demonstrate the mechanism of callback and implementation
 */
package win.chenliwei.javacore.interfaces;

import java.util.HashMap;
import java.util.Random;

public class CallBackTest{
	public static void main(String[] args) throws InterruptedException {
		
		//Synch call
		Customer customerA = new Customer("chenliwei","13912345678");
		customerA.setServiceRequest("Please fix my pc");
		HelpDesk helpDeskA = new HelpDesk();
		helpDeskA.acceptRequest(customerA.getServiceRequest());
		
		//Synch callback
		//customerB calls CallbackHelpDesk.acceptRequest
		//and then CallbackHelpDesk.acceptRequest calls back customerB.methodPerformed
		//which implements the interface Callbackable
		CallbackCustomer customerB = new CallbackCustomer("zhangpeng","13911111111");
		CallbackHelpDesk helpDeskB = new CallbackHelpDesk();
		
		
		helpDeskB.acceptRequest("I need a help on my car", customerB);
		System.out.println("Request has been sloved");
		
		//Asynchronism callback
		helpDeskB.AsyncAcceptRequest("Check my mailbox please", customerB);
		helpDeskB.AsyncAcceptRequest("Give me water, or I will die", customerB);
		System.out.println("Request has been sent, you can go on other issues");
	}

}

/*
 * the below 2 class is simple call relationship
 */
class Customer{
	private String name;
	private String phoneNumber;
	private String serviceRequest;
	public Customer(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		setServiceRequest(null);
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

	public String getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(String serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	
}


class HelpDesk{
	public void acceptRequest(String request) throws InterruptedException{
		System.out.println("Your request is: " + request + ", we are working on it, please wait");
		Thread.sleep(1000 * (new Random()).nextInt(10));
		System.out.println("The answer for your request: you can do it by yourself!");
	};
}

/*
 * the below is for callback
 */
interface Callbackable{
	
	void methodPerformed(String request, String reply);
}


class CallbackCustomer implements Callbackable{
	private String name;
	private String phoneNumber;
	private HashMap<String,String> serviceRequests;
	public CallbackCustomer(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		serviceRequests = new HashMap<>();
	}
	
	public String getServiceRequest(String request) {
		return serviceRequests.get(request);
	}
	public void replyServiceRequest(String request,String reply) {
		this.serviceRequests.put(request,reply);
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
	
	@Override
	public void methodPerformed(String request, String reply) {
		this.replyServiceRequest(request, reply);
		System.out.println("You asked " + request + " and we say:" + reply );
	}
	
}

class CallbackHelpDesk{
	
	public void acceptRequest(String request, Callbackable customer) throws InterruptedException{
	
			String reply;
			Thread.sleep(1000 * (new Random()).nextInt(10));
			reply = "you can do it by yourself, good luck";

			//The below is the callback method
			customer.methodPerformed(request, reply);
	}
	
	public void AsyncAcceptRequest(String request, Callbackable customer) throws InterruptedException{
		System.out.println("Your request has been accepted: " + request);
		Runnable run = new Runnable(){
			@Override
			public void run() {		
				String reply;
				try {
					Thread.sleep(1000 * (new Random()).nextInt(10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
				reply = "you can do it by yourself, good luck";

				//The below is the callback method
				customer.methodPerformed(request, reply);
			}
			
		};
		new Thread(run).start();
	}

}