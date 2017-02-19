/**
 * @author: ChenLiwei
 * 2017-02-19
 * Comments: It is to demonstrate how to use PipedInputStream and PipedOutputStream as a communication
 * pipe between two threads, let's do it
 */
package win.chenliwei.javacore.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedInputOutputStreamTest {

	public static void main(String[] args) throws IOException {
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream();
		pos.connect(pis);
		
		Runnable threadA = new Runnable(){

			@Override
			public void run() {
				try {
					pos.write("To threadB: please come to my office when read this message".getBytes());
					System.out.println("I am threadA and I will get a long time sleep to have a good rest");
					System.out.println("I believe threadB will receive my message");
					Thread.sleep(10000);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		threadA.run();
		Runnable threadB = new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("Hi, I am threadB, I just wake up from a long time sleep");
					System.out.println("let me check if threadA left a meesage for me");
					byte[] b = new byte[1024];
					int len = pis.read(b);
					if(len > 0) {
						System.out.println("Oh, there is a message for me. he says:");
						System.out.println(new String(b,0,len));
					}
				} catch (InterruptedException | IOException e) {
					e.printStackTrace();
				}
			}
		};
		threadB.run();
		pos.close();
		pis.close();
	}

}
