package win.chenliwei.designpattern.factory.simplefactory;

public class PepperOniPizza extends Pizza {

	public PepperOniPizza() {
		name = "Pepperoni Pizza";
		dough = "Crust";
		sauce = "Marinara sauce";
		toppings.add("Sliced Pepperoni");
		toppings.add("Sliced Onion");
		toppings.add("Grated parmesan cheese");
	}

}
