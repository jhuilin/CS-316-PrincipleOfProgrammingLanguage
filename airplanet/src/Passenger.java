import java.util.ArrayList;

public class Passenger {
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private ArrayList<Ticket> tickets = new ArrayList<>();
	
	public Passenger(String fN, String lN, String addr, String ph) {
		firstName = fN;
		lastName = lN;
		address = addr;
		phone = ph;
	}
	
	public void cancel(Ticket t) {
		tickets.remove(t);
	}
	
	public ArrayList<Flight> findFlights(Airline a, String date, double time, String from){
		return a.findFlights(date, time, from);
	}
	
	public Ticket bookFlight(Flight f) {
		Ticket newT = new Ticket(f.airline(), this, f, f.getCost());
		tickets.add(newT);
		return newT;
	}
	
	public Ticket theTicket(int tNum) {
		Ticket theT = null;
		for(Ticket t: tickets)
			if(t.ticketNum() == tNum)
				theT = t;
		return theT;
	}
	
	public void allTickets() {
		for(Ticket t: tickets)
			System.out.printf("%02d %s %s%n", t.ticketNum(), t.toString(), t.myFlight().toString());
	}
	
	//getters
	public String firstName() {
		return firstName;
	}
	
	public String lastName() {
		return lastName;
	}
	
	public String address() {
		return address;
	}
	
	public String phone() {
		return phone;
	}
	
	public boolean hasTicket() {
		return tickets.size() > 0;
	}
	
	public void toS() {
		if(tickets.size() == 0)
			System.out.println("--None--");
		for(Ticket t: tickets) {
			System.out.printf("%s %s", t.toString(), t.myFlight().toString());
		}
	}
}
