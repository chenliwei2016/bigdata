package win.chenliwei.designpattern.factory.factorymethod;

import win.chenliwei.designpattern.factory.simplefactory.Pizza;

public class NYStyleCheesePizza extends Pizza{

	public NYStyleCheesePizza() {
		name = "NY Style Sauce and Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
 
		toppings.add("Grated Reggiano Cheese");
	}

}
