/**
 * @author: ChenLiwei
 * 2017-02-19
 * Comments: Save an object as a file and transfer to another pc or system, it is called Serialization
 * Java support this with ObjectInputStream and ObjectOutputStream
 */
package win.chenliwei.javacore.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerializableEmployee chenliwei = new SerializableEmployee("chenliwei",35);
		SerializableEmployee zhangpeng = new SerializableEmployee("zhangpeng",43);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c:\\maydelete.txt"));
		oos.writeObject(chenliwei);
		oos.writeObject(zhangpeng);
		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c:\\maydelete.txt"));
		chenliwei = (SerializableEmployee) ois.readObject();
		System.out.println(chenliwei.getName());
		zhangpeng = (SerializableEmployee) ois.readObject();
		System.out.println(zhangpeng.getName());
		ois.close();
		
	}

}

class SerializableEmployee  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public SerializableEmployee(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
}
