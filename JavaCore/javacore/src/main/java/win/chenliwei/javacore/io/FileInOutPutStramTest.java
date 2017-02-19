/**
 * @author: ChenLiwei
 * 2017-02-19
 * Comments: It is to show the usage of the pair of FileInputStream and FileOutputStream
 * which extends the InputStream and OutputStream and File oriented
 * but these 2 class can only operate byte array to read or write, therefore, it is inconvenient 
 */
package win.chenliwei.javacore.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInOutPutStramTest {

	public static void main(String[] args) throws IOException {
		
		String str = "Just a test";
		MyInputStream mis = new MyInputStream(str.getBytes());
		mis.read();
		mis.close();
		System.out.println();
		
		FileInputStream fis = new FileInputStream("c:\\employee.db");
		byte[] eName = new byte[8];
		fis.read(eName);
		for(byte b : eName) System.out.print((char) b);
		byte[] eAge = new byte[4]; //a int type occupy 4 bytes
		fis.read(eAge);
		int iAge = unsigned4BytesToInt(eAge,0); //here, you can say it is how difficult to convert a 4 bytes array to an int
		System.out.println(iAge);
		//that's why we use readInt, readLong ....
		//However, FileInputStream class can only deal with bytes, not characters
		//if we wanna readInt, we need DataInputStream
		fis.close();
		
		FileOutputStream fos = new FileOutputStream("c:\\maydelete.txt");
		String sName = "zhangpen";
		iAge = 43;
		fos.write(sName.getBytes());
		fos.write(intToByteArray(iAge)); // see it is how difficult to write data
		fos.close();

	}
	
	public static byte[] intToByteArray(int s) {  
        byte[] targets = new byte[4];  
        for (int i = 0; i < 4; i++) {  
            int offset = (targets.length - 1 - i) * 8;  
            targets[i] = (byte) ((s >>> offset) & 0xff);  
        }  
        return targets;  
    }  

	
	public static int unsigned4BytesToInt(byte[] buf, int pos) {  
       int firstByte = 0;  
       int secondByte = 0;  
       int thirdByte = 0;  
       int fourthByte = 0;  
       int index = pos;  
       firstByte = (0x000000FF & ((int) buf[index]));  
       secondByte = (0x000000FF & ((int) buf[index + 1]));  
       thirdByte = (0x000000FF & ((int) buf[index + 2]));  
       fourthByte = (0x000000FF & ((int) buf[index + 3]));  
       index = index + 4;  
       return (int) (((int) (firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte)) & 0xFFFFFFFFL);  
   }  


}

/*
 *  to honor the top of the stream class
 */
class MyInputStream extends InputStream{
	
	private  byte[] inArray;
	
	public MyInputStream(byte[] inArray) {
		this.inArray = inArray;
	}

	@Override
	public int read() throws IOException {
		for(byte c:inArray) {System.out.print((char) c);}
		return 0;
	}
	
	
	
}