package win.chenliwei.javacore.oobasic;

import java.lang.reflect.*;

/**
 * @author ChenLiwei
 * This class demonstrate how to use Factory method or use Factory design pattern
 * Clash of Clans(CoC) is a popular game of smart phone. Barracks is a factory to generate
 * kinds of army such as Barbarians, Archers, Giants, Wizards etc.
 * Now let's produce army with Factory design pattern.
 */
public class Barracks {

	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		Barbarians[] barbarians =  WariorFactory.produce(5,3,Barbarians.class);
		Archers[] archers =  WariorFactory.produce(3,3,Archers.class);
	}

}


class WariorFactory{
	
	@SuppressWarnings("unchecked")
	public static <T extends Warior> T[]  produce(int level, int scale, Class<T> targetClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException{
		
		Warior[] wariorArmy = new Warior[scale];
		for(int i=0; i<scale; i++) {
			wariorArmy[i] = (Warior) targetClass.newInstance();
			wariorArmy[i].setProperties(level);
		}
		
		Object rtArray = Array.newInstance(targetClass, scale);
		System.arraycopy(wariorArmy, 0, rtArray, 0, scale);
		return (T[])rtArray;
	}
}

interface WariorActions{
	void setProperties(int level) throws InterruptedException;
	void born();
	void walk(Object target);
	void hurt(int hurtPoint);
	void rage(int speedInc);
	void bloodAdd(int bloodPoint);
}


class Warior implements WariorActions{
	//best practice: use setter and getter instead of construct function
	// if so, we can just use newInstance() and don't care of parameters
	private String name;
	private  String appearance;
	private  int blood;
	private  int speed;
	private  int trainTime;
	private  String skill;
	private  String target;
	private  int damage;
	
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected String getAppearance() {
		return appearance;
	}
	protected void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	protected int getBlood() {
		return blood;
	}
	protected void setBlood(int blood) {
		this.blood = blood;
	}
	protected int getSpeed() {
		return speed;
	}
	protected void setSpeed(int speed) {
		this.speed = speed;
	}
	protected int getTrainTime() {
		return trainTime;
	}
	protected void setTrainTime(int trainTime) {
		this.trainTime = trainTime;
	}
	protected String getSkill() {
		return skill;
	}
	protected void setSkill(String skill) {
		this.skill = skill;
	}
	protected String getTarget() {
		return target;
	}
	protected void setTarget(String target) {
		this.target = target;
	}
	protected int getDamage() {
		return damage;
	}
	protected void setDamage(int damage) {
		this.damage = damage;
	}


	@Override
	public void walk(Object target) {
		System.out.println(this.getName() + " is moving to " + target.toString());
	}

	@Override
	public void hurt(int hurtPoint) {
		System.out.println(this.getName() + " is bleeding with " + hurtPoint + " decreased");
	}

	@Override
	public void rage(int speedInc) {
		System.out.println(this.getName() + " is raging and get faster and stronger with " + speedInc + " accelerated");
	}

	@Override
	public void bloodAdd(int bloodPoint) {
		System.out.println(this.getName() + " is getting " + bloodPoint + " blood added");
	}
	
	@Override
	public void born() {
		System.out.println(this.getName() + " was born : " + this.getAppearance());
	}
	
	public String toString(){
		return this.name;
	}
	@Override
	public void setProperties(int level) throws InterruptedException {
	}
		
}

interface FighterActions{
	void attack(Object target);
}

class Fighter extends Warior implements FighterActions{

	@Override
	public void attack(Object target) {
		System.out.println(this.getName() + " is attacking " + target.toString());
	}

}

class Barbarians extends Fighter{

	public void setProperties(int level) throws InterruptedException {
		this.setName("Barbarians");
		this.setAppearance("Strong man with yellow hair and naked body, his weapon is a sword");
		this.setBlood(100 * level);
		this.setDamage(200 * level);
		this.setSkill("Sword");
		this.setSpeed(20 * level);
		this.setTarget("Any");
		this.setTrainTime(5 * level);
		Thread.sleep(5*level*100);
		this.born();
	}
}

class Archers extends Fighter{

	public void setProperties(int level) throws InterruptedException{
		this.setName("Archers");
		this.setAppearance("Cool women with purple hair and green skirt, his weapon is a bow and arrows");
		this.setBlood(200 * level);
		this.setDamage(300 * level);
		this.setSkill("Bow and Arrow");
		this.setSpeed(25 * level);
		this.setTarget("Any");
		this.setTrainTime(10 * level);
		Thread.sleep(10*level*100);
		this.born();
	}
}

class Giants extends Fighter{

	public void setProperties(int level) throws InterruptedException {
		this.setName("Giants");
		this.setAppearance("Gints with naked head and yellow eyebrow , his weapon is his own fists");
		this.setBlood(500 * level);
		this.setDamage(500 * level);
		this.setSkill("Fist");
		this.setSpeed(10 * level);
		this.setTarget("Defense");
		this.setTrainTime(20 * level);
		Thread.sleep(20*level*100);
		this.born();
	}
}