/**
 * @author: ChenLiwei
 * 2017-02-16
 * CloneTest.java
 * Comments: it is to demonstrate the right way to clone an object
 * 1. implements Cloneable interface
 * 2. change the access for Override function clone from protected to public
 * However, to implement fully deep clone an object, we need consider the mutable fields
 * as for the concept of mutable and immutable:
 * Mutable Objects: When you have a reference to an instance of an object, the contents of that instance can be altered
 * Immutable Objects: When you have a reference to an instance of an object, the contents of that instance cannot be altered 
 * Let's say, String class is immutable, all primitive types are immutable
 * to deep clone, mutable fields needs to be cloned correctly
 * We will add a mutable object hireDay to the Employee class 
 */
package win.chenliwei.javacore.clone;

import java.util.Date;
import java.util.GregorianCalendar;

import win.chenliwei.javacore.inheritance.Employee;

public class CloneTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws CloneNotSupportedException {
		
		Employee chenliwei = new Employee("0001","chenliwei",100);
		
		//the below is not copy, essentially points the same object
		Employee chenliweiCopy = chenliwei;
		System.out.println(chenliwei.getSalary());
		System.out.println(chenliweiCopy.getSalary());
		//when chenliwei get salary raised, then his copy get raised too
		chenliwei.salaryRaise(200);
		System.out.println(chenliwei.getSalary());
		System.out.println(chenliweiCopy.getSalary());
		
		//To clone the object, we need to implement the interface Cloneable
		//Since clone function is a protected function from Object
		//is it can only be used inside of Employee instead of outside
		
		CloneableEmployee chenliwei2 = new CloneableEmployee("0001","chenliwei",100);
		Date aDate = new GregorianCalendar(2016,0,12).getTime(); //here, we set the hiredate as 2016.11.12
		chenliwei2.setHireDay(aDate);
		try {
			CloneableEmployee chenliwei2SimpleClone = (CloneableEmployee) chenliwei2.clone();
			chenliwei2SimpleClone.salaryRaise(200);
			//notice here, The variable hireDay of both chenliwei2SimpleClone and chenliwei2
			//reference the same mutable object aDate, so, when aDate's value changes, the cloned changes too
			aDate.setDate(22); 
			System.out.println(chenliwei2.getSalary()); // unchanged
			System.out.println(chenliwei2SimpleClone.getSalary()); 
			System.out.println(chenliwei2.getHireDay().toString());
			System.out.println(chenliwei2SimpleClone.getHireDay().toString());
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		//to realize the deep copy, we needs to clone the mutable variable hireDay
		DeepCopyEmployee chenliwei3 = new DeepCopyEmployee("0001","chenliwei",100);
		chenliwei3.setHireDay(aDate);
		DeepCopyEmployee chenliwei3DeepCopy = (DeepCopyEmployee)chenliwei3.clone();
		chenliwei3DeepCopy.salaryRaise(300);
		//Since we realized deep copy clone, so even if we change the variable aDate
		aDate.setDate(3);
		System.out.println(chenliwei3.getSalary()); // unchanged
		System.out.println(chenliwei3DeepCopy.getSalary()); 
		System.out.println(chenliwei3.getHireDay().toString());
		System.out.println(chenliwei3DeepCopy.getHireDay().toString()); // will not be affected by the change of aDate
		
	}

}

class CloneableEmployee extends Employee implements Cloneable{

	public CloneableEmployee(String employeeId, String name, double salary) {
		super(employeeId, name, salary);
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException { //change from protected to public
		return super.clone(); //default behavior
	}
	
}


class DeepCopyEmployee extends Employee implements Cloneable{

	public DeepCopyEmployee(String employeeId, String name, double salary) {
		super(employeeId, name, salary);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		//further more, we needs to rewrite the clone method
		DeepCopyEmployee e = (DeepCopyEmployee) super.clone();
		e.setHireDay((Date) this.getHireDay().clone());
		return e;
	}
}