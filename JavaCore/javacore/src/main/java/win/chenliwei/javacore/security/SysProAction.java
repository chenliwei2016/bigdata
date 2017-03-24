/**
 * @author: ChenLiwei
 * 2017-03-24
 * SysProAction.java
 * Comments: 
 */
package win.chenliwei.javacore.security;

import java.security.PrivilegedAction;

public class SysProAction implements PrivilegedAction<String> {
	private String propertyName;

	public SysProAction(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String run() {
		return System.getProperty(propertyName);
	}
}
