/**
 * @author: ChenLiwei
 * 2017-03-06
 * ForkJoinTest.java
 * Comments: It is to demonstrate the famous Fork-Join framework since java 7
 * Fork-Join is a popular framework for multiply threaded computation
 * it is designed specially for the below scenario:
 * if (problemSize < threshold)
		solve problem directly
   else{
   		break problem into subproblems
		recursively solve each subproblem
		combine the results
   }
 * this demo is to test the random function, suppose set random(0.0 - 1.0) to 10000000 numbers
 * then count how many numbers are larger than 0.5, you will see the result is around half.
 */
package win.chenliwei.javacore.multithread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

public class ForkJoinTest {

	public static void main(String[] args) {
		final int SIZE = 10000000;
		double[] numbers = new double[SIZE];
		for(int i = 1; i < SIZE; i++) numbers[i] = Math.random();
		Counter task = new Counter(0,10000000,numbers,x->x>0.5);
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(task);
		System.out.println(task.join());
	}

}

class Counter extends RecursiveTask<Integer>{
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 10000;
	private int from, to;
	private double[] values;
	private DoublePredicate filter;
	public Counter(int from, int to, double[] values, DoublePredicate filter) {
		this.from = from;
		this.to = to;
		this.values = values;
		this.filter = filter;
	}
	@Override
	protected Integer compute() {
		int count = 0;
		if((to - from) < THRESHOLD){
			for(int i = from; i < to; i++) if(filter.test(values[i])) count++;
			return count;
		} else {
			int one4th = (to-from)/4;
			Counter first = new Counter(from, from+one4th,values,filter);
			Counter second = new Counter(from+one4th,from+2*one4th,values,filter);
			Counter third = new Counter(from+2*one4th,from+3*one4th,values,filter);
			Counter fourth = new Counter(from+3*one4th,to,values,filter);
			ForkJoinTask.invokeAll(first,second,third,fourth);
			return first.join()+second.join()+third.join()+fourth.join();
		}
	}
}
