/**
 * @author: ChenLiwei
 * 2017-02-14
 * ArrayListTest.java
 * Comments: it demonstrate the implementation of variable length Array type
 * which also known as Generic Type Array
 */
package win.chenliwei.javacore.generictype;


import java.util.ArrayList;

import win.chenliwei.javacore.inheritance.Employee;

public class ArrayListTest {

	public static void main(String[] args) {
		//the below statement means to create a variable array capacity 4, which contains
		// elements in class Employee
		ArrayList<Employee> staff = new ArrayList<>(4);
		Employee zhangpeng = new Employee("002","zhangpeng",500000);
		staff.add(0,zhangpeng); // equivalent to staff[0] = zhangpeng;
		staff.add(zhangpeng);
		staff.add(zhangpeng);
		toString(staff);
		staff.add(1,new Employee("001","chenliwei",300000));
		toString(staff);
		
		//now the length of staff has reached the capacity 4, what if we add one more element?
		staff.add(new Employee("001","chenliwei",300000));
		toString(staff);
		System.out.println(staff.size()); //no problem, so capacity has no impact to the length
		
		//show how to update the element
		staff.set(0, new Employee("003","yaoxiang",300000));
		toString(staff);
		
		//get an element from the array
		System.out.println(staff.get(0));
		
		//remove elements from the array
		staff.remove(zhangpeng); //remove the first zhangpeng
		System.out.println("before");
		toString(staff);
		staff.remove(zhangpeng);
		System.out.println("after");
		toString(staff);
		System.out.println(staff.remove(1)); //another remove function
		
	}
	
	private static void toString(ArrayList<Employee> staff){
		for(Employee e : staff) System.out.println(e);
	}

}
