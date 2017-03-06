/**
 * @author: ChenLiwei
 * 2017-03-06
 * ThreadPoolTest.java
 * Comments: It is to demonstrate use the thread pool to submit a task.
 * Usually, it takes high cost to create a new thread but each thread lasts very short time and dies.
 * To manage the threads efficiently, the better way is to keep a thread pool with either fixed or unlimited threads
 * when the application needs a thread to work, just ask the pool for an available thread
 * there, the thread pool acts as a Factory.
 */
package win.chenliwei.javacore.multithread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("enter a keyword to search:");
		String keyword = in.nextLine();
		System.out.println("enter a directory as starting point:");
		String startDir = in.nextLine();
		in.close();
		//ExecutorService pool = Executors.newCachedThreadPool(); //This pool has no limited number for threads
		//you could replace it with a limited threads number with the above
		ExecutorService pool = Executors.newFixedThreadPool(100);//you could use 100 threads at most
		
		Matcher matcher = new Matcher(new File(startDir),keyword,pool);
		Future<Integer> result = pool.submit(matcher);
		try {
			System.out.println(result.get() + " files matched");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("The max threads simultaneously used during running is : " + ((ThreadPoolExecutor)pool).getLargestPoolSize());
		pool.shutdown();
	}
}

class Matcher implements Callable<Integer>{
	private File startDir;
	private String keyword;
	private ExecutorService pool;
	private int count;
	public Matcher(File startDir, String keyword, ExecutorService pool) {
		this.startDir = startDir;
		this.keyword = keyword;
		this.pool = pool;
	}
	@Override
	public Integer call() throws Exception {
		count = 0;
		File[] files = startDir.listFiles();
		List<Future<Integer>> results = new ArrayList<Future<Integer>>();
		
		for(File file : files){
			if(file.isDirectory()){
				results.add(pool.submit(new Matcher(file,keyword,pool)));	
			}else	if(match(file)) count++;
		}
		
		results.forEach(result->{try {
			count += result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}});
		
		return count;
	}
	
	public boolean match(File file){
		try(Scanner in = new Scanner(file)){
			while(in.hasNextLine()) if(in.nextLine().contains(keyword)) return true;
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
