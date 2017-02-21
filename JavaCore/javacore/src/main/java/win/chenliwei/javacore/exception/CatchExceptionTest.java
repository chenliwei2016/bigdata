/**
 * @author: ChenLiwei
 * 2017-02-21
 * CatchExceptionTest.java
 * Comments: There are 2 ways to deal with exceptions:
 * 	1. Just throw it out, mostly, this is the best way if you don't know how to deal with it
 * 	2. Catch the exception and deal with it if you know how to handle it
 * If no anywhere to catch exception, the application will stop and break
 * Catch the exceptions using try ... catch ... finally structure
 * From Java 7, we have a new feature which is oriented to resource, that is try(resource) catch
 */
package win.chenliwei.javacore.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CatchExceptionTest {

	public static void main(String[] args) throws ClassNotFoundException {
		InputStream in = null; //if declare in within try block, finally will not visit it
		try {
			in = new FileInputStream("ExceptionTypes.txt");
			in.read();
			in.equals(Class.forName("java.lang.String").newInstance());
			Thread.sleep(10000);
		} catch (FileNotFoundException e ) {//notice that there are 2 catch, the level IOException more general than FileNotFoundException
			System.out.println("The file is not found");
			e.printStackTrace();
		} catch (IOException | InterruptedException e) { //notice that if 2 exceptions have no hierarchy, we could use | sign to list them together
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//we could generate a log and then continue to throw the Exception
			System.out.println("Check the class name" + e.getMessage());
			throw new ClassNotFoundException(e.getMessage());
		} finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * From Java 7 we can use try(resource) catch to avoid the closure of a resource
		 */
		
		try(InputStream in2 = new FileInputStream("ExceptionTypes.txt");
			InputStream in3 = new FileInputStream("ExceptionTypes.txt");){
			//This way, we do not worry about the closure of the opened resources and don't need a finally
			in2.read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
