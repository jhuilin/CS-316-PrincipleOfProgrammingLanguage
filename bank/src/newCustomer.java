import java.util.ArrayList;

public class newCustomer implements Runnable{
	
	private Random_Int_Mean meanA= new Random_Int_Mean();
	private Bank bank;
	private ArrayList<Thread> line;		//record all thread
	private boolean anyAlive = true;
	
	public newCustomer(Bank b) {
		meanA = new Random_Int_Mean();
		bank = b;
		line = new ArrayList<>();
	}

	public void run() {
		long startTime = System.currentTimeMillis();
		do {
			if((System.currentTimeMillis()-startTime)/100 < bank.getSimulationTime()) {		//if simulation time not reached allow customer to get in line
				try {
					//get next customer arrival time
					int sleepTime = meanA.random_int(bank.getMeanArr()/10)*1000;
					Thread.sleep(sleepTime);
					
					//create customer when arrival time reached
					Thread t = new Thread(new Customer(bank, startTime, System.currentTimeMillis()));
					line.add(t);			//record every customer thread
					t.start();
					
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
			
			//if customer thread died make sure it is properly disposed
			try {
				for(Thread i : line) {
					anyAlive = i.isAlive();
					if(!i.isAlive())
						i.join();
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}while(bank.currentCustomer() > 0 || System.currentTimeMillis()-startTime <= bank.getSimulationTime()*100 || anyAlive);
	}
	
}
