import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class monitor implements diningServer 
{
	enum State{ thinkng, hungry, eating};
	private State[] states;
	final Lock l;
	final Condition [] self;
	private int numPhilosophers;
	
	monitor(int numPhil)
	{
		numPhilosophers = numPhil;
		l = new ReentrantLock();
		states = new State[numPhilosophers];
		self = new Condition[numPhilosophers];
		for(int i=0;i<numPhilosophers;++i)
		{
			states[i]=State.thinkng;
			self[i]=l.newCondition();
		}
	}
	
	public void takeChopsticks(int i)
	{
		l.lock();
		try
		{
			states[i] = State.hungry;
			if(test4Take(i))
			{
				System.out.format("Philosopher %d acquired its left and right chopstick\n", i+1);
				states[i] = State.eating;
			}
			else
			{
				try 
				{
					self[i].await();
					System.out.format("Philosopher %d acquired its left and right chopstick\n", i+1);
					states[i] = State.eating;
				}catch(InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}finally {l.unlock();}
	}
	
	public void returnChopsticks(int i)
	{
		l.lock();
		try
		{
			states[i] = State.thinkng;
			System.out.format("Philosopher %d released its left and right chopstick\n", i+1);
			int left = (i+4)%numPhilosophers;
			int right = (i+1)%numPhilosophers;
			test4Return(left);
			test4Return(right);
		}finally {l.unlock();}
	}
	
	private boolean test4Take(int i)
	{
		int left = (i+4)%numPhilosophers;
		int right = (i+1)%numPhilosophers;
		return (states[left] != State.eating) && (states[right] != State.eating);
	}
	
	private void test4Return(int i)
	{
		if((states[(i+4)%numPhilosophers] != State.eating) && states[i] == State.hungry && (states[(i+1)%numPhilosophers] != State.eating))
		{
				states[i] = State.eating;
				self[i].signal();	
		}
	}
}
