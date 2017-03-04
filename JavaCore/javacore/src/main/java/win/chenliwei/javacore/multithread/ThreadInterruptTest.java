/**
 * @author: ChenLiwei
 * 2017-03-04
 * Comments:Test How to interrupt a sub thread and how to do if interrupted
 * This is the best practice and needs to be remembered
 */
package win.chenliwei.javacore.multithread;

import java.lang.Thread.UncaughtExceptionHandler;

import win.chenliwei.javacore.inheritance.Employee;

public class ThreadInterruptTest {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new Run1());
		System.out.println("Sub thread thread1 starts,");
		thread1.start();
		System.out.println("Main thread get asleep for 2 millseconds");
		Thread.sleep(2);
		System.out.println("Main thread sends interrupt signal to thread1");
		thread1.interrupt();
		Thread.sleep(2000);
		System.out.println("Now, the status of thread1: " + thread1.getState());
		
		Thread thread2 = new Thread(new Run2());
		System.out.println("Sub thread thread2 starts,");
		thread2.start();
		System.out.println("Main thread get asleep for 1 second");
		Thread.sleep(1000);
		System.out.println("Main thread sends interrupt signal to thread2");
		thread2.interrupt();
		Thread.sleep(2000);
		System.out.println("Now, the status of thread2: " + thread2.getState());
	}
}

class Run1  implements Runnable{

	@Override
	public void run() {
		while(! Thread.currentThread().isInterrupted()){ //the thread can be terminated Only if the condition contains the statement 
			System.out.println(Thread.currentThread().getName() + " : " + Thread.currentThread().getState());
		}
	}
	
}

class Run2 implements Runnable{
	int i;
	long startTime;
	@Override
	public void run() {
		try{
			Thread.currentThread().setUncaughtExceptionHandler(new UncaughtExceptionHandler(){
				//Here, to demonstrate how to deal with UncaughtException in sub thread
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.out.println(t.getName() + " throws an exception: " + e.getMessage());
				}});
			while(! Thread.currentThread().isInterrupted()){  
				startTime = System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName() + " : " + Thread.currentThread().getState());
				i++;
				if(i == 1000) {
					Employee[] staff = new Employee[3];
					staff[0].getName(); //Here, to make a RuntimeException
					Thread.sleep(1000);
				}
			}
		}catch(InterruptedException e){
			System.out.println("Thread2 sys: I am interrupted when I am sleeping");
		}finally{
			System.out.println("Finally thread2 run for " + (System.currentTimeMillis() - startTime) + " milseconds");
		}
	}
	
}