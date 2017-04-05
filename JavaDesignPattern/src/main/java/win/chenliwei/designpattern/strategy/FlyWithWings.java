/**
 * @author: ChenLiwei
 * 2017-04-05
 * FlyWithWings.java
 * Comments: 
 */
package win.chenliwei.designpattern.strategy;

public class FlyWithWings implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I am flying with my wings");
	}

}
