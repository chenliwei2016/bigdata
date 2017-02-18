/**
 * @author: ChenLiwei
 * 2017年2月18日
 * FileTest.java
 * Comments: It is to demonstrate how to use File class, it considers directory as a file too
 */
package win.chenliwei.javacore.io;

import java.io.*;

public class FileTest {

	public static void main(String[] args) {
		String rootDirectory = "D:\\";
		File rootD = new File(rootDirectory);
		String[] files = rootD.list();
		for(String file:files){
			File oFile = new File(rootDirectory + file);
			System.out.println(oFile.getName());
			System.out.println(oFile.canExecute()?"Executable":"Not executable");
			System.out.println(oFile.canWrite()?"Writable":"Not writable");
			System.out.println(oFile.isDirectory()?"is a directory":"is a file");
			System.out.println(oFile.isHidden()?"hidden":"visible");
			System.out.println("size " + (oFile.length()/1024/1024) + "m");
		}
		
	}

}
