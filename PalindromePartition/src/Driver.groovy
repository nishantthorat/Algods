import org.apache.ivy.core.IvyPatternHelper.OriginalArtifactNameValue

class Driver {
	
	static class OutData{
		int minCuts
		List<String> cuts
	}

	static main(args) {
		final String original = 'abanana'
		printAll(original)
		
		//int minCut = 0
		def min = original.length()
		
		//for (int i=0; i < original.length();i++){
			def c = minPartitions(original)
			if (min > c){
				min = c
			}
		//}
		
		//println 'Min cuts required: ' + min
	}
	
	static printAll(String original){
		println 'Original String is ' + original
		
		printAllSubstrings(original)
	}
	
	/***
	 * Calculate minimum cuts required for all partitions to be palindromes
	 * @param original
	 * @return
	 */
	def static int minPartitions(String original){
		def palim = isPalindrome(original)
		//println original + ' Palindrome = ' + palim
		
		if (isPalindrome(original) || 1 == original.length()){
			return 0
		}
		
		return Math.min( minPartitions(original.substring(0,  original.length()-1)) + 1 , 
			minPartitions(original.substring(1 , original.length())) + 1)
		
	}
	
	/***
	 * Print all substrings
	 * @param original
	 */
	def static void printAllSubstrings(final String original){
		def palim = isPalindrome(original)
		println original + ' Palindrome = ' + palim
		
		if (original.length() == 1 || palim){
			// this will be a palindrome for sure
			return;
		}
		
		// split into two parts - b|anana, ba|nana, ban|ana
		//println "First Partition : " + original.substring(0,  original.length()-1) + ", Second Partition: " + original.substring(1 , original.length())
		printAllSubstrings (original.substring(0,  original.length()-1))
		printAllSubstrings (original.substring(1 , original.length()))
	}

	def static boolean isPalindrome(String original){
	   def hi = original.length()-1
	   def lo = 0
	   
	   while(lo < hi){
		   if (original.charAt(lo++) != original.charAt(hi--)){
			   return false
		   }
	   }
	   return true
	}
}
