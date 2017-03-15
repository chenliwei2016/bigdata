/**
 * @author: ChenLiwei
 * 2017-03-15
 * EchoServer.java
 * Comments: It is to demonstrate how to setup a socket server to accept and response
 * Guide to run this program:
 * 1. run the java
 * 2. telent localhost:8189
 */
package win.chenliwei.javacore.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		try(ServerSocket server = new ServerSocket(8189)){
			try(Socket incoming = server.accept()){
				InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();
				try(Scanner in = new Scanner(inStream)){
					PrintWriter out = new PrintWriter(outStream,true);
					out.println("You connected to the server, Enter Bye to exit");
					boolean done = false;
					while(in.hasNextLine() && !done){
						String line = in.nextLine();
						if(line.trim().equalsIgnoreCase("bye")){
							done = true;
							out.println("Good Bye!");
							out.close();
						} else {
							out.println("Echo: " + line);
						}
					}
				}
			}
		}
	}

}
