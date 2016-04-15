
public class LongestIncreasingSubsequenceDemo {

	public static void main(String[] args) {
		LongestIncreasingSubsequenceDemo demo = new LongestIncreasingSubsequenceDemo();
		int[] sequence = new int[] { 12, 18, 7, 34, 30, 28, 90, 88, 96, 101, 198, 0 , 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10, 11, 12};
		System.out.println(demo.calcDistance(sequence));

	}
	
	private int calcDistance(int[] sequence){
		int longestDistannce = 0;
		int [] dist = new int[sequence.length];
		dist[0] = 1;
		// iterate over input sequence
		for (int i = 1; i <  sequence.length; i++){
			dist[i] = 1; // initialize with 1
			
			// consider all combinations until now
			for (int j=0; j < i; j++){
				// check if it is in increasing order or not
				if (sequence[i] > sequence[j]){
					// if yes then - check if dist[j] + 1 > dist[i]
					if (dist[j] + 1 > dist[i]){
						dist[i] = dist[j] + 1;
					}
				}
				
				if (longestDistannce < dist[i]){
					longestDistannce = dist[i];
				}
			}
		}
		
		return longestDistannce;
	}

}
