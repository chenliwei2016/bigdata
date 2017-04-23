/**
 * @author: ChenLiwei
 * 2017-04-05
 * Quack.java
 * Comments: 
 */
package win.chenliwei.designpattern.strategy;

public class Quack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("I am making sound Quack Quack Quack");
	}

}
