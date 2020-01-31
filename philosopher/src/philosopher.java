
public class philosopher implements Runnable 
{
	int id;
	monitor m;
	Thread t;
	philosopher(int id, monitor m)
	{
		this.id = id;
		this.m=m;
		t = new Thread(this);
		t.start();
	}
	public void run()
	{
		while(true)
		{
			this.m.takeChopsticks(id);
			eat();
			this.m.returnChopsticks(id);
			think();
		}
	}
	void eat()
	{
		System.out.format("Philosopher %d is eating\n", id+1);
		SleepUtilities.nap();
	}
	void think()
	{
		System.out.format("Philosopher %d is thinking\n", id+1);
		SleepUtilities.nap();
	}
}
