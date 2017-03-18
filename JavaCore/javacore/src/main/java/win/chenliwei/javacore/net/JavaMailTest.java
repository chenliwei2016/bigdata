/**
 * @author: ChenLiwei
 * 2017年3月18日
 * JavaMailTest.java
 * Comments: It is to demonstrate how to use java mail API to send mails to others
 * Since I am in China, cannot access gmail directly, luckily, hotmail can help the test.

 */
package win.chenliwei.javacore.net;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class JavaMailTest {

	public static void main(String[] args) throws Exception {
		String user,password;
		user = args[0];
		try(Scanner in = new Scanner(System.in)){
			System.out.println("Enter the password for " + user);
			password = in.nextLine();
		}
		String nickName = "";
		Mail mail = new Mail(user,password);
		try {  
			nickName=javax.mail.internet.MimeUtility.encodeText("ChenLiWei");  
		} catch (UnsupportedEncodingException e) {  
			e.printStackTrace();  
		}   
		mail.setFrom(nickName+"<"+user+">"); //Notice Here, you need set a nickname
		mail.setTo(new String[]{args[1]});
		mail.setSubject("JavaMail Testing");
		mail.setBody("Greeting from " + nickName + " using mailbox " + user);
		mail.send();
		System.out.println("The mail has been sent!");
	}

}
