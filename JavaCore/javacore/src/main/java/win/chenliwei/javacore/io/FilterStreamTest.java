/**
 * @author: ChenLiwei
 * 2017-02-19
 * Comments: There are more Stream classes enrich the basic stream class so have more powerful functions
 * and some Stream class can be enclosed together to realize flexible functions, we call them Filtered Streams
 * the group of stream is called Stream Stack.
 * let's try some typical Filtered Stream
 * ps. we could add BufferedInputStream and BufferedOutputStream to boost performance as buffer
 */
package win.chenliwei.javacore.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilterStreamTest {

	public static void main(String[] args) throws IOException {
		/*
		 * first of all, DataInputStream and DataOuputStream, they can read and write all primitive types without
		 * extra effort
		 */
		FileOutputStream fos = new FileOutputStream("c:\\maydelete.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);
		
		//usually, we use the below statement instead of the above 3 statements
		//DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("c:\\maydelete.txt")));
		
		String str = "ab 陈立伟";
		dos.writeUTF(str);
		dos.writeBytes(str); //write one low byte for each character
		dos.writeChars(str); //write two bytes for each character
		
		dos.writeByte(2);
		dos.writeInt(3);
		dos.writeLong(1111111111);
		dos.writeBoolean(true);
		dos.writeDouble(4.44);
		dos.writeChar(97);
		dos.write(str.getBytes());
		
		dos.close();
		
		FileInputStream fis = new FileInputStream("c:\\maydelete.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		
		System.out.println(dis.readUTF());
		byte[] bStr = new byte[6];
		int len = dis.read(bStr);
		System.out.println(new String(bStr,0,len));
		bStr = new byte[12];
		len = dis.read(bStr);
		System.out.println(new String(bStr,0,len));
		
		System.out.println(dis.readByte());
		System.out.println(dis.readInt());
		System.out.println(dis.readLong());
		System.out.println(dis.readBoolean());
		System.out.println(dis.readDouble());
		System.out.println(dis.readChar());
		
		bStr = new byte[20];
		len = dis.read(bStr);
		System.out.println(new String(bStr,0,len));
		
		dis.close();
		
		
		
	}

}
