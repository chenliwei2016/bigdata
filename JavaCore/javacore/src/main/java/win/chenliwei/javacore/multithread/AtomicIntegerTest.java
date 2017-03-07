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

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	private static int ordinaryInteger = 0;
	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void main(String[] args) {
		System.out.println("Let 1000 threads increment an integer 10000 times simultaneously.");
		Integer[] threads  = new Integer[10000000];
		Arrays.fill(threads, 0);
		Arrays.asList(threads).parallelStream().forEach(i->{ordinaryInteger++;});
		
		// after 1000 * 10000 self increment, the final value should be 10,000,000
		System.out.println("int: 1000 * 10000 should be 10,000,000, but atually: " + ordinaryInteger);
		Arrays.asList(threads).parallelStream().forEach(i->{atomicInteger.incrementAndGet();});
		// after 1000 * 10000 self increment, the final value should be 10,000,000
		System.out.println("AtomicInteger: 1000 * 10000 should be 10,000,000, and atually it is exact: " + atomicInteger);
	}
}
