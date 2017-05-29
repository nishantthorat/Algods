import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CombinationSumDriver {

	public static void main(String[] args) {
		Set<Integer> numbers = new LinkedHashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31));
		
		combinationSum(numbers, 90);

	}

	static void combinationSum(Set<Integer> numbers, int target){
		// sort them
		List<Integer> original = new ArrayList<>(numbers);
		for (int i=0; i < original.size();i++){
			combineAndSum(original, 0, new ArrayList<Integer>(), i, target);
		}
	}
	
	static void combineAndSum(List<Integer> numbers, int sumThusFar, List<Integer> setThusFar, int nextIndex, int target){
		// check if we have achieved sum or not if yes return
		if (nextIndex >= numbers.size() ){
			return; // we are past numbers
		}
		sumThusFar += numbers.get(nextIndex);
		setThusFar.add(numbers.get(nextIndex)); // add into list
		
		if (sumThusFar == target){
			// this is it!
			System.out.println(setThusFar);
			return;
		}
		
		if (sumThusFar > target){
			return; // no use probing further
		}
		
		// now without considering this number at nextIndex
		if (++nextIndex > numbers.size()){
			return;
		}
		combineAndSum(numbers, sumThusFar, setThusFar, nextIndex, target);
	}
}
