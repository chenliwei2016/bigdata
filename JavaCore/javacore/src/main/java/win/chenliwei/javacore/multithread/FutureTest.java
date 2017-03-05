/**
 * @author: ChenLiwei
 * 2017-03-05
 * FutureTest.java
 * Comments:It is to demonstrate how to use Future and Callable instead of Runnable to get a return value
 * since you know Runnable has no return value.
 * this demo is much like BlockingQueueTest.java but return the number of matched files
 * Another learning point is the iterating way.
 */
package win.chenliwei.javacore.multithread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Scanner in = new Scanner(System.in);
		System.out.println("enter a keyword to search:");
		String keyword = in.nextLine();
		System.out.println("enter a directory as starting point:");
		String startDir = in.nextLine();
		in.close();
		MatchCounter counter = new MatchCounter(new File(startDir),keyword);
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		new Thread(task).start();
		System.out.println(task.get() + " files matched" );
	}

}

class MatchCounter implements Callable<Integer>{
	private File startDir;
	private String keyword;
	private int count;
	public MatchCounter(File startDir, String keyword) {
		this.startDir = startDir;
		this.keyword = keyword;
	}
	@Override
	public Integer call() {
		count=0;
		File[] files = startDir.listFiles();
		List<Future<Integer>> results = new ArrayList<Future<Integer>>();
		for(File file : files){
			if(file.isDirectory()){
				MatchCounter counter = new MatchCounter(file,keyword);
				FutureTask<Integer> task = new FutureTask<Integer>(counter);
				results.add(task);
				new Thread(task).start();
			} else {
				if(search(file)) count++;
			}
		}
		for(Future<Integer> result : results)
			try {
				count += result.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		return count;
	}
	
	public boolean search(File file){
		try(Scanner in = new Scanner(file)){
			while(in.hasNextLine()){
				if(in.nextLine().contains(keyword)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			return false;
		}
		return false;
	}
	
}