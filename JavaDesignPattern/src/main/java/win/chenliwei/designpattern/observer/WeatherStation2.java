/**
 * @author: ChenLiwei
 * 2017-04-07
 * WeatherStation2.java
 * Comments: This demo rewrite the WeatherStation application by using java embedded implemented Observer interfaces
 * which are in package java.util, one is an interface Observer and the other is the class Observable for subject.
 * Notice Observable is a class not an interface.
 * In the real world, Observer pattern is widely applied at the ActionListener and callback
 * we create a listener to register it to an event source, when the event happens, the actionPerform
 * is triggered. 
 */
package win.chenliwei.designpattern.observer;

import java.util.Observable;

public class WeatherStation2 {

	public static void main(String[] args) {
		WeatherData2 subject = new WeatherData2();
		new CurrentWeather2(subject);
		new WeatherReport2(subject);
		subject.setWeatherData(32, 51, 1024);
		subject.setPublishOption(Publish.PUSH);
		subject.setWeatherData(37, 33, 2044);		
	}

}
enum Publish{PUSH,PULL}

class WeatherData2 extends java.util.Observable{
	private float temp;
	private float humidity;
	private float pressure;
	private Publish publishOption;
	public WeatherData2() {
		this.temp = 0;
		this.humidity = 0;
		this.pressure = 0;
		setPublishOption(Publish.PULL);
	}
	
	//the below 3 getters method are for the pull way
	public float getTemp() {
		return temp;
	}
	public float getHumidity() {
		return humidity;
	}
	public float getPressure() {
		return pressure;
	}
	
	public void setWeatherData(float temp, float humidity, float pressure){
		this.temp = temp;
		this.humidity = humidity;
		this.pressure = pressure;
		super.setChanged();
		if(this.publishOption == Publish.PULL){
			super.notifyObservers();
		}else{
			super.notifyObservers(temp + "," + humidity + "," + pressure);
		}
	}
	public Publish getPublishOption() {
		return publishOption;
	}
	public void setPublishOption(Publish publishOption) {
		this.publishOption = publishOption;
	}
}


class CurrentWeather2 implements java.util.Observer{
	private java.util.Observable subject;
	private String name;
	
	public CurrentWeather2(Observable subject) {
		this.subject = subject;
		this.subject.addObserver(this);
		this.name = "Current Weather";
	}

	@Override
	public void update(Observable subject, Object data) {
		float temp = 0,humidity = 0,pressure = 0;
		if(data != null && !data.equals("")){ //if data is not null means it use push way
			//Here, the reason I didn't get the publish way from subject is it needs a communication 
			String weatherData[] = ((String)data).split(",");
			temp = Float.parseFloat(weatherData[0]);
			humidity = Float.parseFloat(weatherData[1]);
			pressure = Float.parseFloat(weatherData[2]);
		} else {
			if(subject instanceof WeatherData2){//else we get data from the subject
				temp = ((WeatherData2) subject).getTemp();
				humidity = ((WeatherData2) subject).getHumidity();
				pressure = ((WeatherData2) subject).getPressure();
			}
		}
		display(temp,humidity,pressure);
	}
	
	public void display(float temp, float humidity, float pressure){
		System.out.println("**********Dashboard " + name + "***********");
		System.out.println("Temperature is : " + temp + " degree");
		System.out.println("Humidity is : " + humidity + "%");
		System.out.println("Air pressure is : " + pressure + " kpa");
		System.out.println("**********************************************");
		
	}
	
}

class WeatherReport2 implements java.util.Observer{
	private String name;
	private java.util.Observable subject;
	public WeatherReport2(Observable subject) {
		this.subject = subject;
		this.subject.addObserver(this);
		name = "Weather Report";
	}
	@Override
	public void update(Observable subject, Object data) {
		String forcast;
		float humidity = 0;
		if(data != null && !data.equals("")){ //if data is not null means it use push way
			String weatherData[] = ((String)data).split(",");
			humidity = Float.parseFloat(weatherData[1]);
		} else {
			if(subject instanceof WeatherData2){//else we get data from the subject
				humidity = ((WeatherData2) subject).getHumidity();
			}
		}
		if(humidity > 50) forcast = "will be highly rainy";
		else forcast = "will not be rainy";
		display(forcast);
	}
	private void display(String forcast) {
		System.out.println("**********Dashboard " + name + "***********");
		System.out.println("In the near future 2 hours, it " + forcast);
		System.out.println("**********************************************");
	}
	
}