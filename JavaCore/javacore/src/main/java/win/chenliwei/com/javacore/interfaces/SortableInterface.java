/**
 * @author: ChenLiwei
 * 2017-02-15
 * SortableInterface.java
 * Comments: it is to demonstrate how to redefine Employee class to suit to sort
 */
package win.chenliwei.com.javacore.interfaces;

import java.util.Arrays;

import win.chenliwei.javacore.inheritance.Employee;

public class SortableInterface {

	public static void main(String[] args) {
		Employee[] nonSortableEmp = new Employee[3];
		nonSortableEmp[0] = new Employee("001","zhangpeng",500000);
		nonSortableEmp[1] = new Employee("002","chenliwei",300);
		nonSortableEmp[2] = new Employee("003","yaoxiang",20000000);
		
		//Arrays.sort(nonSortableEmp); //Error:cannot be cast to java.lang.Comparable
		
		SortableEmployee[] sortableEmp = new SortableEmployee[3];
		
		sortableEmp[0] = new SortableEmployee("001","zhangpeng",500000);
		sortableEmp[1] = new SortableEmployee("002","chenliwei",300);
		sortableEmp[2] = new SortableEmployee("003","yaoxiang",200);
		
		Arrays.sort(sortableEmp);
		
		System.out.print(Arrays.deepToString(sortableEmp));
		
		
	}

}

class SortableEmployee extends Employee implements Comparable<Employee>{

	public SortableEmployee(String employeeId, String name, double salary) {
		super(employeeId, name, salary);
		
	}

	@Override
	public int compareTo(Employee e) {
		return Double.compare(this.getSalary(), e.getSalary()); //this way, the employee Array can be sortable
	}
	
}
