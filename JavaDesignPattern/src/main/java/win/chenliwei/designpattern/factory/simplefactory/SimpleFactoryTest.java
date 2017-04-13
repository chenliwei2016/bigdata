/**
 * @author: ChenLiwei
 * 2017-04-10
 * SimpleFactoryTest.java
 * Comments: It is to demonstrate what is Simple Factory, actually it is NOT a design pattern
 * it is just a program habit
 */
package win.chenliwei.designpattern.factory.simplefactory;

public class SimpleFactoryTest {

	public static void main(String[] args) {
		Cigarette zhonghua = CigaretteFactory.createCigaretee("zhonghua");
		Cigarette zhongnanhai = CigaretteFactory.createCigaretee("zhongnanhai");
		//just like this, you just need to ask objects from factory
		System.out.println("I got two packs of cigarette: " + zhonghua.getBrand() + " and " + zhongnanhai.getBrand());
	}

}

class Cigarette{
	private String brand;
	private String type;
	public Cigarette(String brand, String type) {
		this.brand = brand;
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void prepare(){}
	public void bake(){}
	public void produce(){}
	public void pack(){}
}

class CigaretteFactory{
	public static Cigarette createCigaretee(String brand){
		if(brand.equalsIgnoreCase("zhonghua")){
			Cigarette zhonghua = new Cigarette("zhonghua","bake");
			zhonghua.prepare();
			zhonghua.bake();
			zhonghua.produce();
			zhonghua.pack();
			return zhonghua;
		}else if(brand.equalsIgnoreCase("zhongnanhai")){
			Cigarette zhongnanhai = new Cigarette("zhongnanhai","mix");
			zhongnanhai.prepare();
			zhongnanhai.produce();
			zhongnanhai.pack();
			return zhongnanhai;
		}else return null;
	}
}