/**
 * @author: Chenliwei
 * 2017-04-14
 * NYStylePizzaStore.java
 * Comments:As a specific pizza store, it can decide its 
 */
package win.chenliwei.designpattern.factory.factorymethod;

import win.chenliwei.designpattern.factory.simplefactory.Pizza;

public class NYStylePizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {
		return null;
	}

}
