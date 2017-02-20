/**
 * @author: ChenLiwei
 * 2017-02-20
 * TimerTest.java
 * Comments: Notice this is another program different with the same class in package interfaces
 * this one is for to show the 1 of 3 targets of inner class, that is
 * to visit the variable of outer class
 * 
 */
package win.chenliwei.javacore.innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest {

	public static void main(String[] args) {
		TalkingClock tClock = new TalkingClock(1000,true);
		tClock.start();
		//Now, we show how to visit the inner class from out of its outer class
		ActionListener listener = new TalkingClock(1000,true).new TimerPrinter();
		//think about the above, how to improve it? make the inner class static
		Timer t = new Timer(1000,listener);
		t.start();
		
		JOptionPane.showMessageDialog(null, "Quit program");
		System.exit(0);
	}

}

class TalkingClock{
	private int interval;
	private boolean beep; // you set it talk or mute
	public TalkingClock(int interval, boolean beep) {
		this.interval = interval;
		this.beep = beep;
	}
	
	public void start(){
		TimerPrinter listener = new TimerPrinter();
		Timer t = new Timer(interval,listener);
		t.start();
	}
	
	//we define a inner class here, it is just like a member variable 
	public class TimerPrinter implements ActionListener{ //we could replace public with private, this way, it is totally invisible to outer world

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Date now = new Date();
			System.out.println("At the tone, the time is " + now);
			if(beep) Toolkit.getDefaultToolkit().beep(); // if(beep), here, we visited beep variable from outer class
			//the formal grammar to call outer beep is: OuterClass.this.VariableName
			if(TalkingClock.this.beep){}
		}
		
	}
	
}