import java.util.concurrent.Semaphore;

public class Bank {
	private int teller;
	private int meanArrivals;
	private int meanService;
	private int simulationLength;
	private int totalCust;
	private int currentCust;
	private double totalWait;
	private Semaphore sem;
	
	public Bank(int t, int a, int s, int sT) {
		teller = t;
		meanArrivals = a;
		meanService = s;
		simulationLength = sT;
		totalCust = 0;
		currentCust = 0;
		totalWait = 0;
		sem = new Semaphore(t, true);
	}
	
	//start the simulation
	public void open() throws InterruptedException {
		System.out.format("%s: %d%n%s: %d%n%s: %d%n%s: %d%n", "Mean inter_arrivel time", meanArrivals,
															"Mean service time", meanService,
															"Number of tellers", teller,
															"Length of simulation", simulationLength);
		Thread t = new Thread(new newCustomer(this));
		t.start();
		t.join();
		System.out.format("%s = %.2f%n", "Average waiting time", totalWait/totalCust);
		System.out.format("%s %d %s%n","Simulation terminated after", totalCust, "customers served");
	}
	
	
	//remain the same during entire simulation, therefore no need synchronization
	public Semaphore getSem() {
		return sem;
	}
	
	public int getTeller() {
		return teller;
	}
	
	public int getMeanArr() {
		return meanArrivals;
	}
	
	public int getMeanSer() {
		return meanService;
	}
	
	public int getSimulationTime() {
		return simulationLength;
	}
	
	
	//only below variable could possibly be changed during the simulation
	public synchronized int totalCustomer() {
		return totalCust;
	}
	
	public synchronized int currentCustomer() {
		return currentCust;
	}
	
	public synchronized void come() {
		totalCust++;
		currentCust++;
	}
	
	public synchronized void leave() {
		currentCust--;
	}
	
	public synchronized void addWaitTime(double w) {
		totalWait+=w;
	}
	
}
