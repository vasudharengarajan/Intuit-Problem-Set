import java.util.ArrayList;

/**
 * @author Vasudha Rengarajan
 * 
 * This class PrimeNumberMachine calculates the nth prime number. This is an optimized
 * solution that I understood by researching a few different mathematical concepts. The
 * reason I did this is that it was very apparent that for this problem, the optimal solution
 * that will really shave time and memory off must be a mathematical discovery, not simply
 * a coding puzzle. Therefore, I came to understand the Sieve of Eratosthenes. 
 * Details of how I devised the algorithm and came to my math insights are given 
 * in the method comment. 
 */
public class PrimeNumberMachine 
{
	/**
	 * I knew that this was the solution I wanted to go with because by intuition and 
	 * dynamic programming with pen and paper I was able to come to conclusion that 
	 * only the square root of the value is the highest necessary number to test. For 
	 * example if the number is 50, by the time one has gone through all the 
	 * possibilities for 1 (1*1, 1*2, 1*3, ... 1*7) and 2 (2*2, 2*3...) and so forth 
	 * until the number 7, we know that 7*7 is not a prime number because it is a perfect
	 * square and therefore we do not need to test this number. Having come to that 
	 * conclusion, I read up on the Sieve of Eratosthenes and understood the fuller picture.
	 */
	public int calcPrime(int n) 
	{
	    // Create the ArrayList with the first two values of prime number 2 and 3.
		ArrayList<Integer> primes = new ArrayList<Integer>();
	    primes.add(2);
	    primes.add(3);
	    
	    /*
	     *  In my algorithm, I started with four because I was asked to give the value
	     *  for the 5th prime number and didn't want to spit it out without computation.
	     *  Also, working with smaller numbers helped me reason through the algorithm
	     *  a lot more easily.
	     */
	    
	    int value = 4;
	    
	    while(primes.size() <= n-1) 
	    {
	    	/*
	    	 * It is an obvious step that sieving out multiples of 2 and 3 will
	    	 * make my program at least twice as fast for having to go through less
	    	 * than half the numbers from 1 to n.
	    	 */
	        if(value%2 != 0 && value%3 != 0) 
	        {
	            int temp = 4;
	            boolean doContinue = true;
	            
	            while(doContinue==true && temp <= (int)Math.sqrt(value))
	            {
	            	// if the value is not divisible by the temp, then do not continue
	                if(value % temp == 0)
	                    doContinue = false;
	                // otherwise increment temp.
	                else
	                	temp++;
	            }
	            
	            // when temp becomes greater than sqrt(value), add to the array.
	            if(temp > (int)Math.sqrt(value)) 
	            {
	                primes.add(value);
	            }
	        }
	        // increment value
	        value++;
	    }
	// return the prime from the created array
	return primes.get(n-1);
    }
	
}
