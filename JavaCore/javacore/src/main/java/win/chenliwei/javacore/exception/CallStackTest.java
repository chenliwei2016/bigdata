/**
 * @author: ChenLiwei
 * 2017-02-21
 * CallStackTest
 * Comments: It is to demonstrate how to print the call stack just like the debug does
 */


package win.chenliwei.javacore.exception;

import java.util.Scanner;

public class CallStackTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("please enter a nutural number:");
		int n = in.nextInt();
		System.out.println(factorial(n));
		in.close();
	}
	
	public static int factorial(int n){
		int r = 0;
		System.out.println("Now executing factorial(" + n + ")");
		Throwable t = new Throwable();
		StackTraceElement[] callstack = t.getStackTrace();
		for(StackTraceElement call : callstack) System.out.println(call);
		if(n > 1) r = n * factorial(n-1);
		else r = 1;
		System.out.println("return : " + r);
		return r;
	}

}
