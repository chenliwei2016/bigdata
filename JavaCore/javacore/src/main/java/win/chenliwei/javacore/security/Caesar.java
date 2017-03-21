/**
 * @author: ChenLiwei
 * 2017-03-21
 * Caesar.java
 * Comments: Is is a tool class to encrypt a file with extension .class to .caesar
 * The main accept 2 args: class name and encrypt key
 */
package win.chenliwei.javacore.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class Caesar {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		if(args.length != 2){
			System.out.println("Usage: cd target/classes");
			System.out.println("Usage: java win.chenliwei.javacore.security.Caesar class key");
			return;
		}
		String[] paths = System.getProperty("java.class.path").replace("\\", "/").split(";");
		String fromname = args[0].replace(".", "/") + ".class";
		String toname = null;
		boolean found = false;
		for(String path : paths){
			if(Paths.get(path + "/" + fromname) != null){
				fromname = path + "/" + fromname;
				toname = fromname.replace(".class", ".caesar");
				found = true;
				break;
			}
		}
		if(!found){
			System.out.println(fromname + " not found under all class paths");
			return;
		}
		try(FileInputStream in = new FileInputStream(fromname);
			FileOutputStream out = new FileOutputStream(toname)){
			int key = Integer.parseInt(args[1]);
			int ch;
			while((ch = in.read()) != -1){
				byte c = (byte) (ch + key);
				out.write(c);
			}
		}
		System.out.println(args[0] + " has been encrypted as " + toname);
	}

}
