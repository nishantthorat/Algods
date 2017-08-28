import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuadrapalSumDriver {

	/***
	 * 
	 * Given an unsorted array of integers arr and a number s, write a function findArrayQuadruplet that finds four numbers (quadruplet) 
	 * in arr that sum up to s. Your function should return an array of these numbers in an ascending order. If such a quadruplet doesn’t exist, 
	 * return an empty array.
	 * Note that there may be more than one quadruplet in arr whose sum is s. You’re asked to return the first one you encounter.
	 * 
	 * input:  arr = [2, 7, 4, 0, 9, 5, 1, 3], s = 20
	 * output: [0, 4, 7, 9] 
	 */
	public static void main(String[] args) {
	
		ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2,7,4,0,9,5,1,3));
		System.out.println(findQudrapal(arr, 20));

	}
	
	// 2, 7, 4, 0, 9, 5, 1, 3 ==> 20
	// 0, 1, 2, 3, 4, 5, 7, 9 ==> 
	// 01, 02, 03, 04, 05, 07, 09
	static ArrayList<Integer> findQudrapal(final ArrayList<Integer> original, final int targetSum){
		
		if (null == original || original.size() < 4){
			return null;
		}
		
		Collections.sort(original);
		
		ArrayList<Integer> arr = new ArrayList<>();
		// we iterate over the array in two loops so that we may pickup two integers from each loop 
		for (int i =0; i <= original.size() - 4; i++){
			for (int j = i+1; j <= original.size() - 3; j++){
				
				// find next pair in j+1, n-1
				int s = targetSum - (original.get(i) + original.get(j));
				int lo = j+1, hi = original.size() - 1;
				while (lo < hi){
					int currentSum = (original.get(lo) + original.get(hi));
					
					if (s == currentSum){
						System.out.println(String.valueOf(original.get(i)) 
								+ String.valueOf(original.get(j)) 
								+ String.valueOf(original.get(lo)) 
								+ String.valueOf(original.get(hi)));
						arr.clear();
						arr.add(original.get(i));
						arr.add(original.get(j));
						arr.add(original.get(lo));
						arr.add(original.get(hi));
						return arr;
					}
					
					if (currentSum < s){
						lo++;
					}else{
						hi--;
					}
				}
			}
		}
		return arr;
	}
	

}
