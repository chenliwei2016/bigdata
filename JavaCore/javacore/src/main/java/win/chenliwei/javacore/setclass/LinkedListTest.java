/**
 * @author: ChenLiwei
 * 2017-02-24
 * LinkedListTest.java
 * Comments: It is to demonstrate the function for the specific collection class: LinkedList
 * LinkedList is a ordered List type which allows add/remove items at any location with high efficiency
 * Different with ArrayList, each item has double links points its previous and next item
 * So we call it Two-Way Chain table.
 * LinkedList.add() only put the item to the tail of the list. so if we want to insert into any position
 * in the list, we need the help of the Iterator, this way, we can operate items at anywhere
 */
package win.chenliwei.javacore.setclass;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<Customer> customer = new LinkedList<>();
		customer.add(new Customer("chenliwei","13900011111"));
		customer.add(new Customer("zhangpeng","13900022222"));
		customer.add(new Customer("yaoxiang","13900033333"));
		customer.forEach(c -> {System.out.println(c.getName());});
		//Now, chenliwei,zhangpeng and yaoxiang are appended to the list in order
		//Suppose we want to insert zhangwei before zhangpeng or after chenliwei
		//Since interface Iterator has no add() method, we need its sub class ListIterator
		ListIterator<Customer> it = (ListIterator<Customer>) customer.iterator();
		//Notice, Since it is two-way list, so we have both next() and previous()
		//We need to pay attention to the position very carefully
		//Rember, place a next() or previous() before remove(), and confirm the returned item
		//and then remove it, or else you will get a mess
		while (true) {
			Customer c = null;
			if(it.hasNext()) c = it.next();
			if(c.getName() == "chenliwei"){
				//Both add() and remove() always change the LinkedList itself
				//So after that, the last previous() and next() get invalid
				it.add(new Customer("zhangwei","13900044444"));				
				//it.remove(); //Error, becuase a new item added
				System.out.println("Now, the next item is " + it.next().getName() 
						+ ", it will be removed");
				it.remove(); // Removed the one returned by last next()
				System.out.println("Now, the previous item is " + it.previous().getName()
						+ ", it will be removed");
				it.remove();
				//we also use set() to update the item which returned by last next() or previous()
				System.out.println("Now, the next item is " + it.next().getName()
						+ ", it will be updated to Lady Gaga");
				it.set(new Customer("Lady Gaga","1390005555"));
				break;
			}
		}
		
		customer.forEach(c -> {System.out.println(c.getName());});
		
		
	}

}
