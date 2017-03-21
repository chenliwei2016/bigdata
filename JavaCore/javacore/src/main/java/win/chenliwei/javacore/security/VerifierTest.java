/**
 * @author: ChenLiwei
 * 2017-03-21
 * VerifierTest.java
 * Comments: It is to demonstrate the bytecode verifier of the JVM, if you use a hex editor
 * to modify the class file, then the JVM should detect the tampering
 * to make it run, Using notepad++ plugin HEX Editor and replace 3c with 3b ( means replace n with m)
 * then run the java program, will get the error: VerifyError
 * and after that, the program will be re-compiled based on the source file
 */
package win.chenliwei.javacore.security;

public class VerifierTest {

	public static void main(String[] args) {
		System.out.println("1+2=" + fun());
	}
	
	public static int fun(){
		int m;
		int n;
		m=1;
		n=2;
		int r=m+n;
		return r;
	}

}
