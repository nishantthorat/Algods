/**
 * You are given an integer array with N elements: d[0], d[1], ... d[N - 1]. 
You can perform AT MOST one move on the array: choose any two integers [L, R], and flip all the elements between (and including) the L-th and R-th bits. L and R represent the left-most and right-most index of the bits marking the boundaries of the segment which you have decided to flip.

What is the maximum number of '1'-bits (indicated by S) which you can obtain in the final bit-string? 

'Flipping' a bit means, that a 0 is transformed to a 1 and a 1 is transformed to a 0 (0->1,1->0). 
Input Format 
An integer N 
Next line contains the N bits, separated by spaces: d[0] d[1] ... d[N - 1] 

Output: 
S 

Constraints: 
1 <= N <= 100000 
d[i] can only be 0 or 1 
0 <= L <= R < n 

Sample Input: 
8 
1 0 0 1 0 0 1 0 

Sample Output: 
6 

Explanation: 

We can get a maximum of 6 ones in the given binary array by performing either of the following operations: 
Flip [1, 5] ==> 1 1 1 0 1 1 1 0

// 1,1,1,1,1,0,1,1 -> max bits = 8 for (5,5)

 * @author nishant_thorat
 *
 */
public class FlipBitsToGetMaxOnesDriver {

	public static void main(String[] args) {
		
		flipForMaxOnes(new int[]{1,0,0,1,0,0,1,0});
		flipForMaxOnes(new int[]{1,1,1,1,1,0,1,1});
		flipForMaxOnes(new int[]{1,0,0,0,0,0,0,0});
		flipForMaxOnes(new int[]{0,0,0,0,1,0,0,0});
		flipForMaxOnes(new int[]{1,1,1,1,1,1,1,0});
		flipForMaxOnes(new int[]{1,1,1,1,1,1,1,1}); // corner case
		flipForMaxOnes(new int[]{0,0,0,0,0,0,0,0}); // corner case
		flipForMaxOnes(new int[]{0,0,0,1});
	}
	
	/**
	 * Dynamic Programming Algorithm: 
	 * @param arr
	 */
	static void flipForMaxOnes(int[] arr){
		// define a matrix one length X length
		int[][] matrix = new int[arr.length][arr.length];
		
		int countOneBit = 0;
		for (int i=0, j=0; i < arr.length; i++,j++){
			countOneBit += arr[i];
			matrix[i][j] = countOneBit;
		}
		
		int totalNumberOfOneBits = matrix[arr.length-1][arr.length-1];
		System.out.println("Original Number of one bits : " + totalNumberOfOneBits);
		
		// corner case of all ones
		if (totalNumberOfOneBits == arr.length){
			System.out.println("Max Bits :" + totalNumberOfOneBits + " Flip not required..");
			return;
		}
		
		int max = Integer.MIN_VALUE;
		int maxOnesRow = Integer.MIN_VALUE;
		int maxOnesCol = Integer.MIN_VALUE;
		int bitsAtThisPosWhenFliped = 0;
		// total bits + flip(x) - (0,0), (1,2)...(7,7)...(n-1)(n-1)
		for (int row=0, col=0; row < arr.length; row++,col++){
			matrix[row][col] =  bitsAtThisPosWhenFliped = totalNumberOfOneBits + flip(arr[col]);
			
			if (bitsAtThisPosWhenFliped > max){
				max = bitsAtThisPosWhenFliped;
				maxOnesRow = row; maxOnesCol = col;
			}
		}
		
		// start at i,j position and run till array end
		// noof bits at this pos = prevpos bits + flip(x)
		
		
		bitsAtThisPosWhenFliped = 0;
		for (int row = 0; row < arr.length; row++){
			for (int col = row + 1; col < arr.length; col++ ){
				matrix[row][col] =bitsAtThisPosWhenFliped = matrix[row][col-1] + flip(arr[col]);
				 
				if (bitsAtThisPosWhenFliped > max){
					max = bitsAtThisPosWhenFliped;
					maxOnesRow = row; maxOnesCol = col;
				}
			}
		}
		
		System.out.println("Max Bits :" + max + " Flip between (" + maxOnesRow + ", " + maxOnesCol +")");
		
	}
	
	static int flip(int x){
		if (x == 0){
			return 1;
		}
		
		return -1;
	}

}
