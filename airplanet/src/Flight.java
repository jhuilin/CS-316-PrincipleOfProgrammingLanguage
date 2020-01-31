

import java.util.ArrayList;

public class Flight {
	private int flightNum = 89;
	private int seats;
	private int filledSeats;
	private Airline airline;
	private String date;
	private String originAirport;
	private String destination;
	private double departureTime;
	private ArrayList<Ticket> tickets = new ArrayList<>();
	private static int counter = 0;
	
	public Flight(Airline al,String d, double dT,int s, String from, String to){
		flightNum += counter;
		airline = al;
		date = d;
		seats = s;
		originAirport = from;
		destination = to;
		departureTime = dT;
		filledSeats = 0;
		counter += 2;
	}
	
	public boolean matches(String d, double t, String from) {
		return 	d.equals(date) && 
				((t+24-departureTime)%24 <= 4 || (departureTime-t >=0 && departureTime-t < 4)) && 
				from.equals(originAirport);
	}
	
	public boolean hasSpace() {
		return filledSeats < seats;
	}
	
	public void addTicket(Ticket t) {
		filledSeats++;
		tickets.add(t);
	}
	
	public void remove(Ticket t) {
		tickets.remove(t);
	}
	
	public double getCost() {
		return airline.cost(this);
	}
	
	//getters
	public int flightNum() {
		return flightNum;
	}
	
	public int seats() {
		return seats;
	}
	
	public int filledSeats() {
		return filledSeats;
	}
	
	public Airline airline() {
		return airline;
	}
	
	//override toString
	public String toString() {
		return String.format("%d %s %.2f from %s to %s ticket cost $%,.2f", flightNum, date, departureTime, originAirport, destination, getCost());
	}
}
