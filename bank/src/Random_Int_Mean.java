import java.util.Random;

public final class Random_Int_Mean
{
  static int initialized = 0;
  static Random r = new Random();

  public static void init_random_int(int seed)
  {
    /*
     * Initializes the random number generator.  If seed is negative then the
     * system clock is used to initialize the generator.  A count is also kept
     * of the number of times this routine has been called.
     */
    if (seed < 0) seed = (int) (System.currentTimeMillis() / 1000);
    r.setSeed(seed);
    initialized++;
  }

  int random_int(int mean)
  {
    /*
     * Computes a random integer from an exponetial distribution with a
     * specified mean.  If this routine is called and the generator has not
     * yet been initialized, it initializes it using the system clock.
     */
    if (initialized == 0) init_random_int(-1);

    /*
     * Find number from exponentially distribution with specified mean
     * and round to an integer.
     */
    int value = (int) (0.5 - mean * Math.log(r.nextDouble()));

    if (value == 0)
      value = 1;
    else
      if (value > 5 * mean) value = 5 * mean;

    return value;
  }
}