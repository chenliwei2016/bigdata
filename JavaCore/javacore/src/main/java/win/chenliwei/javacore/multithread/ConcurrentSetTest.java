/**
 * @author: ChenLiwei
 * 2017-03-05
 * ConcurrentSetTest.java
 * Comments: It is to show the set classes from concurrent packages which provides the thread security
 */
package win.chenliwei.javacore.multithread;

import java.util.HashSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSetTest {

	public static void main(String[] args) {
		ConcurrentSkipListSet<Person> team = new ConcurrentSkipListSet<>();
		team.add(new Person("chenliwei","male"));
		team.add(new Person("zhangpeng","female"));
		team.add(new Person("zhangwei","female"));
		team.add(new Person("yaoxiang","male"));
		HashSet<Person> team1= new HashSet<>();
		team1.addAll(team);
		for(int i=1;i<100;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					team1.add(new Person("chenliwei","male"));
					team1.add(new Person("zhangpeng","female"));
				}}).start();
		}
		
		team1.forEach(action->{System.out.println(action.getName() + " : " + action.getSex());});
		System.out.println(team1.size());
		//you will see the set should have no duplicated data, but it is not true if multithreaded

		for(int i=1;i<100;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					team.add(new Person("chenliwei","male"));
					team.add(new Person("zhangpeng","female"));
				}}).start();
		}
		team.forEach(action->{System.out.println(action.getName() + " : " + action.getSex());});
		System.out.println(team.size());
		//you also still see the set provided by concurrent package is secure
	}
	
	private static class Person implements Comparable<Person>{
		private String name;
		private String sex;
		public Person(String name, String sex) {
			this.name = name;
			this.sex = sex;
		}
		public String getName() {
			return name;
		}
		public String getSex() {
			return sex;
		}
		@Override
		public int compareTo(Person o) {
			return this.name.compareTo(o.name);
				
		}
	}

}
