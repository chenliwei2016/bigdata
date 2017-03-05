/**
 * @author: ChenLiwei
 * 2017-03-05
 * Sync2Bank.java
 * Comments:It is to eliminate the problem appears in UnsyncBankTest.java
 * we use lock mechanism to keep the atomic of a piece of code or method
 * 	1. use concurrent package
 * 	2. use keyword synchronized
 * here, we use the second way to keep synchronization
 * we also demonstrate how to await a thread using its own Condition object
 * and how to wake a thread when the condition is met.
 * Obviously, it seems more clean, so you are encouraged to use this since it can avoid potential problem, 
 * but it is not flexible or powerful than reentrant lock
 * The program will not stop because all the sub threads will blocked by the condition: balance is too low
 * and we called that deadlocks
 */
package win.chenliwei.javacore.multithread;

import java.util.Random;

public class Sync2Bank {
	static Bank icbc;
	static final int NUMACCOUNT = 100;
	static final double MAXAMOUNT = 1000;
	public static void main(String[] args) {
		icbc = new Bank(NUMACCOUNT,MAXAMOUNT);
		for(int i=0; i<NUMACCOUNT; i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					while(true){						
						try {
							icbc.transfer(new Random().nextInt(NUMACCOUNT), new Random().nextInt(NUMACCOUNT), MAXAMOUNT * Math.random());
							Thread.sleep(new Random().nextInt(NUMACCOUNT));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}}).start();
		}
	}

	private static class Bank{
		private final double[] accounts;

		public Bank(int n, double initalBalance) {
			this.accounts = new double[n];
			for(int d=0; d<n ; d++) accounts[d] = initalBalance;
		}
		public synchronized void transfer(int from, int to, double amount) throws InterruptedException{

			while(accounts[from] < amount){
				System.out.println(Thread.currentThread().getName() + " is waiting since insufficient money");
				wait();
			}
			accounts[from] -= amount;
			accounts[to] += amount;
			System.out.printf("%10.2f from %d to %d, Toal Balance is : %10.2f\n", amount,from,to,this.totalBalance());
			notifyAll();
		}
		public synchronized double totalBalance(){
			double sum;
			sum = 0;
			for(double d : accounts) sum += d;
			return sum;
		}
	}

}
