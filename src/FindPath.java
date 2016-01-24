/*
 * Import packages to deal with errors and exceptions.
 */
import java.io.FileNotFoundException;
import org.json.simple.parser.ParseException;
import java.io.IOException;

/*
 * Import packages specific to JSON and supplementary parsing and file reading packages.
 */
import java.util.HashMap;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

/** 
 * @author Vasudha Rengarajan
 * 
 * The class FindPath finds the path to any value in the given JSON. For simplicity in
 * the tester and because the code applies to only a specific case given, the name of 
 * the file is hardcoded to be Intuit.json.
 * 
 * The class FindPath contains two methods. One method is getPath. The other is 
 * makePathString, a recursive method that does most of the assembling and dealing
 * with the varying structures (HashSets/JSONObjects and ArrayLists/JSONArrays) that 
 * the data is held in. The class is block commented within.
 */
public class FindPath 
{
	/*
	 * The purpose of these private variables is simple. They provide an easy
	 * reference with which to use the getClass method. The getClass method is
	 * used in the code because of the varying structures (JSONArrays and JSONObjects)
	 * which must be each addressed in specific ways. The use of this becomes clear in
	 * the makePathString method.
	 */
	static JSONObject sampleObject = new JSONObject();
	static JSONArray sampleArray = new JSONArray();
	
	/**
	 * The method getPath takes in the string the user wishes to find and returns
	 * the path. It uses a recursive helper method makePathString to which it appends
	 * the preliminaries of the path. The reason for separating the path creation
	 * into two methods (i.e. this one and its helper) is for efficiency in the case
	 * of the desired value not being found. The use of this split is made apparent in
	 * the block comments contained within.
	 * 
	 * @param toFind - The String value whose path the user wishes to get.
	 * @return String path if found.
	 */
	public String getPath (String toFind)
	{
		/*
		 * Everything in the getPath method is contained in a try catch statement to
		 * deal with potential errors with the JSON.
		 */
		JSONParser parser = new JSONParser();
		
		/*
		 * The try segment delegates the structural navigation to the makePathString.
		 * The try segment does three simple things 1) quickly return not found if the
		 * value is not contained in the JSONObject and 2) tack on the appropriate path
		 * segment if it is found and 3) delegate the rest of the path to the helper 
		 * method, which finds the value if contained in an element of the JSONArray items. 
		 * 
		 * It thereby produces the final path and returns this value.
		 */
		try 
		{
			// The path starts off as nothing.
			String path = "";
			
			/*
			 * Read the file and convert to a string to quickly find if the value
			 * is contained in that string (and thereby the JSONObject. If not,
			 * return the String "Not Found."
			 */
			Object obj = parser.parse(new FileReader("Intuit.json"));
			JSONObject jsonObject = (JSONObject) obj;			
			String fullDict = jsonObject.get("itemList").toString();
			if (!fullDict.contains(toFind))
			{
				return "Not Found.";
			}
			
			/*
			 * Else (if the value is contained), the path will always begin with /itemList/items.
			 * Each hashSet contained in the JSONArray must be searched to find the occurrence(s)
			 * of the value.
			 */
			else
			{
				// The firstpart will always be /itemList/items for this JSON.
				String firstpart = "/itemList/items";
				JSONObject itemList = (JSONObject) jsonObject.get("itemList");
				JSONArray items = (JSONArray) itemList.get("items");
				
				/*
				 * For each hashSet, tack on the first bit if it's a unique locatin 
				 * or handle the situation in which two keys hold the same value. 
				 * 
				 * NOTE:
				 * I could have improved my program by making it more extensible in
				 * one key way. Even though my hardcoding was a choice because
				 * I didn't want my user to break my code by inputting values not handled by
				 * the instructions of the exercise, that would have been a smart move had I
				 * been creating it for production and use -- it would have been more valuable.
				 * Of course, it is okay because that wasn't the task assigned, but just a thought.
				 * Although looking at my code now, I can see that it can probably handle a value that
				 * appears more than twice, I did not test that possibility (again, because it fell
				 * outside the realm of the assignment).
				 */
				for (int i=0; i<items.size(); i++)
				{
					HashMap map = (HashMap) items.get(i);
					String s = makePathString(map, toFind);
					if (!s.equals(""))
					{
						if (path.equals(""))
							path=firstpart+"["+i+"]/"+s;
						else 
							path=path + " OR " + firstpart+"["+i+"]/"+s;
					}
				}
				return path;
			}
		} 
	
		/*
		 * Catch exceptions if they arise.
		 */
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (ParseException e) {e.printStackTrace();}
		return "Not Found.";
     }
	
