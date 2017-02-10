package win.chenliwei.javacore.oobasic;

/*
 * this demo demonstrate how to use static import
 * it makes codes clean
 */

import static java.lang.System.out; //static import a static field
import static java.lang.Math.*; //static import some static methods

public class StaticImportTest {

	public static void main(String[] args) {
		out.println(pow(2,3) + sqrt(4));
		//it is short for the below, how do you think?
		System.out.println(Math.pow(2, 3) + Math.sqrt(4));
	}

}
