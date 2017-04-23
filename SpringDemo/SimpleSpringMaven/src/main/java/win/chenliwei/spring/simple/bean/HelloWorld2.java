/**
 * @author: ChenLiwei
 * @date 2017-04-23
 * HelloWorld2.java
 * comments: This class will be injected by constructor way
 */
package win.chenliwei.spring.simple.bean;

public class HelloWorld2 {
	private String msg;

	public HelloWorld2(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
