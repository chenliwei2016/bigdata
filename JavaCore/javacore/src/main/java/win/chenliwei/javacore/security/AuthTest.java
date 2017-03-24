/**
 * @author: ChenLiwei
 * 2017-03-24
 * AuthTest.java
 * Comments: It is to demonstrate the authentication and authorization
 */
package win.chenliwei.javacore.security;

import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class AuthTest {

	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		try{
			LoginContext context = new LoginContext("Login1");
			context.login();
			System.out.println("Login successful.");
			Subject subject = context.getSubject();
			System.out.println("Subject = " + subject.toString());
			PrivilegedAction<String> action = new SysProAction("user.home");
			String result = Subject.doAsPrivileged(subject, action, null);
			System.out.println(result);
			context.logout();
		} catch(LoginException e){
			e.printStackTrace();
		}
	}

}
