/**
 * @author: ChenLiwei
 * 2017-02-20
 * CreateNewExceptionTest.java
 * Comments: It is to show the steps to create your own exception
 */
package win.chenliwei.javacore.exception;

import java.util.Random;

public class CreateNewExceptionTest {

	public static void main(String[] args) throws ChenliWeiException {
		if((new Random()).nextInt(7) == 3) throw new ChenliWeiException("Chenliwei is not avaliable at Wednsday");

	}

}

class ChenliWeiException extends Exception{

	private static final long serialVersionUID = 1L;

	public ChenliWeiException() {
		super();
		
	}

	public ChenliWeiException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public ChenliWeiException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public ChenliWeiException(String arg0) {
		super(arg0);
		System.out.println("Chenliwei's own exception");
	}

	public ChenliWeiException(Throwable arg0) {
		super(arg0);
		
	}
	
	
}