/**
 * @author: ChenLiwei
 * 2017-02-20
 * TimerTest.java
 * Comments: An callback example from the book
 * Timer is a tool which performs an action in a interval parameter for example 10 seconds
 * but Timer does not know what actions are performed before you tell it.
 * So Timer constructor has the second parameter, an interface ActionListener
 * Any class implemented ActionListener interface can be passed to Timer.
 * As for what to be done, you can customize them in the override function actionPeformed in your class
 */
package win.chenliwei.javacore.interfaces;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest {

	public static void main(String[] args) {
		ActionListener listener = new TimerPrinter();
		Timer t = new Timer(10000,listener);
		t.start();
		JOptionPane.showMessageDialog(null, "Quit program");
		System.exit(0);
	}

}

class TimerPrinter implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Date now = new Date();
		System.out.println("At the tone, the time is " + now);
		Toolkit.getDefaultToolkit().beep();
	}
	
}