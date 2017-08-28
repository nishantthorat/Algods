import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StringMinWIndowDriver {

	public static void main(String[] args) {
		printAllStringCombinations("this is a test string", "xyz");
	}
	
	static void printAllStringCombinations(final String original, final String search){
		/*for (int i=0, currentIndex = 1, beginIndex = 0; i< original.length(); i++){
			printString(original, beginIndex++, currentIndex++);
		}*/
		
		calcMinWindow(original, new HashSet<Character>(asList(search)));
	}
	
	static void printString(final String original, int beginIndex, int currentIndex){
		System.out.println(original.substring(beginIndex, currentIndex++));
		
		if (currentIndex <= original.length()){
			printString(original, beginIndex, currentIndex);
		}
	}
	
	static void calcMinWindow(final String original, Set<Character> searchSet){
		
		int min = 0;
		for (int i=0, currentIndex = 0;  i< original.length(); i++,currentIndex++){
			String startStr = "";
			Set<Character> foundSet = new HashSet<>();
			int currentMin = minWindow(original, startStr, original.charAt(currentIndex), currentIndex, searchSet, foundSet);
			
			/*if (currentMin > 0 && ){
				min = currentMin;
			}*/
		}
		
		//System.out.println(min);
	}

	private static int minWindow(String original, String currentStr, char append, int currentIndex, Set<Character> searchSet, Set<Character> foundSet) {
		// if currentStr length is one and is one of the chars to find then only proceed
		if (currentStr.length() == 0 && !searchSet.contains(append)){
			// no need to go further as in min window it would always start and end with one character we are looking for
			return -1;
		}
		
		// append character to form next string we are looking for
		// now this is formed string
		currentStr += append; currentIndex++;
		if (searchSet.contains(append)){
			foundSet.add(append);
			
			if (foundSet.containsAll(searchSet)){
				System.out.println("Finished Prob (successful) : " + currentStr);
				return currentStr.length() + 1;
			}
		}
		
		if (currentIndex >= original.length()){
			System.out.println("Finished Prob (unsuccessful) : " + currentStr);
			return -1;
		}
		
		return minWindow(original, currentStr, original.charAt(currentIndex), currentIndex, searchSet, foundSet);
		
	}
	
	public static List<Character> asList(final String string) {
	    return new AbstractList<Character>() {
	       public int size() { return string.length(); }
	       public Character get(int index) { return string.charAt(index); }
	    };
	}

}
