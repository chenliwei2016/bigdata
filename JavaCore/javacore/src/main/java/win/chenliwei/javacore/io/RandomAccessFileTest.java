/**
 * @author: ChenLiwei
 * 2017-02-18
 * Comments: Random access file class provides method to control file just like a database
 */
package win.chenliwei.javacore.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest{
	
	public static void main(String[] args) throws IOException{
		Employee chenliwei = new Employee("chenliwei",35);
		Employee zhangpeng = new Employee("zhangpeng",42);
		Employee yaoxiang = new Employee("yaoxiang",25);
	
		RandomAccessFile  raf = new RandomAccessFile("c:\\employee.db","rw");
		//write the information into the database
		raf.seek(raf.length()); //move the point to the end of file
		raf.write(chenliwei.getName().getBytes());
		raf.writeInt(chenliwei.getAge());
		raf.write(zhangpeng.getName().getBytes());
		raf.writeInt(zhangpeng.getAge());
		raf.write(yaoxiang.getName().getBytes());
		raf.writeInt(yaoxiang.getAge());
		raf.close();
		
		raf = new RandomAccessFile("c:\\employee.db","r");
		raf.skipBytes(12); // skip the first record
		byte[] name = new byte[8];
		raf.read(name);
		System.out.println("the second employee's name is " + new String(name));
		System.out.println("and his/her age is " + raf.readInt());
		
		raf.seek(0); //back to begin of file
		raf.read(name);
		System.out.println("the first employee's name is " + new String(name));
		System.out.println("and his/her age is " + raf.readInt());

		
		raf.close();
		
	}

}

class Employee{
	private static final int LENGTH = 8;
	private String name;
	private int age;
	public Employee(String name, int age) {
		if(name.length() < LENGTH ){
			this.name = name;
		} else{
			this.name = name.substring(0, LENGTH);
		}
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.length() < LENGTH ){
			this.name = name;
		} else{
			this.name = name.substring(0, LENGTH);
		}
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
