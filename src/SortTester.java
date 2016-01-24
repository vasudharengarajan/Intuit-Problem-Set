/**
 * @author Vasudha Rengarajan
 * The method SortTester tests the Sort method and contains the main method and a
 * trivial helper method to print the sorted list (simply for convenience.
 */
public class SortTester 
{
	/**
	 * The main method prints the list before and after sorting to demonstrate
	 * that the given list is sorted.
	 */
	public static void main (String[] args)
	{
		// test the sort
		int[] numList = {1,10,5,63,29,71,10,12,44,29,10,-1};
		printArray(numList);
		Sort numSort = new Sort();
		numSort.sort(numList);
		printArray(numList);
	}
	
	/**
	 * The method printArray prints the array.
	 * @param array - the array to print
	 */
	public static void printArray(int[] array)
	{
		for (int i=0; i<array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
