/**
 * @author: ChenLiwei
 * 2017-03-01
 * HashMapTest.java
 * Comments: It is to demonstrate the usage of one subclass of Map: HashMap
 * which is stored for key-value pairs with hash function but not in order
 */
package win.chenliwei.javacore.setclass;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public static void main(String[] args) {
		Map<String, Item> itemMap = new HashMap<>();
		itemMap.put("1st", new Item("Bag", 123));
		itemMap.put("4th", new Item("Cups", 333));
		itemMap.put("3th", new Item("Hats", 444));
		itemMap.put("2nd", new Item("Shoes", 122));
		System.out.println(itemMap.get("2nd").getDescription());
		//to remove a item, just to remove a key
		System.out.println(itemMap.remove("2nd").getDescription() + "has been removed");
		//Iterating the Map
		itemMap.forEach((key,value)->{System.out.println(key + " " + value.getDescription());});
	}

}
