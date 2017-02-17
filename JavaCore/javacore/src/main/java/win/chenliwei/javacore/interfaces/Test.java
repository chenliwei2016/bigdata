package win.chenliwei.javacore.interfaces;

public class Test {

	public static void main(String[] args) {
		A a = new A();
		a.execue("hello", new I() {

			@Override
			public void say(String t) {

				System.out.println(t + ", example1");
			}
		});

		a.execue("hello", new I() {

			@Override
			public void say(String t) {

				System.out.println(t + ", example2");
			}
		});

		a.execue("hello", new I() {

			@Override
			public void say(String t) {

				Runnable run = new Runnable() {

					@Override
					public void run() {
						
						try {
							System.out.println("I am sleeping!");
							System.out.println(t + ", example2");
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				};

				new Thread(run).start();

			}
		});
		
		System.out.println("I am the master thread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class A {

	public void execue(String t, I i) {
		i.say(t);
	}
}

interface I {
	public void say(String t);
}
