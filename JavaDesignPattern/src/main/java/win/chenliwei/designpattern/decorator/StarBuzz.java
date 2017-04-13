/**
 * @author: ChenLiwei
 * 2017-04-07
 * StarBuzz.java
 * Comments: It is to demonstrate the decorator design pattern
 * which is a way to decorate the super class one by one to enrich the functions
 * to realize the open-close principle.
 * In the real world, it is used in IO classes to decorate the input/output stream gradually
 * gain more features.
 * This demo solves the problem of a coffee shop named StarBuzz which sell kinds of coffee
 */
package win.chenliwei.designpattern.decorator;

public class StarBuzz {

	public static void main(String[] args) {
		Beverage coffee = new Espresso(); //a cup of coffee
		coffee = new Milk(coffee); // add some milk
		coffee = new Milk(coffee); // add more milk
		coffee = new Mocha(coffee); // add some Mocha
		//Now start to pay
		System.out.println(coffee.getDescription());
		System.out.println("Total payment: " + coffee.cost() + " dollars"); //expected 27
	}

}

abstract class Beverage{
	String description = "unknow beverage";
	
	public String getDescription() {
		return description;
	}

	public abstract double cost();
}

abstract class CondimentDecorator extends Beverage{
	public abstract String getDescription();
}

class Espresso extends Beverage{

	public Espresso() {
		this.description = "Espresso";
	}

	@Override
	public double cost() {
		return 20;
	}
	
}

class HouseBlend extends Beverage{

	public HouseBlend() {
		this.description = "HouseBlend";
	}

	@Override
	public double cost() {
		return 10;
	}
	
}

class Milk extends CondimentDecorator{
	private Beverage beverage;

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + " plus a Milk";
	}

	@Override
	public double cost() {
		return 1 + beverage.cost();
	}
	
}

class Mocha extends CondimentDecorator{
	private Beverage beverage;

	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + " plus a Mocha";
	}

	@Override
	public double cost() {
		return 5 + beverage.cost();
	}
	
}

