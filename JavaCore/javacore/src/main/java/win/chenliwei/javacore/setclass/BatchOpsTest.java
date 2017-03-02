/**
 * @author: ChenLiwei
 * 2017-03-01
 * BatchOpsTest.java
 * Comments: It is to demonstrate the batch operations for set classes
 */
package win.chenliwei.javacore.setclass;

import java.util.HashSet;
import java.util.Set;

public class BatchOpsTest {

	public static void main(String[] args) {
		//Suppose we want to implement the intersection of two sets
		Set<Integer> a = new HashSet<Integer>(), b = new HashSet<Integer>();
		a.add(1);a.add(2);a.add(3);
		b.add(1);b.add(2);b.add(4);
		a.retainAll(b);
		System.out.println("*****Intersection*******");
		a.forEach(i->{System.out.println(i);});
		
		//Suppose we want the union of two sets
		a.clear(); b.clear();
		a.add(1);a.add(2);a.add(3);
		b.add(1);b.add(2);b.add(4);
		a.addAll(b);
		System.out.println("*****Union********");
		a.forEach(i->{System.out.println(i);});
		
		//Suppose we want the result of subtraction
		a.clear(); b.clear();
		a.add(1);a.add(2);a.add(3);
		b.add(1);b.add(2);b.add(4);
		a.removeAll(b);
		System.out.println("*****Subtraction*******");
		a.forEach(i->{System.out.println(i);});
	}

}
