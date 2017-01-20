package win.chenliwei.javacore.oobasic;

/*
 * this demo demonstrate an method of class can visit any object's private variable belong this class
 */
public class VisitOtherObjectPrivateVars {

	public static void main(String[] args) {
		Employee zhangpeng = new Employee("zhangpeng",23,"male",50000);
		Employee yaoxiang = new Employee("yaoxiang",28,"male",200);
		System.out.println(zhangpeng.seeOthersSalary(yaoxiang));
	}

}

@SuppressWarnings("unused")
class Employee{
	private String name;
	private int age;
	public double getSaraly() {
		return saraly;
	}
	private String sex;
	private double saraly;
	public Employee(String name, int age, String sex, double saraly) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.saraly = saraly;
	}
	public double seeOthersSalary(Employee employee){
		return employee.saraly;
	}
	public void raiseSalary(double amount){
		this.saraly += amount;
	}
}