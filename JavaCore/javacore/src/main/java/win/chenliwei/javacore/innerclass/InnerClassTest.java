package win.chenliwei.javacore.innerclass;

/*
 this demo demonstrate how to use inner anonymous class to extend or implement
 a super class or interface without using key word extend or implement 
 1. war is a object of anonymous class implemented interface WarCar
 2. zhangpeng is a object of anonymous class extended class Men
 */
interface Car{
	void run();
	void stop();
}

interface WarCar extends Car{
	void fire();
}

class Men{
	public void fuck(String who){
		System.out.println("fuck " + who);
	}
}

public class InnerClassTest {
	public static void main(String args[]){
		WarCar warcar = new WarCar(){
			public void fire(){System.out.println("fire from WarCar");}
			public void run(){System.out.println("run from Car");}
			public void stop(){System.out.println("stop from Car");}
			
		};
		warcar.fire();
		Men zhangpeng = new Men(){
			public void fuck(String who){
				super.fuck(who);
				System.out.println("fuck " + who + " again！");
			}
		};
		zhangpeng.fuck("苍井空");
		
	}

}
