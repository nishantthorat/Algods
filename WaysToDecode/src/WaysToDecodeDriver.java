import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Ways to Decode
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

Example :

Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
26262 = (2 6 2 6 2) (26 2 6 2) (2 6 26 6 2)  (26 26 2) 
24232614 = (2 4 2 3 2 6 1 4) (24 2 3 2 6 1 4) (24 23 2 6 1 4) (24 23 26 1 4) (24 23 26 14)
9987654 = (9 9 8 7 6 5 4) 
 */
public class WaysToDecodeDriver {

	public static void main(String[] args) {
		//System.out.println(findNumberOfWaysToDecode("26143324"));
		System.out.println(findNumberOfWaysToDecode("5163490394499093221199401898020270545859326357520618953580237168826696965537789565062429676962877038781708385575876312877941367557410101383684194057405018861234394660905712238428675120866930196204792703765204322329401298924190"));
	}
	
	static int findNumberOfWaysToDecode(final String message){
		Integer ways = 0;
		
		if (message == null || message.isEmpty()){
	        return 0;
	    }
		
		boolean foundZero = false;
		List<String> combinations = new ArrayList<>();
		for (int i=0; i< message.length(); i++){
			foundZero = false;
			
			if (('0' == message.charAt(i))){
				foundZero = true;
			}
			String singleChar = message.substring(i, i+1);
			if ((singleChar != null && !singleChar.isEmpty() && !isValidDuplet(message.charAt(i), message.charAt(i+1), Integer.valueOf(singleChar)))){
				
				return ways;
			}
			combinations.add(singleChar);
		}
		ways = 1;

		System.out.println(combinations);
		
		for (int index=0;index < message.length()-1; index++){
			ways += findWays(new LinkedList<String>(combinations), index);
		}
		
		return ways;
	}
	
	static boolean isValidDuplet(char c, char d, int n){
		return n <= 26; // number of alphabets
	}
	
	static int findWays(List<String> combinations, int startIndexInCombinations){
		
		// make sure we can form a duplet
		if (startIndexInCombinations+1 > combinations.size()-1){
			return 0;
		}
		
		String duplet = combinations.get(startIndexInCombinations) + combinations.get(startIndexInCombinations+1);
		System.out.println("Comsidering duplet: " + duplet);
		if( !isValidDuplet(Integer.valueOf(duplet))){
			return 0;
		}
		
		// able to form duplet so remove ones which formed duplet together and insert duplet at its place
		combinations.remove(startIndexInCombinations);
		combinations.remove(startIndexInCombinations);
		combinations.add(startIndexInCombinations, duplet);
		
		System.out.println(combinations);
		int ways = 1;
		
		for (int index = startIndexInCombinations+1; index < combinations.size()-1; index++){
			// advance by one
			ways += findWays(new LinkedList<String>(combinations), index);
		}
		
		return ways;
		
	}

}
