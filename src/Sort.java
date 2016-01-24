/**
 * @author Vasudha Rengarajan
 * 
 * I chose to use MergeSort as my sort for two simple reasons. First, it is one of the
 * efficient arrays so I certainly did not want to stick with something like BubbleSort
 * or SelectionSort. Secondly, choosing between QuickSort and MergeSort, I chose 
 * Mergesort because it is stable and I feel that stability, even if not relevant in 
 * the given problem because the integers are identical, should be a consideration in good
 * design. Plus, there are repetitions of the same number in this list so stability is
 * not completely eliminated from the equation, even if the integers are identical in
 * all ways -- really, both sorts solve the given problem, but MergeSort is safer in
 * general.
 */
public class Sort 
{
     
    private int[] origArray;
    private int length;
 
    /**
     * The class sort takes in an array of integers a and sorts it. The method
     * saves the array to private instance variables (and its length for simplicity
     * later) and calls the method mergesort.
     * @param a the given array
     */
    public void sort(int a[]) 
    {
        this.origArray = a;
        this.length = a.length;
        mergesort(0, length - 1);
    }
    /**
     * The method mergesort recursively makes calls to divide the lists into
     * smaller lists and then sort them as they are combined into one full sorted
     * array.
     * @param lowerIndex - the integer lower index of the part of the list
     * @param higherIndex - the integer higher index of the part of the list
     */
    private void mergesort(int lowerIndex, int higherIndex) 
    {
    	//if can be divided into halves (not list of 0 or 1)
        if (lowerIndex < higherIndex) 
        {
        	// set the middle index with the understanding that one portion may be smaller (not necessarily equal) 
            int midIndex = lowerIndex + (higherIndex - lowerIndex) / 2;
            // mergesort the left - keep dividing
            mergesort(lowerIndex, midIndex);
            // mergesort the right - keep dividing
            mergesort(midIndex + 1, higherIndex);
            // now merge - combine the left and right parts
            merge(lowerIndex, midIndex, higherIndex);
        }
    }
 
    /**
     * The method merge takes in the indexes lower, mid, and higher and merges the
     * list to sort.
     * @param lower - the lower index (int)
     * @param mid - the middle index (int)
     * @param higher - the higher index (int)
     */
    private void merge(int lower, int mid, int higher) 
    {
    	/*
    	 * make copy of original array - create temp array and set its 
    	 * values to orig array's values
    	 */
    	int[] tempArray = new int[length];
        for (int i = lower; i <= higher; i++) 
        {
            tempArray[i] = origArray[i];
        }
        
        int leftCursor = lower;
        int rightCursor = mid + 1;
        int listCursor = lower;
        
        /*
         * While traversing the left portion and the right portion, sort as the
         * list is combined into the the origArray. the listCursor keeps track of
         * where in the big (combined) list we are.
         */
        while (leftCursor <= mid && rightCursor <= higher) 
        {
            if (tempArray[leftCursor] <= tempArray[rightCursor]) 
            {
            	origArray[listCursor] = tempArray[leftCursor];
            	leftCursor++;
            } 
            else 
            {
            	origArray[listCursor] = tempArray[rightCursor];
            	rightCursor++;
            }
            listCursor++;
        }
        
        /*
         * This portion is useful because (as explained before), when the list is odd,
         * the left portion's remainder of 1 element should be added. The left is left
         * with the remainder becuase I designed it such that the left portion includes
         * the middle in the case of an odd number of values to sort.
         */
        while (leftCursor <= mid) 
        {
        	origArray[listCursor] = tempArray[leftCursor];
        	listCursor++;
            leftCursor++;
        }
 
    }
}