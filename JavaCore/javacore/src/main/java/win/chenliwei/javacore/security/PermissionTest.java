/**
 * @author: ChenLiwei
 * 2017-03-24
 * PermissionTest.java
 * Comments: It is to demonstrate the customized permission
 */
package win.chenliwei.javacore.security;

public class PermissionTest {

	public static void main(String[] args) {
		System.setProperty("java.security.policy", "src/main/resources/PermissionTest.policy");
		//The policy file says that exception happens if input sex,drugs or C++
		System.setSecurityManager(new SecurityManager());
		insertWords("sex");
	}
	private static void insertWords(String text) {
		try{
			WordCheckPermission permission = new WordCheckPermission(text, "insert");
			SecurityManager manager = System.getSecurityManager();
			if(manager != null){
				manager.checkPermission(permission);
			}
			System.out.println("inserted: " + text); 
		} catch(SecurityException e){
			System.out.println("Sorry, but I cannot do that, try another word");
			e.printStackTrace();
		}
	}
}

