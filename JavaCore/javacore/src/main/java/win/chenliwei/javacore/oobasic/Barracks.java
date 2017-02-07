package win.chenliwei.javacore.oobasic;

/**
 * @author chenliwei
 * This class demonstrate how to use Factory method or use Factory design pattern
 * Clash of Clans(CoC) is a popular game of smart phone. Barracks is a factory to generate
 * kinds of army such as Barbarians, Archers, Giants, Wizards etc.
 * Now let's produce army with Factory design pattern.
 */
public class Barracks {

	
	public static void main(String[] args) {

	}

}

class Warior{
	private String name;
	private String appearance;
	private int level;
	private int blood;
	private int speed;
	private int trainTime;
	private String skill;
	private String target;
	private int damage;

	protected Warior(String name, String appearance, int level, int blood, int speed, int trainTime, String skill,
			String target, int damage) {
		super();
		this.name = name;
		this.appearance = appearance;
		this.level = level;
		this.blood = blood;
		this.speed = speed;
		this.trainTime = trainTime;
		this.skill = skill;
		this.target = target;
		this.damage = damage;
	}
	
	public void walk(){
		System.out.println(this.name + " is moving to " + this.target + " in speed " + this.speed);
	}
	
	public void attack(Enermy target){
		System.out.println(this.name + " is attacking to " + target.getName());
		target.hurt(this.damage);
	}
	
	public void hurt(int hurtPoint){
		this.blood -= hurtPoint;
		if(this.blood <= 0 ) System.out.println(this.name + " is dead");
		System.out.println(this.name + " is bleeding, the blood lefts " + this.blood);
	}
	
	public void rage(int speedInc, int damageInc){
		this.speed += speedInc;
		this.damage *= 1 + damageInc / 100;
		System.out.println(this.name + " is raging, so moves faster and stronger");
	}
	
}

class Enermy{
	private String name;
	private int blood;
	public void hurt(int hurtPoint){
		this.blood -= hurtPoint;
		if(this.blood <= 0 ) System.out.println("Enermy is dead");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

class Barbarians extends Warior{

	protected Barbarians(String name, String appearance, int level, int blood, int speed, int trainTime, String skill,
			String target, int damage) {
		super(name, appearance, level, blood, speed, trainTime, skill, target, damage);
		// TODO Auto-generated constructor stub
	}
	
}