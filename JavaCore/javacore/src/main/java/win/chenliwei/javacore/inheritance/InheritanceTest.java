/**
 * @author: ChenLiwei
 * 2017-02-10
 * InheritanceTest.java
 * Comments: it demonstrate polymorphism in Java, it called "is a" rule
 * that is a place suits for a parent class, it can be replaced with a child class
 */
package win.chenliwei.javacore.inheritance;

import java.util.Arrays;

public class InheritanceTest {

	public static void main(String[] args) {
		Manager boss = new Manager("001","chenliwei",100000);
		
		Employee[] emps = new Employee[3];
		
		//Manager Class is child class of Employee class, so it can store a boss
		emps[0] = boss;
		
		System.out.println(emps[0].getName());
		
		Manager[] bosses = new Manager[3];
		Arrays.fill(bosses, boss);
		Employee[] staff = bosses;
		
		/*
		 * the above, a parent array refers to his child array, it is legal
		 * the variable staff and bosses both points the same array, which is only allowed Manager
		 */
		
		System.out.println(staff[0].getName());
		
		//Since the array bosses has declared as Manager, so the below statement success to compile
		//but will error out when run, because the array is not allowed Employee class
		//staff[0] = new Employee("002","zhangpeng",500000);
		
	}

}

class Employee{
	private String employeeId;
	private String name;
	private double salary;
	public Employee(String employeeId, String name, double salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.salary = salary;
	}
	public void salaryRaise(double amount){
		this.salary += amount;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}
}

class Manager extends Employee{
	private double bouns;
	
	public Manager(String employeeId, String name, double salary) {
		super(employeeId, name, salary);
		this.setBouns(0);
	}

	public double getBouns() {
		return bouns;
	}

	public void setBouns(double bouns) {
		this.bouns = bouns;
	}
	
}