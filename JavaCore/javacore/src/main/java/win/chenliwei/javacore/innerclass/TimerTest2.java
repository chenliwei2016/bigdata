/**
 * @author: ChenLiwei
 * 2017-02-20
 * TimerTest2.java
 * Comments: This is to show how to create a local inner class
 * which means the class is defined within a method just like a local variable
 * this way, realized the 2 of 3 targets of inner class, that is
 * totally invisible to outer class and outside of outer class 
 */
package win.chenliwei.javacore.innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest2 {

	public static void main(String[] args) {
		new TalkingClock2().start(2000, true); // you see it is simpler and stronger and flexible
	}

}

class TalkingClock2{
	public void start(int interval,final boolean beep){ //local class can visit final variable within method
		class TimerPrinter implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				Date now = new Date();
				System.out.println("At the tone it is time " + now);
				if(beep) Toolkit.getDefaultToolkit().beep();
			}
		}
		ActionListener listener = new TimerPrinter();
		Timer t = new Timer(interval,listener);
		t.start();
		JOptionPane.showMessageDialog(null, "Quit program");
		System.exit(0);
	}
}
