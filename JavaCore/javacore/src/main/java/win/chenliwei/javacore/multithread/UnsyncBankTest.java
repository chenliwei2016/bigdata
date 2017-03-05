/**
 * @author: ChenLiwei
 * 2017-03-04
 * Comments: Show the problems if does not notice the synchronization between multiply threads
 * when you run the code, you will find it is too soon to display the total balance is out of control
 * the problem happens when 2 threads transfer amount concurrently to the same account, one of two threads will lose update
 * Think how to solve this using multithreaded technology.
 * Time	Thread1								Thread2
 * t1	read 1000 from accounts[1]
 * t2	update 1000 to 1500
 * t3										read 1000 from accounts[1]
 * t4										update 1000 to 2000
 * t5										write 2000 to accounts[1]
 * t6	write 1500 to accounts[1]
 * 
 * see, we lost the changes from t3 to t5, it causes the decrease of total balance. 
 * 
 */
package win.chenliwei.javacore.multithread;

import java.util.Random;;

public class UnsyncBankTest {
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
						icbc.transfer(new Random().nextInt(NUMACCOUNT), new Random().nextInt(NUMACCOUNT), MAXAMOUNT * Math.random());
						try {
							Thread.sleep(new Random().nextInt(NUMACCOUNT));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
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
		public void transfer(int from, int to, double amount){
			if(accounts[from] < amount) return;
			accounts[from] -= amount;
			accounts[to] += amount;
			System.out.printf("%10.2f from %d to %d, Toal Balance is : %10.2f\n", amount,from,to,this.totalBalance());
		}
		public double totalBalance(){
			double sum = 0;
			for(double d : accounts) sum += d; 
			return sum;
		}
	}

}

