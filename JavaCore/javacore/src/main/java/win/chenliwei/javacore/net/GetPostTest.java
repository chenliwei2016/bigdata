/**
 * @author: ChenLiwei
 * 2017-03-17
 * GetPostTest.java
 * Comments: It is to demonstrate the two ways to send data to server:
 * 	1.Get, it could send data in the url plus ?parameter=value&parameter=value..., this format is simple
 * 		however, most browser has limitation in length of url, so it is not common
 * 	2.Post, this is can send data as much as you can, it provides a static html page with a form let client
 * 		input the data, and then send them all to the server which often is a server side script such as asp,php etc
 * Before our test, I set up a server side php in www.chenliwei.win/formdemoreply.php, but if you are in China
 * please use www.chenliwei.site/formdemoreply.php, I applied this domain for access.
 * it can response both in Get and Post way, the static html page is www.chenliwei.site/formdemo.html
 */
package win.chenliwei.javacore.net;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class GetPostTest {

	public static void main(String[] args) {
		String urlStr = "http://www.chenliwei.win/formdemoreply.asp";
		System.out.println("*****************************************");
		System.out.println("the reply from server:");
		doGet(urlStr,"chenliwei","chenliwei@chenliwei.win");
		System.out.println("*****************************************");
	}
	public static void doGet(String urlStr,String name,String email){
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("Authorization", "Basic " + Base64.encode((name + ":" + email).getBytes()));
			try(Scanner in = new Scanner(conn.getInputStream())){
				while(in.hasNext()) System.out.println(in.nextLine());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
