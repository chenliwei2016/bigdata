/**
 * Author: Chenliwei
 * DI is for Dependency Injection which is the core function of Spring framework
 */
package win.chenliwei.spring.simple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import win.chenliwei.spring.simple.bean.Employee;
import win.chenliwei.spring.simple.bean.HelloWorld;
import win.chenliwei.spring.simple.bean.HelloWorld2;

public class DITest {


	public static void main(String[] args) {
		//Create IOC object, IOC is for Inversion of Control
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloWorld hello = (HelloWorld) ctx.getBean("HelloWorld");
		System.out.println(hello.getMessage());
		
		HelloWorld2 hello2 = (HelloWorld2) ctx.getBean("HelloWorld2");
		System.out.println(hello2.getMsg());
		
		HelloWorld hello3 = (HelloWorld) ctx.getBean("HelloWorld3");
		System.out.println(hello3.getMessage());
		
		Employee moore = (Employee) ctx.getBean("Moore");
		System.out.println(moore.getHomeAddress());

	}

}
