/**
 * @author: ChenLiwei
 * 2017-03-01
 * LinkedHashMapTest.java
 * Comments: It is to demonstrate the new type which combines the features of LinkedList and HashMap
 * that is, stored with hash and iterate with order.
 */
package win.chenliwei.javacore.setclass;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LinkedHashMapTest {

	public static void main(String[] args) {
		Map<Integer,String> linkedHashMap = new LinkedHashMap<Integer,String>(3,1F,true){

			private static final long serialVersionUID = -8700708360326753483L;

			/* 
			 * Tell the map when to remove the oldest item
			 */
			@Override
			protected boolean removeEldestEntry(Entry<Integer, String> eldest) {
				System.out.println("removeEldestEntry() is triggered");
				return this.size() > 3;
			}
			
			
		};
		
		linkedHashMap.put(3, "Third");
		linkedHashMap.put(2, "Second");
		linkedHashMap.put(4, "Fourth");
		
		linkedHashMap.forEach((k,v)->{System.out.println(v);});
		System.out.println(linkedHashMap.get(2));
		//Notice, when get() is triggered, the item visited will move to the end of Iterator
		linkedHashMap.forEach((k,v)->{System.out.println(v);});
		//Now, let's test the removeEldestEntry(), see what happens if we put a new one
		linkedHashMap.put(1, "First");
		linkedHashMap.forEach((k,v)->{System.out.println(v);});
		//See, we implements the principle of high speed cache: Least Recently Used
	}
}

