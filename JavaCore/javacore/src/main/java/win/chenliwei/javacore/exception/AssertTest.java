/**
 * @author: ChenLiwei
 * 2017-02-21
 * AssertTest.java
 * Comments: It is to demonstrate how to use assert
 * Assert is a mechanism to test the variable if qualifies the program during the development and testing
 * Assert statements will be removed when the program goes to production
 * So, it is not used often in the real world
 * Notice when run the program, we need add -ea to the VM argument in Eclipse
 */
package win.chenliwei.javacore.exception;

public class AssertTest {

	public static void main(String[] args) {
		System.out.println(factorial(5));
		System.out.println(factorial(0));
	}
	
	public static int factorial(int n){
		/*
		 * This function is for a nature number which means an int > 0
		 * however, we don't need to add this check in the program
		 * to avoid the program seems so fat. and we think in the production
		 * No one give a negative number or zero to the function
		 */
		assert n > 0 : n + " should be > 0"; 
		if(n == 1) return 1;
		else return n*factorial(n-1);
	}

}
