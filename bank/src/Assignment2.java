public class Assignment2 {
	public static void main(String args[]) throws InterruptedException {
		
		Bank bank = new Bank(Integer.parseInt(args[0]),		//# of teller
							Integer.parseInt(args[1]),		//mean inter-arrival time
							Integer.parseInt(args[2]),		//mean service time
							Integer.parseInt(args[3]));		//length of the simulation
		bank.open();
	}
}
