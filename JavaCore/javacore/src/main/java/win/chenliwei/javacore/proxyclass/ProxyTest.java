package win.chenliwei.javacore.proxyclass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class ProxyTest {

	public static void main(String[] args) {
		Object[] elements = new Object[1000];
		
		//fill elements with proxies for the integers 1 .. 1000
		for (int i = 0; i < elements.length; i++){
			Integer value = i+1; 
			InvocationHandler invocationHandler = new TraceHandler(value);
			Object proxy = Proxy.newProxyInstance(null, new Class[]{ Comparable.class }, invocationHandler);
			elements[i] = proxy;
			System.out.println(proxy.getClass().getName());
		}
		
		//Construct a new random Integer
		Integer key = new Random().nextInt(elements.length) + 1;
		
		//Search for the key
		int result = Arrays.binarySearch(elements, key);
		
		//Print if matched
		if(result >= 0) System.out.println(elements[result]);
	}

}

class TraceHandler implements InvocationHandler{
	private Object target;

	public TraceHandler(Object target) {
		this.target = target;
	}
	
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable{
		//print implicit argument
		System.out.print(target);
		//print method name
		System.out.print("." + m.getName() + "(");
		//print explicit arguments
		if(null != args){
			for(int i = 0; i < args.length; i++){
				System.out.print(args[i]);
				if(i < args.length-1){
					System.out.print(",");
				}
			}
			System.out.print(")\n");
		}
		
		//invoke actual method
		return m.invoke(target, args);
		
	}
	
}
