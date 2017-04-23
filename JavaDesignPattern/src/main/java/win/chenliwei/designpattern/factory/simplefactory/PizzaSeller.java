/**
 * @author: Chenliwei
 * 2017-04-13
 * PizzaSeller.java
 * Comments: It is to demonstrate what is simple factory, actually it is not a design pattern
 */
package win.chenliwei.designpattern.factory.simplefactory;

public class PizzaSeller {

	public static void main(String[] args) {
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore pizzaStore = new PizzaStore(factory);
		System.out.println("We ordered a pizza which is your forvarite");
		Pizza pepperOniPizza = pizzaStore.orderPizza("PepperOni");
		System.out.println(pepperOniPizza);
	}

}
