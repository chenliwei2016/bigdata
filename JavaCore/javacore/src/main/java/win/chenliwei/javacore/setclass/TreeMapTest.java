/**
 * @author: ChenLiwei
 * 2017-03-01
 * TreeMapTest.java
 * Comments: It is to demonstrate another type of Map: TreeMap
 * Just like the TreeSet, the items contained in the Map are stored in order
 * and so can be iterated in order. By the way, the key needs to be either Comparable or packaged by Comparator
 */
package win.chenliwei.javacore.setclass;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {

	public static void main(String[] args) {
		
		Map<String, Item> itemMap = new TreeMap<>();
		//Since String class implements the interface Comparable, so no need to do extra work
		itemMap.put("1st", new Item("Bag", 123));
		itemMap.put("4th", new Item("Cups", 333));
		itemMap.put("3th", new Item("Hats", 444));
		itemMap.put("2nd", new Item("Shoes", 122));
		//TreeMap is slower than HashMap but has order
		itemMap.forEach((key,value)->{System.out.println(key + " " + value.getDescription());});
	}

}
