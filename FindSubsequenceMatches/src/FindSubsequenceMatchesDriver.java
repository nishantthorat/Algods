
// https://stackoverflow.com/questions/6877249/find-the-number-of-occurrences-of-a-subsequence-in-a-string/41893487#41893487
public class FindSubsequenceMatchesDriver {

	private static int[][] solution = null;
	public static void main(String[] args) {
		System.out.println(findSubSequenceMatches("1123123", "123"));
	}
	
	private static int findSubSequenceMatches(final String input, final String subSequence){
		if (null == input || input.isEmpty() || null == subSequence || subSequence.isEmpty()){
			return 0;
		}
		
		// this matrix represents rows - input sequence bottom up style where bottom row is
		solution = new int[input.length()+1][subSequence.length()+1];
		
		// ----- initialize solution matrix -----
		// 0th column to be always 1 - if subsequence is exhausted we know we have a match
		for (int row = 0; row < solution.length; row++){
			solution[row][0] = 1;
		}
		
		// 0th row to be always 0 (except first column) - its like we have subsequence but we exhausted input :(
		for (int col = 1; col < solution[0].length; col++ ){
			solution[0][col] = 0;
		}
		
		findSubSequenceMatchesRecursively(input, subSequence, input.length(), subSequence.length());
		
		return solution[input.length()][subSequence.length()];
	}
	
	private static void findSubSequenceMatchesRecursively(String input, String subSequence, int rowInput, int colSequence){
		if (null == input || input.isEmpty() || null == subSequence || subSequence.isEmpty()){
			return;
		}
		
		char in = input.charAt(0);
		char sub = subSequence.charAt(0);
		boolean matched = in == sub;
		
		String discardedInput = input.substring(1, input.length());
				
		findSubSequenceMatchesRecursively(discardedInput, subSequence, --rowInput, colSequence);
		// copy from same column of previous row
		solution[rowInput+1][colSequence] = solution[rowInput][colSequence]; 
				
		// if first character in input matches to first char in subSequence, update solution matrix
		if (matched){
			input = input.substring(1, input.length());
			subSequence = subSequence.substring(1, subSequence.length());
			findSubSequenceMatchesRecursively(input, subSequence, rowInput, --colSequence);
			solution[rowInput+1][colSequence+1] += solution[rowInput][colSequence];
		}
	}
}
