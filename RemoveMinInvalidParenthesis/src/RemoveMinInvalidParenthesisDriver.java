import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoveMinInvalidParenthesisDriver {
	static Map<String, Integer> mapValidInput = new HashMap<>();
    static Set<String> set = new HashSet<>();
    
	public static void main(String[] args) {
		String input = "()(";
		System.out.println(input + " " + minRemoveParenthesis(input, 0));
	}
	
	static int minRemoveParenthesis(String input, int level){
		if (null == input || input.isEmpty() || 1 == input.length()){
			return -1;
		}
		
		// now we have a input which needs to be checked in map
		/*if (mapValidInput.containsKey(input)){
			// check its not -1 i.e. cannot form a valid input
			if (-1 == mapValidInput.get(input)){
				return -1;
			}else{
				// we know how many needs to be removed
				return level + mapValidInput.get(input);
			}
		}else{*/
			// no information available in map
			if (isValid(input)){
				//System.out.println(input + " ==> " + 1);
				//mapValidInput.put(input, 1);
				return 0;
			}else{
				
				int minRemoved = Integer.MAX_VALUE;
				// for each parenthesis being removed do a DFS
				for (int i=0; i< input.length(); i++){
					String subLevelInput = input.substring(0, i) + input.substring(i+1, input.length());
					int removed = minRemoveParenthesis(subLevelInput, level+1);
					
					if (removed >= 0 && minRemoved > removed){
						minRemoved = removed;
					}
					
					//mapValidInput.put(subLevelInput, removed);
				}
				//mapValidInput.put(input, minRemoved);
				return minRemoved;
			//}
		}
	}

	static boolean isValid(String input){
		int count = 0;
		
		for (int i=0; i < input.length(); i++){
			switch(input.charAt(i)){
			case '(': 
				count++;
				break;
			case ')':
				count--;
				break;
			}
			
			if (count < 0){
				return false;
			}
		}
		
		if (count == 0){
			return true;
		}
		
		return false;
		
	}
}
