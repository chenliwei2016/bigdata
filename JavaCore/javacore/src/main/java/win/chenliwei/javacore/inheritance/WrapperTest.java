/**
 * @author: ChenLiwei
 * 2017-02-14
 * WrapperTest.java
 * Comments: Wrapper is the concept for the classes points the primitive types
 * for example, Integer class is for int, Double class is for double ....
 * Since generic type ArrayList does not allows primitive types
 * so if we needs a int array, we need define ArrayList<Integer> instead of ArrayList<int> 
 * but the system can automatically convert the int to Integer and vice versa.
 * Another usage for Wrapper is some static functions
 */
package win.chenliwei.javacore.inheritance;

import java.util.ArrayList;

import org.omg.CORBA.IntHolder;

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
		System.out.println(aInteger == bInt); // the answer is true because of auto converting.
		
		Integer x = 3;
		triple(x); //though it is not primitive type, the value of x will not be changed
		System.out.println(x);
		
		IntHolder y = new IntHolder(3); // it is recommended to use org.omg.CORBA.IntHolder to change the value
		triple(y);
		System.out.println(y.value);
		
		//actually it is meaningless, it is no different with the below code block;
		IntHold z = new IntHold(3);
		triple(z);
		System.out.println(z.value);
		
		//some static function added in Wrapper such as
		String str = "3456";
		int intStr = Integer.parseInt(str);
		System.out.println(intStr*2);
	}
	
	public static void triple(Integer x){
		 x *= 3;
	}
	
	public static void triple(IntHolder x){
		x.value *= 3;
	}
	
	public static void triple(IntHold x){
		x.value *= 3;
	}

}

class IntHold{
	public int value;

	public IntHold(int value) {
		this.value = value;
	}
}