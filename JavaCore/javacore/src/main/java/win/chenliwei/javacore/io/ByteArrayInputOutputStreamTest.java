/**
 * @author: ChenLiwei
 * 2017-02-19
 * Comments: The pair of ByteArrayInputStream and ByteArrayOutputStream is to create temporary files directly in memory
 * instead of hard disk
 * Suppose we want to encrypt what we read and send out, can try this pair
 */
package win.chenliwei.javacore.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteArrayInputOutputStreamTest {

	public static void main(String[] args) {
		String noEncrptedStr = "This is a secret that zhangpeng has an affair with his female boss";
		ByteArrayInputStream bais = new ByteArrayInputStream(noEncrptedStr.getBytes());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		encrypt(bais,baos,1);
		System.out.println("Now, the message has been encrpted, don't worry and send it out");
		System.out.println(baos.toString());
	}
	
	public static void encrypt(ByteArrayInputStream bais, ByteArrayOutputStream baos,int key){
		int asc2OfByte; // range from 0 to 255
		while((asc2OfByte = bais.read()) != -1){
			asc2OfByte = (asc2OfByte > 100) ? (asc2OfByte - key) : (asc2OfByte + key);
			baos.write(asc2OfByte);
		}
	}

}
