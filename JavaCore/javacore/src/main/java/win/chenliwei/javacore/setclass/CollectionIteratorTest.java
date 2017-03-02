/**
 * @author: ChenLiwei
 * 2017-02-24
 * CollectionIteratorTest.java
 * Comments: It is to demonstrate the basic concept for all collection types in Java
 * 	1. Collection, it is one of basic interface for all collection types
 * 	2. Iterator, another basic interface for them
 * Let's learn the basic usage of those 2 interfaces
 * In the end, practice some static function of Collections class, which implemented
 * algorithms of sort,search
 */
package win.chenliwei.javacore.setclass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CollectionIteratorTest {

	public static void main(String[] args) {
		Collection<Customer> c = new ArrayList<>(); //you could choose any sub collection class
		//The two basic methods of Collection is add() and iterator()
		c.add(new Customer("chenliwei","13900011111"));
		c.add(new Customer("zhangpeng","13900011112"));
		c.add(new Customer("yaoxiang","13900011113"));
		
		Iterator<Customer> it = c.iterator();
		//The function of Iterator is to provide a way to go through all the items of the collection
		//it has 3 methods which are next(), hasNext() and remove()
		while(it.hasNext()) System.out.println(it.next().getName());
		//Or, we could use for each statement instead of Iterator
		for(Customer customer : c) System.out.println(customer.getName());
		//Now, take a look at the third method: remove(), it is somewhat complicated
		//It removes the item of return value of last called next()
		//Suppose we need to remove zhangpeng
		it = c.iterator();
		it.next(); // returns chenliwei
		it.next(); // returns zhangpeng
		it.remove(); // OK, remove zhangpeng
		for(Customer customer : c) System.out.println(customer.getName());
		//it.remove(); //Error, because the item of last next() has been removed already
		
		/*
		 * Let's practice some static functions from Collections
		 */
		
		List<Integer> a = new ArrayList<Integer>(), b = new ArrayList<Integer>();
		a.add(1);a.add(2);a.add(3);a.add(10);a.add(7);a.add(8);a.add(9);a.add(5);a.add(4);
		//Copy the content from one list to another, not good to use in real world because
		//it needs to initialize destination and guarantee the size() is identical
		b.addAll(a);
		Collections.copy(b, a); //So, we use addAll instead of copy
		System.out.println("*******The list a*********");
		a.forEach(i->{System.out.print(i + ",");});
		System.out.print("\n");
		System.out.println("The max value of list a is :" + Collections.max(a)); //Max function
		Collections.sort(a); // get sorted
		System.out.println("*******The sorted list a*********");
		a.forEach(i->{System.out.print(i + ",");});
		System.out.print("\n");
		System.out.println("The location of 10 in list a is : " + Collections.binarySearch(a, 5));
		Collections.reverse(a);
		System.out.println("*******The reversed list a*********");
		a.forEach(i->{System.out.print(i + ",");});
		System.out.print("\n");
		Collections.rotate(a, 2);
		System.out.println("*******The rotated list a with distance 2*********");
		a.forEach(i->{System.out.print(i + ",");});
		System.out.print("\n");
		
	}

}
