/***
 * In a m x n matrix, each row is filled with 0's, 1's or both, either in ascending or descending order. Find row with max 1's.
 * Interview @ Druva
 * @author nishant_thorat
 *
 */
public class FindMax1sRowInMatrixDriver {

	public static void main(String[] args) {
		int[][] matrixAsc = {
				{0,0,0,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
				{0,0,1,1,1,1,1},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
		System.out.println("Max 1's row : " + findMax1sInMatrix(matrixAsc));
	}
	
	private static int findMax1sInMatrix(int[][] arr){
		int max = Integer.MIN_VALUE;
		int rowNumWithMax1s = -1;
		for (int i = 0; i< arr.length; i++){
			int row1s = findNumberof1sInRow(arr[i]);
			if (row1s > max){
				max = row1s;
				rowNumWithMax1s = i;
			}
		}
		return rowNumWithMax1s;
	}
	
	private static int findNumberof1sInRow(int[] arr){
		
		if (null == arr || 0 == arr.length){
			return Integer.MIN_VALUE;
		}
		
		int lo = 0;
		int hi = arr.length-1;
		
		if (arr[lo] == arr[hi]){
			return arr[lo] == 0 ? 0 : arr.length; 
		}else {
			int mid = 0;
			boolean asc = arr[lo] == 0 && arr[hi] == 1;
			
			while (lo < hi){
				mid = (lo + hi) / 2;
				
				if (asc ? arr[mid] == 1 : arr[mid] == 0){
					hi = mid;
				}else {
					lo = mid + 1;
				}
			}
			
			// now we got starting position for 1
			return asc ? arr.length - lo : hi;
		}
	}

}
