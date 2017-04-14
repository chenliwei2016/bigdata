/**
 * @author: Chenliwei
 * 2017-04-13
 * SimplePizzaFactory.java
 * Comments: This is the simple factory class
 */
package win.chenliwei.designpattern.factory.simplefactory;

public class SimplePizzaFactory {
	
	public Pizza createPizza(String type){
		Pizza pizza = null;
		if(type.equalsIgnoreCase("Cheese")){
			pizza = new CheesePizza();
		} else if(type.equalsIgnoreCase("Clam")){
			pizza = new ClamPizza();
		} else if(type.equalsIgnoreCase("PepperOni")){
			pizza = new PepperOniPizza();
		}
		
		return pizza;
	}

}
