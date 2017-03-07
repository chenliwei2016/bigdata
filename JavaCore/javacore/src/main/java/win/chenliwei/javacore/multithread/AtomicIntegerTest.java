/**
 * @author: ChenLiwei
 * 2017-03-07
 * AtomicIntegerTest.java
 * Comments: It is to demonstrate the atomic update to an Integer with class AtomicInteger.
 * Some guys cannot understand why update an integer needs thread security. Simply say,
 * To increment an integer, inside CPU, it needs 3 directives to finish it:
 * 	1. read the old value
 * 	2. add 1 to old value as new value
 * 	3. write new value back
 * It is highly possible that CPU switches to other threads just from any step of the 3 steps.
 * Then, a little while later, CPU comes back, and continue with the left steps,
 *  but, remember, you are in a concurrency environment and the Integer has been changed already 
 *  Just while CPU was absence right away.
 *  We could compare an ordinary Integer and an AtomicInteger, you will see the difference.
 */
package win.chenliwei.javacore.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.omg.CORBA.IntHolder;

public class AtomicIntegerTest {
	private static IntHolder ordinaryInteger = new IntHolder(0);
	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void main(String[] args) {
		System.out.println("Let 1000 threads increment an integer 10000 times simultaneously.");
		List<Thread> threadlist = new ArrayList<Thread>();
		for(int i= 0; i < 1000; i++) threadlist.add(new Thread(new IntegerIncrement(ordinaryInteger)));
		threadlist.forEach(t -> {t.start();});
		threadlist.forEach(t -> {try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}});
		// after 1000 * 10000 self increment, the final value should be 10,000,000
		System.out.println("IntHolder: 1000 * 10000 should be 10,000,000, but atually: " + ordinaryInteger.value);
		
		threadlist = new ArrayList<Thread>();
		for(int i= 0; i < 1000; i++) threadlist.add(new Thread(new AtomicIntegerIncrement(atomicInteger)));
		threadlist.forEach(t -> {t.start();});
		threadlist.forEach(t -> {try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}});
		// after 1000 * 10000 self increment, the final value should be 10,000,000
		System.out.println("AtomicInteger: 1000 * 10000 should be 10,000,000, and atually it is exact: " + atomicInteger);
		
	}
	private static class IntegerIncrement implements Runnable{
		private IntHolder ordinaryInteger;
		public IntegerIncrement(IntHolder ordinaryInteger) {
			this.ordinaryInteger = ordinaryInteger;
		}
		@Override
		public void run() {
			for(int i=0;i<10000;i++)ordinaryInteger.value++;
		}
	}
	private static class AtomicIntegerIncrement implements Runnable{
		private AtomicInteger atomicInteger;
		public AtomicIntegerIncrement(AtomicInteger atomicInteger) {
			this.atomicInteger = atomicInteger;
		}
		@Override
		public void run() {
			for(int i=0;i<10000;i++)atomicInteger.incrementAndGet();
		}
	}
	
}
