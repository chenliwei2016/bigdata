/**
 * @author: ChenLiwei
 * 2017-02-20
 * TimerTest3.java
 * Comments: Let's make TimerTest2 more simple and flexible using anonymous inner class
 * you must shout wow
 */
package win.chenliwei.javacore.innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest3 {

	public static void main(String[] args) {
		TalkingClock3.start(2000, true);
		JOptionPane.showMessageDialog(null, "Quit program");
		System.exit(0);	
	}

}

class TalkingClock3{
	public static void start(int interval, final boolean beep){
		new Timer(interval, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("At the tone it is time :" + new Date());
				if(beep) Toolkit.getDefaultToolkit().beep();
			}
			
		}).start();	
	}
}