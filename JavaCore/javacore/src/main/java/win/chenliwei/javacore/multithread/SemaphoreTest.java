/**
 * @author: ChenLiwei
 * 2017-03-06
 * SemaphoreTest.java
 * Comments: It is to demonstrate how to use semaphore to illustrate the service and queuing in the bank
 * Suppose there are only 2 windows to provide the service and the customers is in a queue
 */
package win.chenliwei.javacore.multithread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2);
		Queue<String> customers = new ArrayDeque<>();
		customers.add("chenliwei");
		customers.add("zhangpeng");
		customers.add("yaoxiang");
		customers.add("zhangwei1");
		customers.add("zhangwei2");
		customers.forEach(customer->{new Thread(new BankService(semaphore,customer)).start();});
	}

}

class BankService implements Runnable{
	private Semaphore semaphore;
	private String customer;

	public BankService(Semaphore semaphore, String customer) {
		this.semaphore = semaphore;
		this.customer = customer;
	}

	@Override
	public void run() {
		long current;
		try {
			current = System.currentTimeMillis();
			semaphore.acquire();
			System.out.println(customer + " is servered at service window");
			Thread.sleep(3000); //Here, enjoy the service
			System.out.println(customer + " left, it stays here for " + (int)((System.currentTimeMillis() - current)/1000) + " seconds");
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}