import java.util.concurrent.Semaphore;

public class Customer implements Runnable{
	private Bank bank;
	
	private Semaphore sem;
	private long startTime;
	private double waitT;
	private double begin;
	private Random_Int_Mean meanS;
	private long currentTime;
	
	private int id;
	
	public Customer(Bank b, long sT, long cT) {
		bank = b;
		this.sem = bank.getSem();
		startTime = sT;
		currentTime = cT;
		meanS = new Random_Int_Mean();
	}
	
	public void run() {
		try {
			bank.come();		//customer came in, update current number of customer in the line
			id = bank.totalCustomer();		//add total customer in this simulation
			currentTime-=startTime;
			begin = currentTime/100;		//arrival time
			System.out.format("%s%10d, %s%10d %s%n", "At Time", currentTime/100, "customer", id, "arrives in line");		//get in line
			sem.acquire();
			
			//one teller free, get serviced
			currentTime = System.currentTimeMillis() - startTime;
			System.out.format("%s%10d, %s%10d %s%n", "At Time", currentTime/100, "customer", id, "start being served");		//curently being serve
			waitT = currentTime/100-begin;		//calculate wait time
			bank.addWaitTime(waitT);
			
			//wait until this customer are served
			int sleepTime = meanS.random_int(bank.getMeanSer()/10)*1000;
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
		currentTime = System.currentTimeMillis() - startTime;
		System.out.format("%s%10d, %s%10d %s%n", "At Time", currentTime/100, "customer", id, "leaves the bank");
		bank.leave();		//customer leave, update current number of customer in the line
		sem.release();
		
	}
}
