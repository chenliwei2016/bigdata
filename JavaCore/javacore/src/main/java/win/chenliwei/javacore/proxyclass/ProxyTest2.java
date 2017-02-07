package win.chenliwei.javacore.proxyclass;
/*
 * this demo demonstrate how to use proxy class to enhance the function of a existing
 * class without extend, it is also called AOP, and used in java programming such as
 * transactions, you can add extra logic before or after a method, just like
 * trigger in oracle. now I want to design a Person class with eat function,
 * and then enhance the eat function using proxy class
 * the steps to realize a proxy class are:
 * 1. import java.lang.reflect.*
 * 2. identify which interface/class needs to be enhanced
 * 3. identify which function or functions needs to be enhanced
 * 4. construct a object of target class which implemented an interface
 * 5. construct a handler to handle the target objects, enhance the functions here
 * 6. construct a proxy object to replace the target object
 * 7. then, any calls to proxy is to call the target object
 */

import java.lang.reflect.*;

interface PersonActions{
	void eat(String what);
	void drink(String what);
	void fuck(String whom);
	String getName();
}

class Person implements PersonActions {
	private String name;
	public Person(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void drink(String what){
		System.out.println(this.name + " is drinking " + what);
	}
	public void eat(String what){
		System.out.println(this.name + " is eating " + what);
	}
	public void fuck(String whom){
		System.out.println(this.name + " is fucking " + whom);
	}
}

class TraceHandlerPerson implements InvocationHandler{
	private Object target;

	public TraceHandlerPerson(Object target) {
		this.target = target;
	}
	
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable{
		
		//place the logic before original method here
		if(m.getName() == "fuck"){
			String name = ((PersonActions)proxy).getName();
			System.out.println( name + " is now fucking " + args[0] + ", please keep secret");
		}
		
		if(m.getName() == "eat"){
			//before eating, let's have a cup of wine
			((PersonActions)proxy).drink("MaoTai");
		}
		
		//original method executed here
		Object obj = m.invoke(target, args);
		
		//place the logic after original method here
		if(m.getName() == "fuck"){
			System.out.println( "They finished fucking already, please keep secret.promise me");
		}
		
		if(m.getName() == "eat"){
			//after eating, let's have a smoke
			System.out.println(((PersonActions)proxy).getName() + " finished eating, and have a smoke like a god");
		}
		//return
		return obj;
	}
	
}

public class ProxyTest2 {

	public static void main(String[] args) {
		Person zhangPeng = new Person("ZhangPeng");
		InvocationHandler tracePerson = new TraceHandlerPerson(zhangPeng);
		PersonActions proxy = (PersonActions)Proxy.newProxyInstance(PersonActions.class.getClassLoader(), new Class[]{PersonActions.class}, tracePerson);
		proxy.fuck("CangJingKong");
		proxy.eat("Pizza");
	}

}
