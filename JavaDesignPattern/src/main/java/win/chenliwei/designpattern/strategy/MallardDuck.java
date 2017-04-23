/**
 * @author: ChenLiwei
 * 2017-04-05
 * MallardDuck.java
 * Comments: 
 */
package win.chenliwei.designpattern.strategy;

public class MallardDuck extends Duck {
	
	public MallardDuck() {
		setFlyBehavior(new FlyWithWings());
		setQuackBehavior(new Quack());
	}

	@Override
	public void display() {
		System.out.println("I have a beautiful green head");
	}

}
