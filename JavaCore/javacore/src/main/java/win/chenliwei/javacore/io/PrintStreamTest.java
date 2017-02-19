/**
 * @author: ChenLiwei
 * 2017-02-19
 * Comments: To use PrintStream as a sub class of OutputStream
 * it provides rich println functions
 * A generally applied in daily work is System.out is just a PrintStream
 * The dirfference is the System.out point the screen as the output
 */
package win.chenliwei.javacore.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {

	public static void main(String[] args) throws FileNotFoundException {
		//now, we redirect the println to a file
		PrintStream ps = new PrintStream(new FileOutputStream("c:\\maydelete.txt"));
		//now, we write something into the file
		ps.println("whatever");
		ps.close();
		
		//we can also redirect the output to the screen again
		ps = System.out;
		ps.println("see, it displays in screen again");
		ps.close();
	}

}
