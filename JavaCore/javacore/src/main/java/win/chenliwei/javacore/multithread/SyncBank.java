/**
 * @author: ChenLiwei
 * 2017-03-04
 * SyncBank.java
 * Comments: It is to eliminate the problem appears in UnsyncBankTest.java
 * we use lock mechanism to keep the atomic of a piece of code or method
 * 	1. use concurrent package
 * 	2. use keyword synchronized
 * here, we use the first way to explicitly declare a lock object
 * will use the second way in another java file
 * we also demonstrate how to await a thread when a condition is not met
 * and how to wake a thread when the condition is met.
 * The program will not stop because all the sub threads will blocked by the condition: balance is too low
 * We called deadlock. so we provide the standard way to terminate the sub threads
 * do not use stop() to terminate thread.
 */
package win.chenliwei.javacore.multithread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.omg.CORBA.BooleanHolder;

public class SyncBank {

	static Bank icbc;
	static final int NUMACCOUNT = 100;
	static final double MAXAMOUNT = 1000;
	public static void main(String[] args) {
		icbc = new Bank(NUMACCOUNT,MAXAMOUNT);
		for(int i=0; i<NUMACCOUNT; i++){
			new Thread(new Runnable(){
				private BooleanHolder bWait = new BooleanHolder(true);  
				@Override
				public void run() {
					while((!Thread.currentThread().isInterrupted()) && (bWait.value)){
						icbc.transfer(new Random().nextInt(NUMACCOUNT), new Random().nextInt(NUMACCOUNT), MAXAMOUNT * Math.random(), bWait);
						try {
							Thread.sleep(new Random().nextInt(NUMACCOUNT));
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							System.out.println(Thread.currentThread().getName() + " is waked while sleeping");
						}
					}
				}}).start();
		}
	}

	private static class Bank{
		private Lock banklock = new ReentrantLock();
		private final double[] accounts;
		private Condition sufficientMoney = banklock.newCondition(); //one lock could have one more conditions
		//every condition manage a group of threads which blocked by this condition

		public Bank(int n, double initalBalance) {
			this.accounts = new double[n];
			for(int d=0; d<n ; d++) accounts[d] = initalBalance;
		}
		public void transfer(int from, int to, double amount, BooleanHolder bWait){
			banklock.lock();
			int waits = 0;
			try{
				while(accounts[from] < amount){
					System.out.println(Thread.currentThread().getName() + " is waiting since insufficient money");
					waits++;
					if(waits < 5) {
						sufficientMoney.await(1,TimeUnit.SECONDS); // wake up after 1 second to test again
					}else{
						System.out.println(Thread.currentThread().getName() + " terminates after waiting 5 seconds");
						bWait.value = false;
						return;
					}
				}
				accounts[from] -= amount;
				accounts[to] += amount;
				System.out.printf("%10.2f from %d to %d, Toal Balance is : %10.2f\n", amount,from,to,this.totalBalance());
				sufficientMoney.signalAll();

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally{
				banklock.unlock();
			}
		}
		public double totalBalance(){
			banklock.lock();
			double sum;
			try {
				sum = 0;
				for(double d : accounts) sum += d;
			} finally{
				banklock.unlock();
			} 
			return sum;
		}
	}

}
