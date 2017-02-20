/**
 * @author: ChenLiwei
 * 2017-02-20
 * ExceptionTypes.java
 * Comments: It is to list types of exceptions.
 * as the book says: there are 2 top types of exception
 * 1. Errors
 * 2. Exceptions
 * Error means inner error and run out of resource, the application should not throw this kind of exception
 * Error is very seldom to see.
 * Exception is divided into 2 types: 
 * 	a. RuntimeException, this kind of Exception must be thrown by program itself.
 * 	b. other Exception such as IO Exception, this kind happens because of depending on outside systems
 * As for RuntimeException, we should not throw exception, instead of that, we need research our codes to avoid the Exception
 * As for other Exception, we also called "Checked Exception", that is what we need to throw
 * The compiler will not let us go if we don't throw or deal with the Checked Exception
 * Remember: Throw or try catch the checked exceptions only
 */
package win.chenliwei.javacore.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import win.chenliwei.javacore.inheritance.Employee;
import win.chenliwei.javacore.inheritance.Manager;

@SuppressWarnings("unused")
public class ExceptionTypes {

	public static void main(String[] args) throws FileNotFoundException {
		/*
		 * RuntimeException, This kind of exceptions should have avoided by programmer himself
		 */
		//1. Wrong cast between types
		Employee[] staff = new Employee[3];
		//Manager[] boss = (Manager[]) staff; //This will throw a RuntimeException: ClassCastException
		
		//2. Visit out of bound of an Array
		//Employee doesNotExist = staff[3]; //This will throw a RuntimeException: ArrayIndexOutOfBoundsException
		
		//3. Visit null pointer of an object
		//staff[0].getName(); //This will throw a RuntimeException:  NullPointerException
		
		/*
		 * Checked Exception
		 */
		//1. Try to open a file which does not exist
		//FileInputStream fis = new FileInputStream("c:\\doesnotexist.txt"); //This will throw a Checked Exception: FileNotFoundException
		
		//2. Continue read after the end of the file
		RandomAccessFile raf = new RandomAccessFile("ExceptionTypes.txt","rw");
		try {
			raf.write("A demo file for ExceptionTypes.java".getBytes());
			//now, it goes to the end of file
			raf.seek(raf.length());
			//raf.readDouble(); // It will throw a checked exception: EOFException
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//3. The class does not exist
		try {
			System.out.println(Class.forName("win.chenliwei.javacore.exception.ExceptionTypes").getName());
			Class.forName("ClassNotExist").getName(); // It will throw a check exception: ClassNotFoundException
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
