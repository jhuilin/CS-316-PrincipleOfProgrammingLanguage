import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main{
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Random r = new Random();
		ArrayList<Airline> airline = new ArrayList<>();
		ArrayList<Airline> airline2 = new ArrayList<>();
		
		for(int d = 0; d < 31; d++) {
			airline.add(new Airline("Air Queens"));
			airline2.add(new Airline("Air Queens"));
		}
		
		for(int i = 6; i < 23; i++) {
			double time = i+r.nextInt(60)/100.0;
			double time2 = i+r.nextInt(60)/100.0;
			for(int d = 0; d < 31; d++) {
				airline.get(d).createFlight("10/"+ (d+1) +"/2017", time, 600, "Kennedy", "Laguardia");
				airline2.get(d).createFlight("10/"+ (d+1) +"/2017", time2, 600, "Laguardia", "Kennedy");
			}
		}

		for(int b = 0; b<10000; b++) {
			Passenger p = new Passenger("a", "a", "a", "a");
			boolean book = true;
			int day = 0;
			while(book) {
				int time = r.nextInt(24) + 1;
				day = r.nextInt(31);
  				ArrayList<Flight> flights = p.findFlights(airline.get(day), "10/"+ (day+1) +"/2017", (double)time, "Kennedy");
				if(airline.get(day).book(p, flights.get(r.nextInt(flights.size()))) == null)
					book = false;
			}
		}
		for(int b = 0; b<10000; b++) {
			Passenger p = new Passenger("a", "a", "a", "a");
			boolean book = true;
			int day = 0;
			while(book) {
				int time = r.nextInt(24) + 1;
				day = r.nextInt(31);
				ArrayList<Flight> flights = p.findFlights(airline2.get(day), "10/"+ (day+1) +"/2017", (double)time, "Laguardia");
				if(airline2.get(day).book(p, flights.get(r.nextInt(flights.size()))) == null)
					book = false;
			}
		}	
		
		String name = "";
		String fL[] = new String[2];
		String addr = "";
		String phone = "";
		String ans = "yes";
		String operation = "";
		
		while(ans.equalsIgnoreCase("yes")) {
			System.out.println("Ready to book your flights. Enter your first and last name please:");
			name = sc.nextLine();
			while(!name.contains(" ")) {
				System.out.println("Ivalid name. Please enter your first and last name with space between:");
				name = sc.nextLine();
			}
			fL = name.split(" ");
			System.out.println("Type your address on one line please:");
			addr = sc.nextLine();
			System.out.println("Type your phone number on one line please:");
			phone = sc.nextLine();
			System.out.println("----Ready to book your flights for Kennedy <--> Laguardia on October 2017----");
			Passenger p = new Passenger(fL[0], fL[1], addr, phone);
			while(ans.equalsIgnoreCase("yes")) {
				System.out.println("Do you want to book or cancel a flight? Answer Yes or No:");
				ans = sc.nextLine();
				while(!ans.equalsIgnoreCase("yes") && !ans.equalsIgnoreCase("no")) {
					System.out.println("Invalid answer. Please enter only Yes or No");
					ans = sc.nextLine();
				}
				if(ans.equalsIgnoreCase("yes")) {
					System.out.println("Enter C to cancel, K for a flight from Kennedy, or L for a flight from Laguardia");
					operation = sc.nextLine().toUpperCase();
				
					String legal_Char = "CKL";
					boolean op = false;
					
					while(!op) {
					if(operation.length() > 1 || legal_Char.indexOf(operation.charAt(0)) == -1)
						System.out.println("Invalid letter. Enter a correct letter:");
					else
						op = true;
					}
					if(operation.equals("C"))
						cancel(p, sc);
					else if(operation.equals("K"))
						book(p, airline, "Kennedy", sc);
					else
						book(p, airline2, "Laguardia", sc);
				}
			}
			System.out.println("Thank you for booking with Air Queens\nHere is a list of your bookings:");
			p.toS();
			sc.close();
		}
	}
	
	static void cancel(Passenger p, Scanner sc) {
		if(p.hasTicket()) {
			int ticketNum = 0;
			
			System.out.println("Here are the tickets you have booked:");
			p.allTickets();
			System.out.println("Type the number of the ticket you wish to cancel:");
			ticketNum = Integer.parseInt(sc.nextLine());
			while(p.theTicket(ticketNum) == null) {
				System.out.println("Invalid ticket number, Please Enter the correct Ticket Number:");
				ticketNum = Integer.parseInt(sc.nextLine());
			}
			Ticket theT = p.theTicket(ticketNum);
			System.out.println("Successfully cancel the ticket.");
			theT.myAirline().issueRefund(theT);
			theT.cancel();			
		}else
			System.out.println("You haven't booked any flight yet.");
	}
	
	static void book(Passenger p, ArrayList<Airline> a, String from, Scanner sc) {
		int day = 1;
		double time = 1;
		int flightNumber = 0;
		
		System.out.println("Enter the day in October that you want to fly:");
		day = Integer.parseInt(sc.nextLine());
		while(day<1 || day>31) {
			System.out.println("Ivalid date. Please enter a valid date for October:");
			day = Integer.parseInt(sc.nextLine());
		}
		System.out.println("Enter an hour you would like to fly (in range 1 - 24): ");
		time = Double.parseDouble(sc.nextLine());
		while(time<1 || time >24) {
			System.out.println("Invalid hour. Please enter an hour in the range of 1 - 24):");
			time = Double.parseDouble(sc.nextLine());
		}
		boolean hasSp = false;
		ArrayList<Flight> flights = null;
		while(!hasSp) {
			flights = p.findFlights(a.get(day-1), "10/"+ (day) +"/2017", time, from);
			for(Flight f: flights) {
				if(f.hasSpace()) {
					System.out.print(f.airline().name() + " ");
					System.out.println(f);
					hasSp = true;
				}
			}
			if(!hasSp) {
				System.out.printf("Sorry, all flights for %s between time %d on 10/%s/2017 is full.%nPlease enter another hour(1 - 24) on 10/%d/2017.%n",a.get(day-1).name(), (int)time, day, day);
				time = Double.parseDouble(sc.nextLine());
			}
		}
		System.out.println("Type the number of the flight you wish to book:");
		flightNumber = Integer.parseInt(sc.nextLine());
		
		boolean correctFnumber = false;
		Flight theFlight = null;
		
		while(!correctFnumber) {
			for(Flight f: flights)
				if(f.flightNum() == flightNumber) {
					correctFnumber = true;
					theFlight = f;
				}
			if(!correctFnumber) {
				System.out.println("Invalid flight number. Please enter a correct flight number: ");
				flightNumber = Integer.parseInt(sc.nextLine());
			}
		}
		if(a.get(day-1).book(p, theFlight) != null)
			System.out.println("Successfully Booked ticket.");
		else
			System.out.println("Failed to Booked ticket.");
	}
}