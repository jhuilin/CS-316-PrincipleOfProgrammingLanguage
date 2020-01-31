
public class Ticket {
	private int ticketNumber = 0;
	private Airline myAirline;
	private Passenger myPassenger;
	private Flight myFlight;
	private double price;
	private static int counter = 0;
	
	public Ticket(Airline a, Passenger p, Flight f, double price) {
		ticketNumber += counter++;
		myAirline = a;
		myPassenger = p;
		myFlight = f;
		this.price = price;
	}
	
	public void cancel() {
		myFlight.remove(this);
		myPassenger.cancel(this);
	}
	
	//getters	
	public int ticketNum() {
		return ticketNumber;
	}
	
	public Airline myAirline() {
		return myAirline;
	}
	
	public Passenger myPassenger() {
		return myPassenger;
	}
	
	public Flight myFlight() {
		return myFlight;
	}
	
	public double price() {
		return price;
	}
	
	public String toString() {
		return myPassenger.firstName() + " " + myPassenger.lastName() + " booked on " + myAirline.name();
	}
}
