/**
 * @author: ChenLiwei
 * 2017-02-24
 * SetTest.java
 * Comments: It is to demonstrate the usage of Set which is a data structure you don't care
 * the order of items and want to get the high performance of search
 * HashSet is a kind of Set which use hash code to store data and match result
 * It is very rapid to get the result, notice that the Set has no duplicated value
 * when adds an exists item, will not affect the set
 */
package win.chenliwei.javacore.setclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) throws FileNotFoundException {
		Set<String> words = new HashSet<String>();
		long totalTime = 0;
		Scanner in = new Scanner(new File("ATaleOf2Cities.log"));
		while(in.hasNext()){
			String word = in.next();
			long callTime = System.currentTimeMillis();
			words.add(word);
			callTime = System.currentTimeMillis() - callTime;
			totalTime = totalTime + callTime;
		}
		Iterator<String> it = words.iterator();
		for(int n = 1; n <= 15 && it.hasNext(); n++) System.out.println(it.next());
		System.out.println("...");
		System.out.println("The artical has " + words.size() + " distinct words, it takes "
				+ totalTime + " milliseconds");
		in.close();
	}

}
