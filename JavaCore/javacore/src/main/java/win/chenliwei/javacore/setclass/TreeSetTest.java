/**
 * @author: ChenLiwei
 * 2017-02-27
 * TreeSetTest.java
 * Comments: It is to demonstrate the new set type : TreeSet
 * It can store the items in order based on the HashSet
 * So the result items in order when use Iterator
 * The key knowledge here is how to use Comparator which is better than implements Comparable
 */
package win.chenliwei.javacore.setclass;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args) {
		SortedSet<Item> parts = new TreeSet<>();
		// Will get error because of no implementation for interface Comparable
		// To fix the problem we have 2 way
		// 1. class Item to implement the Comparable 
		// 2. Use class Comparator to package the Item class
		parts = new TreeSet<Item>(new Comparator<Item>(){
			@Override
			public int compare(Item arg0, Item arg1) {
				return arg0.getDescription().compareTo(arg1.getDescription());
			}});
		parts.add(new Item("Toaster",1234)); 
		parts.add(new Item("Widget",4444));
		parts.add(new Item("Modem",5555));
		System.out.println(parts);
	}

}

class Item{
	private String description;
	private int partNumber;
	public Item(String description, int partNumber) {
		this.description = description;
		this.partNumber = partNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + partNumber;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (partNumber != other.partNumber)
			return false;
		return true;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}
	
}
