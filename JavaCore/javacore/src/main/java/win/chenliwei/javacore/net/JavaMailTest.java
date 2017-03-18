/**
 * @author: ChenLiwei
 * 2017年3月18日
 * JavaMailTest.java
 * Comments: It is to demonstrate how to use java mail API to send mails to others
 */
package win.chenliwei.javacore.net;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailTest {

	public static void main(String[] args) throws IOException, MessagingException {
		Properties pros = new Properties();
		try(InputStream in = Files.newInputStream(Paths.get("mail.properties"))){
			pros.load(in);
		}
		List<String> message = Files.readAllLines(Paths.get(args[0]), Charset.forName("UTF-8"));
		String from = message.get(0);
		String to = message.get(1);
		String subject = message.get(2);
		StringBuilder text = new StringBuilder();
		for(int i=3; i<message.size();i++){
			text.append(message.get(i));
			text.append("\n");
		}
		
//		//Consider security, we need you input password
//		Console console = System.console();
//		String password = new String(console.readPassword("Please enter the password for the mail " + from));
		Scanner in = new Scanner(System.in);
		String password = in.nextLine();
		in.close();
		Session mailSession = Session.getDefaultInstance(pros);
		MimeMessage mail = new MimeMessage(mailSession);
		mail.setFrom(from);
		mail.addRecipient(RecipientType.TO, new InternetAddress(to));
		mail.setSubject(subject);
		mail.setText(text.toString());
		Transport tr = mailSession.getTransport();
		try{
			tr.connect(null, password);
			tr.sendMessage(mail, mail.getAllRecipients());
		} finally{
			tr.close();
		}
		
	}

}
