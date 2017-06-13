import java.util.ArrayList;
import java.util.Arrays;

public class MaxProductSubarrayDriver {

	public static void main(String[] args) {
	
		System.out.println(findMaxProductSubArrayModifiedKedane(new ArrayList<Integer>(Arrays.asList(-2, -3, 5, -4, 0, 99, 1))));
	}
	
	
	
	/**
	 *  Understand that unlike addition, multiplication of two negative numbers result into positive number
	 *  Also note that multiplication of positive with negative will result into negative and vice versa
	 *  Also note that it also means a positive (max) can become negative (min) and vice versa
	 *  Also note that it means we need to track two max one towards negative infinity and another one towards positive infinity
	 *  Max towards positive infinity is what we are seeking
	 * @param original
	 * @return
	 */
	private static int findMaxProductSubArrayModifiedKedane(final ArrayList<Integer> original){
		
		if (null == original || original.isEmpty()){
			return Integer.MIN_VALUE;
		}
		
		int max_ending_here = original.get(0);
		int min_ending_here = original.get(0);
		int max_so_far = original.get(0);
		
		for (int i=1; i< original.size(); i++){
			// multiplication by negative number makes small number large and vice versa
			if (original.get(i) < 0){
				// swap
				int temp = max_ending_here;
				max_ending_here = min_ending_here;
				min_ending_here = temp;
			}
			
			// max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
			max_ending_here = Math.max(original.get(i), original.get(i) * max_ending_here);
			min_ending_here = Math.min(original.get(i), original.get(i) * min_ending_here);
			
			max_so_far = Math.max(max_ending_here, max_so_far);
		}
		
		System.out.println(max_so_far);
		return max_so_far;
	}
	

}
