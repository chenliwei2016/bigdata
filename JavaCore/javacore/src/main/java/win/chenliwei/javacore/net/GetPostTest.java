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
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class GetPostTest {

	public static void main(String[] args) throws IOException {
		String urlStr = "http://www.chenliwei.site/formdemoreply.php";
		System.out.println("*****************************************");
		System.out.println("the reply from server:");
		//doGet(urlStr,"chenliwei","chenliwei@chenliwei.win");
		doPost(urlStr,"chenliwei","chenliwei@chenliwei.win");
		System.out.println("*****************************************");
	}
	public static void doGet(String urlStr,String name,String email){
		try {
			URL url = new URL(urlStr +"?name=" + name + "&email=" + email);
			URLConnection conn = url.openConnection();
		try(Scanner in = new Scanner(conn.getInputStream())){
				while(in.hasNext()) System.out.println(in.nextLine());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void doPost(String urlStr,String name,String email) throws IOException{

			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			try(PrintWriter out = new PrintWriter(conn.getOutputStream())){
				out.println("name=" + URLEncoder.encode(name,"UTF-8") + "&email=" + URLEncoder.encode(email,"UTF-8") );
			}
			StringBuilder response = new StringBuilder();
			try(Scanner in = new Scanner(conn.getInputStream())){
				while(in.hasNext()) {
					response.append(in.nextLine());
					response.append("\n");
				}
			} catch(IOException e){
				if(!(conn instanceof HttpURLConnection)) throw e;
				InputStream err = ((HttpURLConnection)conn).getErrorStream();
				if(err == null) throw e;
				Scanner in = new Scanner(err);
				response.append(in.nextLine());
				in.close();
			}
			System.out.println(response);
	}
}
