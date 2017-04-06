/**
 * @author: ChenLiwei
 * 2017-04-06
 * WeatherStation.java
 * Comments: It is to demonstrate how to use Observer design patter
 * There are two roles: subject and observer.
 * the subject is the role provides content and information observers care
 * the relationship between subject and observer is 1:n
 * every subject implements interface Subject to realize 3 methods which can add,remove and notify observers
 * every observer implements interface Observer to realize 2 methods
 */
package win.chenliwei.designpattern.observer;

import java.util.ArrayList;

public class WeatherStation {

	public static void main(String[] args) {
		WeatherData subject = new WeatherData();
		Observer current = new CurrentWeather("current weather");
		Observer future = new WeatherReport("weather report");
		subject.registerObserver(current);
		subject.registerObserver(future);
		subject.setWeatherData(23, 80, 1200);
		subject.removeObserver(current);
		subject.setWeatherData(32, 20, 1800);
		
	}

}

interface Subject{
	void registerObserver(Observer ob);
	void removeObserver(Observer ob);
	void notifyObservers();
}

interface Observer{
	void update(float temp, float humidity, float pressure);
	void display();
}

class WeatherData implements Subject{
	private float temp;
	private float humidity;
	private float pressure;
	private ArrayList<Observer> observers;
	
	public WeatherData() {
		this.temp = 0;
		this.humidity = 0;
		this.pressure = 0;
		this.observers = new ArrayList<>();
	}

	public void setWeatherData(float temp, float humidity, float pressure){
		this.temp = temp;
		this.humidity = humidity;
		this.pressure = pressure;
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer ob) {
		observers.add(ob);
	}

	@Override
	public void removeObserver(Observer ob) {
		observers.remove(ob);
	}

	@Override
	public void notifyObservers() {
		observers.forEach(ob -> {ob.update(temp, humidity, pressure);});
	}
}

class CurrentWeather implements Observer{
	private float temp;
	private float humidity;
	private float pressure;
	private String name;
	public CurrentWeather(String name) {
		this.name = name;
		this.temp = 0;
		this.humidity = 0;
		this.pressure = 0;
	}
	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temp = temp;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}
	@Override
	public void display() {
		System.out.println("**********Dashboard " + name + "***********");
		System.out.println("Temperature is : " + temp + " degree");
		System.out.println("Humidity is : " + humidity + "%");
		System.out.println("Air pressure is : " + pressure + " kpa");
		System.out.println("**********************************************");
	}	
}

class WeatherReport implements Observer{
	private String name;
	private String forcast;
	public WeatherReport(String name) {
		this.name = name;
	}
	@Override
	public void update(float temp, float humidity, float pressure) {
		if(humidity > 50) forcast = "will be highly rainy";
		else forcast = "will not be rainy";
		display();
	}
	@Override
	public void display() {
		System.out.println("**********Dashboard " + name + "***********");
		System.out.println("In the near future 2 hours, it " + forcast);
		System.out.println("**********************************************");
	}
	
	
}