/**
 * @author: ChenLiwei
 * 2017-02-23
 * GenericMehodTest.java
 * Comments: Besides for generic class, we still have generic method
 * it can be defined both in regular class and generic class
 */
package win.chenliwei.javacore.generictype;

public class GenericMehodTest {

	public static void main(String[] args) {
		String[] strDisorder = {"Helen","Jerry","Bob","Elane"};
		Integer[] intDisorder = {100,300,50,30,200};
		
		System.out.println("The minimum name is " + ArrayAlg.<String>minmax(strDisorder).getFirst());
		System.out.println("The maximum name is " + ArrayAlg.minmax(strDisorder).getSecond());
		
		System.out.println("The minimum number is " + ArrayAlg.<Integer>minmax(intDisorder).getFirst());
		System.out.println("The maximum number is " + ArrayAlg.minmax(intDisorder).getSecond());
	}

}

class ArrayAlg{
	public static <T extends Comparable<T>> Pair<T> minmax(T[] a){
		if( (a == null ) || (a.length == 0) ) return null;
		T min = a[0];
		T max = a[0];
		for(T t : a){
			if (t.compareTo(min) < 0) min = t;
			if (t.compareTo(max) > 0) max = t;
		}
		return new Pair<>(min,max);
	}
}