	/**
	 * The method makePathString is a recursive method that handles three scenarios 
	 * that arise in parsing this JSONArray's elements to find the path: 1) The element's
	 * keys hold Strings, 2) The element's keys hold JSONArrays 3) The element's keys hold 
	 * JSONObjects.
	 * 
	 * NOTE:
	 * Another data structure that would have worked in a largely similar way is a tree.
	 * That is not what I used because I was not particularly concerned with extensibility
	 * for this specific assignment, but it definitely would have been a smart alternative.
	 * 
	 * @param map - each element of the JSONArray items is a HashMap (JSONObject) 
	 * @param s - the string value whose path the user wants.
	 * @return String - the variant of the path (the part that does not vary is handled 
	 * in the other method and is simply tacked on to the front).
	 */
	public static String makePathString(HashMap map, String s)
	{
		/*
		 * There are two different return strings because we wish to identify when
		 * a value occurs more than one. 
		 * 
		 * NOTE:
		 * An area of improvement here is that although in this specific example, the 
		 * same value was contained in two different cases (One was in an element with
		 * the value of a String and the other was in an element with the value of a 
		 * JSONObject), I could also easily handle if both values were in keys of
		 * the same hierarchy. Just a thought.
		 */
		String toReturn="";
		String embeddedReturn="";
		
		Set<String> set = map.keySet();
		Object[] keys = set.toArray();
		
		/*
		 * For each key in the element...
		 */
		for (int i=0; i<keys.length; i++)
		{
			
			/*
			 * Find the path if the key contains a String value.
			 */
			if (map.get(keys[i]).equals(s))
			{
				toReturn = keys[i].toString();
			}
			
			/*
			 * Find the path if the key contains a JSONArray value.
			 */
			else if (map.get(keys[i]).getClass() == sampleArray.getClass())
			{
				JSONArray a = (JSONArray) map.get(keys[i]);
				for (int j=0; j<a.size(); j++)
				{
					HashMap myHashMap = (HashMap) a.get(j);
					String innerReturn="";
					innerReturn = makePathString(myHashMap, s);
					if (!innerReturn.equals(""))
						embeddedReturn = keys[i].toString() + "[" + j + "]/" + innerReturn;
				}
			}
			
			/*
			 * Find the path if the key contains a JSONObject value.
			 */
			else if (map.get(keys[i]).getClass() == sampleObject.getClass())
			{
				HashMap myEmbeddedHashMap = (HashMap) map.get(keys[i]);
				String innerReturn="";
				innerReturn = makePathString(myEmbeddedHashMap, s);
				if (!innerReturn.equals(""))
					embeddedReturn = keys[i].toString() + "/" + innerReturn;
			}
				
		}
		
		/*
		 * This if statement indicates that my program is not geared to work if
		 * the value whose path we are looking for occurs at the same point in the
		 * hierarchy (aka at items[0] and items[1]. Like I mentioned earlier, this was
		 * not an issue in the given problem. But just a note for making the program
		 * better and more extensible.
		 */
		if (toReturn.equals("") || embeddedReturn.equals(""))
			return toReturn+embeddedReturn;
		
		return "";
	}
	
}
