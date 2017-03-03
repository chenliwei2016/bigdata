/**
 * @author: ChenLiwei
 * 2017-03-02
 * TicketSeller.java
 * Comments: It uses Multithreaded to simulate the scene of ticket selling in train station
 */
package win.chenliwei.javacore.multithread;

import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;

public class TicketSeller {

	public static void main(String[] args) {

	}

}

class Ticket{
	private Passager passager;
	private Seat seat;
	private TrainShift trainShift;
	
	public Ticket(Passager passager, Seat seat, TrainShift trainShift) {
		this.passager = passager;
		this.seat = seat;
		this.trainShift = trainShift;
	}

	public void print(){
		System.out.println("-------------Train Ticket-----------");
		System.out.println(trainShift.toString() + seat.toString());
		System.out.println(passager.toString());
	}
	
}

class Passager{
	private String name;
	private String id;
	public Passager(String name, String id) {
		this.name = name;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		return name + id.substring(0, 5) + "******" + id.substring(10);
	}
}

class TrainShift{
	private String id;
	private Station departCity;
	private Station destCity;
	private SortedMap<Integer,Station> passStations;

	public String getId() {
		return id;
	}
	public TrainShift(String id, Station departCity, Station destCity, SortedMap<Integer, Station> passStations) {
		this.id = id;
		this.departCity = departCity;
		this.destCity = destCity;
		this.passStations = passStations;
	}
	public Station getDepartCity() {
		return departCity;
	}
	public void setDepartCity(Station departCity) {
		this.departCity = departCity;
	}
	public Station getDestCity() {
		return destCity;
	}
	public void setDestCity(Station destCity) {
		this.destCity = destCity;
	}
	public SortedMap<Integer, Station> getPassStations() {
		return passStations;
	}
	public void addPassStations(int key,Station station) {
		this.passStations.put(key, station);
	}
	@Override
	public String toString() {
		Calendar c = Calendar.getInstance();
		c.setTime(departCity.getArriveTime());
		c.add(Calendar.MINUTE, departCity.getStopMinutes());
		return id + "   " + departCity.getName() + " -> " + destCity.getName() + " starts at "
				+ c.getTime() ;
	}
	
}

class Station{
	private String name;
	private Date arriveTime;
	private int stopMinutes;
	public Station(String name, Date arriveTime, int stopMinutes) {
		this.name = name;
		this.arriveTime = arriveTime;
		this.stopMinutes = stopMinutes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public int getStopMinutes() {
		return stopMinutes;
	}
	public void setStopMinutes(int stopMinutes) {
		this.stopMinutes = stopMinutes;
	}
}

enum SeatNumber{A,B,C,D,F}

class Seat{
	private int carriage;
	private int array;
	private char seat;
	public Seat(int carriage, int array, char seat) {
		this.carriage = carriage;
		this.array = array;
		this.seat = seat;
	}
	
	@Override
	public String toString() {
		return "Seat: carriage=" + carriage + "  array=" + array + "  seat=" + seat;
	}
	
}