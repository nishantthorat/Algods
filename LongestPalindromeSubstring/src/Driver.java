

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//printAllSubStrings("abcd");
		longestPalindrome("forgeeksskeegfor");
		longestPalindrome("banana");
		longestPalindrome("abcbabcbabcba");
	}
	
	static void printAllSubStrings(final String original){
		// abcd => a b c d, ab bc cd, abc bcd, abcd, 
		
		for (int i=0; i< original.length(); i++){
			for (int j = i+1; j <= original.length(); j++){
				String s = original.substring(i, j);
				System.out.println(s);
			}
		}
	}
	
	static void longestPalindrome(final String original){
		// maintain a table of [n][n] its initialized to false by default
		boolean palim[][] = new boolean[original.length()][original.length()];
		
		// for length starting from 0 till n-1 increment by one each loop (curr_len)
		// for each string str starting at 0 till n-1 of length curr_length i
		// check if palim[i][curr_len] = true if yes check if start char is same as last character
		// palim check to be done for length greater than 2
		int biginAt = -1;
		int max_len = 0;
		for (int curr_len = 0; curr_len < original.length(); curr_len++){
			for (int i= 0; i< original.length(); i++){
				if (i+curr_len+1 <= original.length()){
					String s = original.substring(i, i+curr_len+1);
					//System.out.println(i + " " + curr_len + " " + original.substring(i, i+curr_len+1));
					if (curr_len < 2 && s.charAt(0) == s.charAt(curr_len)){
						palim[i][i+curr_len] = true;
						//System.out.println(i + " " + (i+curr_len));
						biginAt = i;max_len = curr_len;
					}
					if (curr_len >= 2) {
						if (s.charAt(0) == s.charAt(curr_len) && palim[i+1][i+curr_len-1] == true){
							palim[i][i+curr_len] = true;
							//System.out.println("matched" + curr_len);
							biginAt = i;
							max_len = curr_len;
						}
					}
				}
			}
		}
		
		System.out.println(original.substring(biginAt, biginAt+max_len+1));
		
	}
	

}
