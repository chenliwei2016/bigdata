package win.chenliwei.designpattern.factory.simplefactory;

import java.util.ArrayList;

abstract public class Pizza {
	protected String name;
	protected String dough;
	protected String sauce;
	protected ArrayList<String> toppings = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	
	public void prepare(){
		System.out.println("Pizza is being prepared");
	}
	
	public void bake(){
		System.out.println("Pizza is being baked");
	}
	
	public void cut(){
		System.out.println("Pizza is being cut into pieces");
	}
	
	public void box(){
		System.out.println("Pizza is being packed to the box");
	}

	@Override
	public String toString() {
		
		StringBuilder display = new StringBuilder();
		display.append(name + "\n");
		display.append(dough + "\n");
		display.append(sauce + "\n");
		toppings.forEach(top -> {display.append(top+"\n");});
		
		return display.toString();
	}
	
	
}
