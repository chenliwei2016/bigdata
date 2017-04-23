/**
 * @author: ChenLiwei
 * 2017-04-05
 * RubberDuck.java
 * Comments: 
 */
package win.chenliwei.designpattern.strategy;

public class RubberDuck extends Duck {

	public RubberDuck() {
		setFlyBehavior(new FlyNoWay());
		setQuackBehavior(new Squeak());
	}

	@Override
	public void display() {
		System.out.println("I am not a real duck but a rubber one");
	}
	
	
}
