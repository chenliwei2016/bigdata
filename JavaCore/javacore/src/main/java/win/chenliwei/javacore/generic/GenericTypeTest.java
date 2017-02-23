/**
 * @author: ChenLiwei
 * 2017-02-23
 * GenericTypeTest.java
 * Comments: Generic programming means the code could be reused by multiply type
 * to make the code more general
 * One can pass the type parameter to the class to replace the generic type
 * This program will define a generic type class 
 */
package win.chenliwei.javacore.generic;

/*
 * Generic type usually use upper case single character
 * E means the type of the ELEMENT of a set
 * K, V means KEY and VALUE
 * T,U ... means any type
 */

 class Pair<T> { //<T> means T is a generic type, it could be any type, like String, Integer ... etc
	private T first;
	private T second;
	
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	

	public Pair() {
		this.first = null;
		this.second = null;
	}


	public T getFirst() {
		return first;
	}


	public void setFirst(T first) {
		this.first = first;
	}


	public T getSecond() {
		return second;
	}


	public void setSecond(T second) {
		this.second = second;
	}

}

/*
 *  We could define another generic class such as key-value pair
 */
 
 class KeyValuePair<K,V>{
	 private K key;
	 private V value;
	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	 
 }
 
public class GenericTypeTest {

	public static void main(String[] args) {
		Pair<String> strPair = new Pair<>("chenliwei","zhangpeng");
		System.out.println(strPair.getFirst() + " and " + strPair.getSecond());
		
		KeyValuePair<String, Integer> kvPair = new KeyValuePair<String, Integer>("chenliwei",35);
		System.out.println(kvPair.getKey() + ":" + kvPair.getValue());

	}

}
