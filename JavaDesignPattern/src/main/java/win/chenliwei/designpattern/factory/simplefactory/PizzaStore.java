/**
 * @author: Chenliwei
 * 2017-04-13
 * PizzaStore.java
 * Comments: Pizza store sells pizzas created by simple factory
 */
package win.chenliwei.designpattern.factory.simplefactory;

public class PizzaStore {
	private SimplePizzaFactory factory;
	
	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory;
	}

	public Pizza orderPizza(String type){
		Pizza pizza = factory.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

}
