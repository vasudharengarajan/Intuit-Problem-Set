/**
 * @author Vasudha Rengarajan
 * FindPathTester is a tester class for FindPath, which takes in a JSON
 * and find the path to each contained value.
 */
public class FindPathTester 
{
	/**
	 * The main method is the only method in the tester class. It tests the class
	 * FindPath for every single value in the JSON. All checked out. Comments are 
	 * within.
	 */
	public static void main (String[] args)
	{
		/*
		 * Create an instance of FindPath to test.
		 */
		FindPath myFindPath = new FindPath();
		
		/*
		 *  Test the situation where a key holds a single value but each element in the
		 *  JSONArray 'items' can have multiple keys.
		 */
		System.out.println(myFindPath.getPath("item2"));
		System.out.println(myFindPath.getPath("Item 2"));
		System.out.println(myFindPath.getPath("item3"));
		System.out.println(myFindPath.getPath("item4"));
		System.out.println(myFindPath.getPath("item5"));
		System.out.println(myFindPath.getPath("subItem1"));
		System.out.println(myFindPath.getPath("item6"));
		System.out.println(myFindPath.getPath("item7"));
		System.out.println(myFindPath.getPath("Item 7"));
		System.out.println(myFindPath.getPath("subItem2"));
		
		/*
		 * Test the situation in which a key holds a JSONArray.
		 */
		System.out.println(myFindPath.getPath("subItem1Item1"));
		System.out.println(myFindPath.getPath("SubItem 1"));
		System.out.println(myFindPath.getPath("subItem1Item2"));
		System.out.println(myFindPath.getPath("SubItem 2"));
		System.out.println(myFindPath.getPath("subItem1Item3"));
		System.out.println(myFindPath.getPath("SubItem 3"));
		
		/*
		 * Test the situation in which a value is twice repeated in the JSON.
		 * Must retrieve both locations.
		 */
		System.out.println(myFindPath.getPath("item1"));
		
		/*
		 * Test the situation in which the key holds the value of a JSONObject
		 */
		System.out.println(myFindPath.getPath("SubItem 2 item1"));
		
		
	}
}
