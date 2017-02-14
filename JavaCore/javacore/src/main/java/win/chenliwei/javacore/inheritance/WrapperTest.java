/**
 * @author: ChenLiwei
 * 2017-02-14
 * WrapperTest.java
 * Comments: Wrapper is the concept for the classes points the primitive types
 * for example, Integer class is for int, Double class is for double ....
 * Since generic type ArrayList does not allows primitive types
 * so if we needs a int array, we need define ArrayList<Integer> instead of ArrayList<int> 
 * but the system can automatically convert the int to Integer and vice versa.
 */
package win.chenliwei.javacore.inheritance;

import java.util.ArrayList;

public class WrapperTest {

	public static void main(String[] args) {
		// ArrayList<int> intArray = new ArrayList<>(); Error
		ArrayList<Integer> intArray = new ArrayList<>();
		int aInt = 9;
		intArray.add(aInt); //Autoboxing, means automatically convert the primitive type to its Wrapper
		intArray.add(Integer.valueOf(aInt)); //this is actually identical to the above statement
		
		int bInt = intArray.get(0); //here, automatically convert from Integer to int
		bInt = intArray.get(0).intValue(); // also same to the above statement
		
		Integer aInteger = 9;
		System.out.println(aInteger == bInt);
		
		
	}

}
