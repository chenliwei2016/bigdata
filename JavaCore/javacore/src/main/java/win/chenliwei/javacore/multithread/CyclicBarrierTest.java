/**
 * @author: ChenLiwei
 * 2017-03-08
 * CyclicBarrierTest.java
 * Comments: It is to demonstrator another device of synchronization: CyclicBarrier
 * different to CountDownLatch, it can be reused when its value decreases to zero.
 * Image we are facing the security check at airport, there will be a barrier in front of each queue
 * Every time when checking workers finished a batch of passengers, the barrier is open to let the next batch go
 * then, close the barrier to wait the inner checking.
 */
package win.chenliwei.javacore.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	final static Passenger EMPTY = new Passenger("EMPTY",0);
	
	public static void main(String[] args) {
		final int NUMWORKER = Integer.parseInt(args[0]);
		final int NUMPASSENGER = Integer.parseInt(args[1]);
		CyclicBarrier barrier = new CyclicBarrier(NUMWORKER); //check 3 passengers each time
		List<SecurityWorker> workers = getWorkers(NUMWORKER,barrier);
		ConcurrentLinkedDeque<Passenger> passengers = getPassengers(NUMPASSENGER);
		//To make sure the barrier open finally in case of NUMPASSENGER % NUMWORKER not equal to zero
		if(NUMPASSENGER % NUMWORKER > 0) for (int i=0; i<(3-(NUMPASSENGER % NUMWORKER));i++) passengers.add(EMPTY);
		while(!passengers.isEmpty()) workers.parallelStream().forEach(worker->{try {
			worker.check(passengers.pop());
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}});
		System.out.println(NUMPASSENGER + " passengers accepted the security check by " + NUMWORKER + " security officers");
	}
	
	public static List<SecurityWorker> getWorkers(int number,CyclicBarrier barrier){
		List<SecurityWorker> workers = new ArrayList<>();
		for(int i=1;i<number+1;i++) workers.add(new SecurityWorker("W"+i,barrier));
		return workers;
	}
	
	public static ConcurrentLinkedDeque<Passenger> getPassengers(int number){
		ConcurrentLinkedDeque<Passenger> passengers = new ConcurrentLinkedDeque<>();
		for(int i=1;i<number+1;i++) passengers.add(new Passenger("P"+i,i));
		return passengers;
	}
	
	public static class SecurityWorker{
		private String ID;
		private CyclicBarrier barrier;

		public SecurityWorker(String ID,CyclicBarrier barrier) {
			this.ID = ID;
			this.barrier = barrier;
		}
		public void check(Passenger passenger) throws InterruptedException, BrokenBarrierException{
			barrier.await();
			if(passenger == EMPTY) {
				System.out.println("Security Officer " + ID + " has no passengers to check");
				return;
			}
			System.out.println("Security Officer " + ID + " is checking " + passenger.getName());
			Thread.sleep(1000*passenger.getLuggages());
		}
	}
	
	public static class Passenger{
		private String name;
		private int luggages;
		public Passenger(String name, int luggages) {
			this.name = name;
			this.luggages = luggages;
		}
		public String getName() {
			return name;
		}
		public int getLuggages() {
			return luggages;
		}
		
	}

}
