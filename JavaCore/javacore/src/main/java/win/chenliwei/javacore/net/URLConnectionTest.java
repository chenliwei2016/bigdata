/**
 * @author: ChenLiwei
 * 2017-03-16
 * URLConnectionTest.java
 * Comments: It is to demonstrate use URLConnection to get information instead of Socket directly
 * To make it run, you need to configure a web page which need simple authentication
 * I create a php page in my site, you can visit http://chenliwei.site/basic_authentication.php
 * the browser will pop up a dialog to let you input user name and password, then encode with Base64
 * after authentication succeeds, you will see the welcome page.
 * Now we simulate this process with URLConnection
 */
package win.chenliwei.javacore.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@SuppressWarnings("restriction")
public class URLConnectionTest {

	public static void main(String[] args) {
		String urlName = "http://chenliwei.site/basic_authentication.php";
		try {
			URL url = new URL(urlName);
			URLConnection connection = url.openConnection();
			String username = "chenliwei";
			String password = "welcome";
			String encoding = Base64.encode((username + ":" + password).getBytes());
			//Of course you could encrypt the password
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			connection.connect();
			
			Map<String, List<String>> headers = connection.getHeaderFields();
			headers.forEach((key, values)->{values.forEach(value->{System.out.println(key + ":" + value);});});
			
			Scanner in = new Scanner(connection.getInputStream());
			while(in.hasNext()) System.out.println(in.nextLine());
			in.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
