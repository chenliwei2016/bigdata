/**
 * @author: ChenLiwei
 * 2017-03-05
 * BlockingQueueTest.java
 * Comments: It is to demonstrate to implement serialized device using blocking queue in multithreaded environment
 * So no need to use lock mechanism to protect the shared operations, which is encouraged.
 */
package win.chenliwei.javacore.multithread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("enter a keyword to search:");
		String keyword = in.nextLine();
		System.out.println("enter a directory as starting point:");
		String startDir = in.nextLine();
		in.close();
		final int QUEUESIZE = 10;
		final int SEARCHTHREADS = 100;
		BlockingQueue<File> queue = new ArrayBlockingQueue<>(QUEUESIZE);
		FileEnumerationTask fileEnums = new FileEnumerationTask(new File(startDir),queue);
		new Thread(fileEnums).start();//one producer writes to the queue
		for(int i=0; i<SEARCHTHREADS;i++) new Thread(new SearchTask(queue,keyword)).start(); // 100 consumers to read from the queue
	}

}

class FileEnumerationTask implements Runnable{
	private File startDir;
	public static final File ENDOFQUEUE = new File("");
	private BlockingQueue<File> queue;
	
	public FileEnumerationTask(File startDir, BlockingQueue<File> queue) {
		this.startDir = startDir;
		this.queue = queue;
	}

	public void enumerate(File startDir) throws InterruptedException{
		File[] files = startDir.listFiles();
		for(File f : files){
			if(f.isDirectory()){
				enumerate(f);
			}else{
				queue.put(f);
			}
		}
	}

	@Override
	public void run() {
		try{
			enumerate(startDir);
			queue.put(ENDOFQUEUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}

class SearchTask implements Runnable{
	private  BlockingQueue<File> queue;
	private String keyword;
	
	public SearchTask(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}
	
	public void search(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		int linenumber = 0;
		while (in.hasNextLine()){
			linenumber++;
			if(in.nextLine().contains(keyword)) System.out.println(file.getAbsolutePath() + " : " + linenumber);
		}
		in.close();
	}

	@Override
	public void run() {
		boolean done = false;
		try{
			while(!done){
				File file = queue.take();
				if(file == FileEnumerationTask.ENDOFQUEUE) {
					done = true;
					queue.put(file); //This is very important, and the whole application will not terminate if this statement is omitted
				}else search(file);
			}
		} catch (FileNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}