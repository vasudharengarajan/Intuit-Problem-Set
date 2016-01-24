/**
 * @author Vasudha Rengarajan
 * 
 * The class PrimeNumberMachineTester tests the class PrimeNumberMachine which
 * finds the nth prime number.
 */
public class PrimeNumberMachineTester 
{
	/**
	 * The only method in the tester is the main method. It tests the given cases:
	 * Find the 3rd, 58th, and 100001th prime numbers. The answers checked out.
	 */
	public static void main (String[] args)
	{
		// test the prime number finder
		PrimeNumberMachine primer = new PrimeNumberMachine();
		System.out.println(primer.calcPrime(3));
		System.out.println(primer.calcPrime(58));
		System.out.println(primer.calcPrime(10001));
	}
	
}
