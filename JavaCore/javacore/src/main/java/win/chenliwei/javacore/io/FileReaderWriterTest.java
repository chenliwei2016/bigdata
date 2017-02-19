/**
 * @author: ChenLiwei
 * 2017-02-19
 * Comments: The pairs of Readers and Writers means the classes with suffix Reader or Writer
 * which are specific to deal with characters objects, in contrast, InputStram and OutputStream
 * are only specific to deal with bytes object, they are more powerful but not convenient enough
 * Here, we need to figure out 2 concepts: text file and binary file
 * Text file means the file only contains visible characters, but binary file
 * must contains bytes besides for characters
 */
package win.chenliwei.javacore.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriterTest {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("c:\\employee.db");
		char[] eInfo = new char[1024];
		int len = fr.read(eInfo);
		System.out.println(new String(eInfo,0,len));
		/*
		 * since we wrote the binary information into the file, so it is a binary file
		 * so it will display invisible characters
		 * that's why Reader and Writer only can read characters, other words, can only deal with text file
		 */
		fr.close();
		
		File file = new File("c:\\maydelete.txt");
		if(file.exists()) file.delete();
		FileWriter fw = new FileWriter(file);
		System.out.println(fw.getEncoding());
		String eName = "陈立伟";
		int eAge = 35;
		fw.write(eName + eAge);
		fw.close();
		
		fr = new FileReader(file);
		System.out.println(fr.getEncoding());
		len = fr.read(eInfo);
		System.out.println(new String(eInfo,0,len));
		fr.close();
	}

}
