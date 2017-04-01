/**
 * @author: ChenLiwei
 * 2017-02-17
 * ButtonFrame.java
 * Comments: It's to demonstrate the mechanism of Annotation
 */
package win.chenliwei.javacore.annotation;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	private JPanel panel;
	private JButton yellowButton, blueButton, greenButton;
	
	public static void main(String args[]){
        JFrame frame = new ButtonFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	public ButtonFrame() {
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel();
		this.add(panel);
		
		yellowButton = new JButton("yellow");
		blueButton = new JButton("blue");
		greenButton = new JButton("green");
		
		panel.add(blueButton);
		panel.add(greenButton);
		panel.add(yellowButton);
		ActionListenerInstaller.processAnnotations(this);
		
	}
	
	@ActionListenerFor(source = "yellowButton") public void yellowBackground(){
		panel.setBackground(Color.YELLOW);
	}
	
	@ActionListenerFor(source = "blueButton") public void blueBackground(){
		panel.setBackground(Color.BLUE);
	}
	
	@ActionListenerFor(source = "greenButton") public void greenBackground(){
		panel.setBackground(Color.GREEN);
	}

}

@Target(ElementType.METHOD) @Retention(RetentionPolicy.RUNTIME) @interface ActionListenerFor{
	String source();
}


class ActionListenerInstaller{
	
	public static void processAnnotations(Object obj){
		try{
			Class<?> cl = obj.getClass();
			for(Method m : cl.getDeclaredMethods()){
				ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
				if(a != null){
					Field f = cl.getDeclaredField(a.source());
					f.setAccessible(true);
					addListener(f.get(obj), obj, m);
				}
			}
		}catch(ReflectiveOperationException e){
			e.printStackTrace();
		}
	}
	
	public static void addListener(Object source, final Object param, final Method m) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		InvocationHandler handler = new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				return m.invoke(param);
			}
			
		};
		
		Object listener = Proxy.newProxyInstance(null, new Class[]{java.awt.event.ActionListener.class}, handler);
		Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
		adder.invoke(source, listener);
		
	}
}
