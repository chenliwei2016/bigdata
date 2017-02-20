/**
 * @author: ChenLiwei
 * 2017-02-20
 * ProactiveThrowExceptionTest.java
 * Comments: It is to demonstrate how to throw a specific exception which already defined in system
 * Suppose, we received a file with declared length, but actually, it breaks and the reading stops
 * we want to throw this exception with a meaningful message
 */
package win.chenliwei.javacore.exception;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class ProactiveThrowExceptionTest {

	public static void main(String[] args) throws IOException {
		readFile("ExceptionTypes.txt",1000);
	}
	
	public static void readFile(String file,int len) throws IOException{
		FileInputStream reader = new FileInputStream(file);
		int cnt=0;
		int asc2Int;
		while((asc2Int = reader.read()) != -1) {
			cnt++;
			System.out.print((char)asc2Int);
		}
		System.out.println("\n");
		reader.close();
		if(cnt < len) throw new EOFException("This exception throwed by chenliwei: " + cnt + " < " + len);
	}

}
