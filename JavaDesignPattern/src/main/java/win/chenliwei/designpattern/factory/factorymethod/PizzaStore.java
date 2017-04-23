/**
 * @author: Chenliwei
 * 2017-04-13
 * PizzaStore.java
 * Comments: This pizza store is different from the one in simple factory
 * 1. abstract the class and abstract the createPizza method
 * 2. final the orderPizza method to make sure no changes to it
 */
package win.chenliwei.designpattern.factory.factorymethod;

import win.chenliwei.designpattern.factory.simplefactory.Pizza;

public abstract class PizzaStore {
	public final Pizza orderPizza(String type){
		Pizza pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

	protected abstract Pizza createPizza(String type);
}
