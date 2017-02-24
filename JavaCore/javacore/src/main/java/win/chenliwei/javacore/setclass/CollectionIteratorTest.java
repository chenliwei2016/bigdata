/**
 * @author: ChenLiwei
 * 2017-02-24
 * CollectionIteratorTest.java
 * Comments: It is to demonstrate the basic concept for all collection types in Java
 * 	1. Collection, it is one of basic interface for all collection types
 * 	2. Iterator, another basic interface for them
 * Let's learn the basic usage of those 2 interfaces
 */
package win.chenliwei.javacore.setclass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
		it.remove(); //Error, because the item of last next() has been removed already

	}

}
