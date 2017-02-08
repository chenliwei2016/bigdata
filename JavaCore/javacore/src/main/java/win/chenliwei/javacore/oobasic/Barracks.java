package win.chenliwei.javacore.oobasic;

/**
 * @author ChenLiwei
 * This class demonstrate how to use Factory method or use Factory design pattern
 * Clash of Clans(CoC) is a popular game of smart phone. Barracks is a factory to generate
 * kinds of army such as Barbarians, Archers, Giants, Wizards etc.
 * Now let's produce army with Factory design pattern.
 */
public class Barracks {

	
	public static void main(String[] args) {
		Fighter aBarbarians = WariorFactory.getBarbarians(5);
		aBarbarians.born();
		Fighter aArchers = WariorFactory.getArchers(3);
		aArchers.born();
	}

}


class WariorFactory{
	public static Fighter getBarbarians(int level){
		return new Barbarians(level);
	}
	public static Archers getArchers(int level){
		return new Archers(level);
	}
	public static Giants getGiants(int level){
		return new Giants(level);
	}
}

interface WariorActions{
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
	private  int level;
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
	protected int getLevel() {
		return level;
	}
	protected void setLevel(int level) {
		this.level = level;
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

	protected Barbarians(int level) {
		super();
		this.setName("Barbarians");
		this.setAppearance("Strong man with yellow hair and naked body, his weapon is a sword");
		this.setBlood(100 * level);
		this.setLevel(level);
		this.setDamage(200 * level);
		this.setSkill("Sword");
		this.setSpeed(20 * level);
		this.setTarget("Any");
		this.setTrainTime(5 * level);
	}
}

class Archers extends Fighter{
	protected Archers(int level) {
		super();
		this.setName("Archers");
		this.setAppearance("Cool women with purple hair and green address, his weapon is a bow and arrows");
		this.setBlood(200 * level);
		this.setLevel(level);
		this.setDamage(300 * level);
		this.setSkill("Bow and Arrow");
		this.setSpeed(25 * level);
		this.setTarget("Any");
		this.setTrainTime(10 * level);
	}
}

class Giants extends Fighter{
	protected Giants(int level) {
		super();
		this.setName("Giants");
		this.setAppearance("Gints with naked head and yellow eyebrow , his weapon is his own fists");
		this.setBlood(500 * level);
		this.setLevel(level);
		this.setDamage(500 * level);
		this.setSkill("Fist");
		this.setSpeed(10 * level);
		this.setTarget("Defense");
		this.setTrainTime(20 * level);
	}
}