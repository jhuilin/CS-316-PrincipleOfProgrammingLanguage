
public class assignment2 {
	public static void main(String[] args) 
	{
			int philosophersNum = 5;
			philosopher[] p = new philosopher[philosophersNum];
			monitor mon = new monitor(philosophersNum);
			System.out.println("let`s have Dinner");
			for(int i=0;i<philosophersNum;++i)
				p[i] = new philosopher(i,mon);
			for(int i=0;i<philosophersNum;++i)
			{
				try
				{
					p[i].t.join();
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
	}
}
