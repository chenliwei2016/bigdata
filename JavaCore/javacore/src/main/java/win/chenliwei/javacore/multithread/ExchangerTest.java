/**
 * @author: ChenLiwei
 * 2017-03-09
 * ExchangerTest.java
 * Comments: An Exchanger is used when two threads are working on two instances of the same
 * data buffer. Typically, one thread fills the buffer, and the other consumes its
 * contents. When both are done, they exchange their buffers.
 * In the ancient times, people use a device named sand filter as a clock.
 * The mechanism of it is very likely to the exchanger. when the bottom part of sand filter is full.
 * then exchange the top and the bottom so that the time continue to fly.
 * Let's illustrate the sand filter using Exchanger
 */
package win.chenliwei.javacore.multithread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Exchanger;

public class ExchangerTest {

	public static void main(String[] args) throws InterruptedException {
		Exchanger<Queue<Sand>> exchanger = new Exchanger<Queue<Sand>>();
		Queue<Sand> topGlass = new ArrayDeque<Sand>();
		Queue<Sand> bottomGlass = new ArrayDeque<Sand>();
		for(int i=0;i<60;i++) topGlass.add(new Sand("Sand" + i));
		new Thread(new TopThread(exchanger, topGlass,3)).start();
		new Thread(new BottomThread(exchanger, bottomGlass,3)).start();
	}
}


class TopThread implements Runnable{
	private Exchanger<Queue<Sand>> exchanger;
	private Queue<Sand> topGlass;
	private int hours;
	public TopThread(Exchanger<Queue<Sand>> exchanger, Queue<Sand> topGlass,int hours) {
		this.exchanger = exchanger;
		this.topGlass = topGlass;
		this.hours = hours;
	}
	@Override
	public void run() {
		Sand s;
		for(int i = 0; i < hours; i++){
			System.out.println("topGlass.size: " + topGlass.size());
			while(!topGlass.isEmpty()){
				s = topGlass.poll();
				System.out.println(s.getDesc());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				topGlass = exchanger.exchange(topGlass); // provide empty Glass to exchanger
				System.out.println("top send out topGlass with empty");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class BottomThread implements Runnable{
	private Exchanger<Queue<Sand>> exchanger;
	private Queue<Sand> bottomGlass;
	private int hours;
	public BottomThread(Exchanger<Queue<Sand>> exchanger, Queue<Sand> bottomGlass,int hours) {
		this.exchanger = exchanger;
		this.bottomGlass = bottomGlass;
		this.hours = hours;
	}
	@Override
	public void run() {
		for(int j=0; j<hours; j++){
			System.out.println("bottomGlass.size: " +bottomGlass.size());
			for(int i=0;i<60;i++) {
				bottomGlass.add(new Sand("Sand" + i));
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				bottomGlass = exchanger.exchange(bottomGlass); // provide full Glass to exchanger
				System.out.println("bottom send out bottomGlass with full");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Sand{
	private String desc;

	public Sand(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
}