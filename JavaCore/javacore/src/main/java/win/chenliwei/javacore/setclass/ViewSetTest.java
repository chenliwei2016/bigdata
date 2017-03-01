/**
 * @author: ChenLiwei
 * 2017-03-01
 * ViewSetTest.java
 * Comments: It is to demonstrate the View of set classes, can provides the functions to
 * transformations between set and set or set and array,etc
 */
package win.chenliwei.javacore.setclass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ViewSetTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//For example, Arrays.asList can accept array or strings and then change it to a list
		//Sometimes we needs to transform it to adapt the interface of another method
		
		 class Pair{
			int key;
			String value;
			Pair(int key, String value) {
				this.key = key;
				this.value = value;
			}
		}
		 
		Pair[] pairArray = {new Pair(1,"First"), new Pair(2,"Second"), new Pair(3,"Third")};
		List<Pair> pairList = Arrays.asList(pairArray);
		System.out.println(pairList.get(2).key + " : " + pairList.get(2).value);
		pairList.set(2, new Pair(4,"Fourth"));
		System.out.println(pairList.get(2).key + " : " + pairList.get(2).value);
		
		//The below show how to get sub range of a list
		List<Pair> subPairList = pairList.subList(1, 3);
		subPairList.forEach(action->{System.out.println(action.key + " : " + action.value);});
		
		//The below show how to generate an unmodified view
		List<Pair> unmodifedList = Collections.unmodifiableList(pairList);
		//unmodifedList.set(2, new Pair(5,"Fifth")); //Error!
		
	}

}
