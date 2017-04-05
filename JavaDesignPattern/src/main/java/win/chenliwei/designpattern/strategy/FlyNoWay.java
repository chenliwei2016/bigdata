/**
 * @author: ChenLiwei
 * 2017-04-05
 * FlyNoWay.java
 * Comments: 
 */
package win.chenliwei.designpattern.strategy;

public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I cannot fly!");
	}

}
