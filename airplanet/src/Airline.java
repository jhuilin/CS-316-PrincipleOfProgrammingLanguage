
import java.util.ArrayList;

public class Airline {
	private String name;
	private ArrayList<Flight> flights = new ArrayList<>();
	
	public Airline(String n) {
		name = n;
	}
	
	public void issueRefund(Ticket t) {
		System.out.printf("Airline %s refunds $%.2f to %s %s.%n" ,t.myAirline().name(), t.price(), t.myPassenger().firstName(), t.myPassenger().lastName());
	}
	
	public ArrayList<Flight> findFlights(String d, double t,String origin){
		ArrayList<Flight> aviarableF = new ArrayList<>();
		for(Flight f: flights) {
			if(f.matches(d, t, origin)) {
				aviarableF.add(f);
			}
		}
		return aviarableF;
	}
	
	public Ticket book(Passenger p, Flight f) {
		if(!f.hasSpace()) 
			return null;
		Ticket newT = p.bookFlight(f);
		f.addTicket(newT);
		return newT;
	}
	
	public double cost(Flight f) {
		return ((double)f.filledSeats()/f.seats() + 1.0) * 100.0;
	}
	
	public void createFlight(String d, double t, int numSeats, String f, String to) {
		flights.add(new Flight(this, d, t, numSeats, f, to));
	}
	
	//getters
	public String name() {
		return name;
	}
	
}