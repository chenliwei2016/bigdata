/**
 * @author: ChenLiwei
 * @date 2017-04-23
 * HelloWorld.java
 * comments: This class will be injected by setter way
 * Notice: All the classes used by Spring names "Bean"
 */
package win.chenliwei.spring.simple.bean;


public class HelloWorld {
	private String message;
	
	public HelloWorld(){}

	public HelloWorld(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
