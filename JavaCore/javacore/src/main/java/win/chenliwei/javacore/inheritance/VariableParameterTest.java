/**
 * @author: ChenLiwei
 * 2017-02-15
 * VariableParameterTest.java
 * Comments: it is to demonstrate the usage of variable parameters in java
 * it is convenient to input than just using Array
 */
package win.chenliwei.javacore.inheritance;

public class VariableParameterTest {

	public static void main(String ... args) { // notice here, String[] has been replaced with String ...
		//compare the below two statements, you will see the convenience.
		System.out.println(max(1.2,5,7,3,2/3,3.2,3));
		System.out.println(max2(new double[]{1.2,5,7,3,2/3,3.2,3}));
	}
	
	public static double max(double ... args){ //here, it can accept any number of parameters
		double rtValue = args[1];
		for(double x : args){
			if(x > rtValue) rtValue = x;
		}
		return rtValue;
	}
	public static double max2(double[] args){ //here, it can accept any number of parameters
		double rtValue = args[1];
		for(double x : args){
			if(x > rtValue) rtValue = x;
		}
		return rtValue;
	}
}
