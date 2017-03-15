/**
 * @author: ChenLiwei
 * 2017-03-15
 * SocketTest.java
 * Comments: It is a simple test to use socket to get an html page and print it out
 * This is exactly an browser how to get the web page.
 * A socket describe a program with an internet address plus a port in a client/server mode.
 * when connection is created. you could open output stream to write request and open input stream
 * to read the response from the server.
 */
package win.chenliwei.javacore.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketTest {

	public static void main(String[] args) {
		try(Socket s = new Socket()){
			s.connect(new InetSocketAddress("horstmann.com",80),1000);
			OutputStream outStream = s.getOutputStream();
			outStream.write("GET / HTTP/1.1\n".getBytes());
			outStream.write("Host: horstmann.com\n".getBytes());
			outStream.write("\n\n".getBytes());
			InputStream inStream = s.getInputStream();
			Scanner in = new Scanner(inStream);
			while(in.hasNextLine()){
				System.out.println(in.nextLine());
			}
			outStream.close();
			in.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
