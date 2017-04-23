/**
 * @author: ChenLiwei
 * 2017-04-05
 * DuckFamily.java
 * Comments: It is to demonstrate the first design pattern : strategy pattern
 * Here are some key points:
 * 	1. separate the part unchangeable from the part changeable, only keep unchanged in super class
 * 	2. Use interface as an unchanged part in the class and then implements interface to construct a group of algorithm for changeable part
 * 	3. Interface first but not realize, so it is very important to use Interface-Oriented mind
 * 	4. Try to use as much upcasting as you can
 * 	5. Try to use as much implements as you can instead of extends
 */
package win.chenliwei.designpattern.strategy;

public class DuckFamily {

	public static void main(String[] args) {
		Duck duck = new MallardDuck();
		duck.display();
		duck.performFly();
		duck.performQuack();
		
		duck = new RubberDuck();
		duck.display();
		duck.performFly();
		duck.performQuack();
	}

}
