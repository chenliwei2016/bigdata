package win.chenliwei.javacore.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayBasic {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int[] a = new int[4]; // this is preferred
		int b[] = new int[4];
		
		//the below for loop is flexible and powerful but not easy enough
		for(int i=0; i<a.length;i++){
			a[i] = i+1;
		}
		//the below for each loop is newer format and easy but not flexible
		for(int i : a){
			System.out.println(i);
		}
		
		//initialization and anonymous array
		int[] c = {1,2,3,4,5};
		b = new int[] {2,3,5,9}; //note here, this is very similar with the format of inner class
		
		//Copy Array with two ways, address or value
		a = b; //means both a and points to the address of b
		a = Arrays.copyOf(b, b.length);
		System.out.println(Arrays.toString(a));//can print array in this way
		a = Arrays.copyOfRange(b, 0, 2);
		System.out.println(Arrays.toString(a));//can copy part of an array
		a = Arrays.copyOf(b, b.length * 2);
		System.out.println(Arrays.toString(a));//if original array length is less than new length, then default value will be set
		
		//Sort array
		Arrays.sort(b);
		System.out.println(Arrays.toString(b));
		
		//Search Array
		System.out.println(Arrays.binarySearch(b, 3));
		
		//Fill Array to initialize
		Arrays.fill(a, 1);
		System.out.println(Arrays.toString(a));
		
		//Compare Arrays
		a = Arrays.copyOf(b, b.length);
		System.out.println(Arrays.equals(a, b));
		for(int i = b.length; i>0; i--){
			a[b.length - i] = b[i-1];
		}
		System.out.println(Arrays.equals(a, b));
		
		//the following code reverse a Integer array using Collection
		//Note: don't use int type because it is a primitive type
		Integer[] arrayInteger1 = new Integer[]{2,3,5,7};
		List<Integer> arrayIntegerList = Arrays.asList(arrayInteger1);
		Integer[] arrayInteger2 = Arrays.copyOf(arrayInteger1, arrayInteger1.length);
		Collections.reverse(arrayIntegerList);
		arrayInteger1 = arrayIntegerList.toArray(arrayInteger1);
		System.out.println(Arrays.toString(arrayInteger1));
		System.out.println(Arrays.toString(arrayInteger2));
		System.out.println(Arrays.equals(arrayInteger2,arrayInteger1));
		
	}

}
