/**
 * @author: ChenLiwei
 * 2017-02-27
 * PriorityQueueTest.java
 * Comments: It is to demonstrate the usage of Priority Queue which is used in a typical scene:
 * Suppose we need to coordinate some jobs executions, the job with most high priority goes first
 * in the waiting list when performing remove() function.
 * Same with TreeSet, either implement interface Comparable or package with Comparator for the items
 */
package win.chenliwei.javacore.setclass;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args) {
		PriorityQueue<Job> jobs = new PriorityQueue<>(new Comparator<Job>(){

			@Override
			public int compare(Job o1, Job o2) {
				return o1.getPriority() - o2.getPriority();
			}
			
		});
		jobs.add(new Job(300,"Check Mailbox"));
		jobs.add(new Job(100,"Draft To Do List"));
		jobs.add(new Job(500,"Check Wechat Moments"));
		jobs.add(new Job(1,"Make a cup of tea"));
		
		System.out.println("Iterating the jos"); //The order of items is random
		jobs.forEach(job -> {System.out.println(job.getJobName() + " with priority " + job.getPriority());});
		
		System.out.println("Now, Start doing jobs");
		while(! jobs.isEmpty()){
			System.out.println("Now doing " + jobs.remove().getJobName());
			//The job with the minimum value is removed first
		}
	}

}

class Job{
	private int priority;
	private String jobName;
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Job(int priority, String jobName) {
		this.priority = priority;
		this.jobName = jobName;
	}
}