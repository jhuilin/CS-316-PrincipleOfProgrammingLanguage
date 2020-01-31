
public class SleepUtilities 
{
	private static final int nap_time = 3;
	public static void nap()
	{
		nap(nap_time);
	}
	public static void nap(int d)
	{
		int sleeptime = (int)(d*Math.random());
		try
		{
			Thread.sleep(sleeptime * 1000);
		}catch(InterruptedException e) {}
	}
}
