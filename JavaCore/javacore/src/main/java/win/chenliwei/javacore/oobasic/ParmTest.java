package win.chenliwei.javacore.oobasic;

/*
 * this demo demonstrate there is only value pass for a method in Java
 * no any address pass in Java
 */

public class ParmTest {

	public static void tripleValue(double x){
		x *= 3;
	}

	public static void main(String[] args) {
		/* as for primitive type, pass the value of this variable to a method
		 * we cannot change the value of original variable
		 */
		double originalValue = 3;
		tripleValue(originalValue);
		System.out.println(originalValue);
		
		/* as for Object type, it also pass the value of an object
		 * but, the value is specific to the reference of the object, not the content of the object
		 */
		Employee chenliwei = new Employee("chenliwei", 30, "male", 500000);
		System.out.println(chenliwei.getSaraly());
		chenliwei.raiseSalary(50000);
		System.out.println(chenliwei.getSaraly());
	}

}
