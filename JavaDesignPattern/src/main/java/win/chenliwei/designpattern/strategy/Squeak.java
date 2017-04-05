/**
 * @author: ChenLiwei
 * 2017-04-05
 * Squeak.java
 * Comments: 
 */
package win.chenliwei.designpattern.strategy;

public class Squeak implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("I am making sound Zhi Zhi Zhi");
	}

}
