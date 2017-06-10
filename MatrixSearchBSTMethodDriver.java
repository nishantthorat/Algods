import java.util.ArrayList;

// matrix of m x n size is given find if number x is in this matrix or not.
// first number of each row is always greater than last number in previous row and each row is sorted from left to right.
public class MatrixSearchBSTMethodDriver {

	public static void main(String[] args) {
		int a[][] = {
				{Integer.MIN_VALUE, Integer.MIN_VALUE + 1, 3},
				{4, 5, 6},
				{6, 7, 9},
				{10, 11, 15},
				{87, 99, 345},
				{500, 500, Integer.MAX_VALUE}
		};
		
		ArrayList<ArrayList<Integer>> ar = new ArrayList<ArrayList<Integer>>();
		fillArrayListOfArrayList(ar, a);
		//int[][] x = convertArrayListOfArrayList(ar);
		if (0 != searchRow(ar, Integer.MIN_VALUE+1)){
			System.out.println("Found Value in Matrix");
		}else{
			System.out.println("Value Not Found in Matrix");
		}
	}
	
	private static void fillArrayListOfArrayList(ArrayList<ArrayList<Integer>> ar, int[][] a){
		for (int row = 0; row < a.length; row++){
			ArrayList<Integer> intRow = new ArrayList<>();
			for (int col = 0; col < a[row].length; col++){
				intRow.add(a[row][col]);
			}
			
			ar.add(intRow);
		}
	}
	
	private static int[][] convertArrayListOfArrayList(ArrayList<ArrayList<Integer>> a){
		int[][] array = new int[a.size()][a.get(0).size()];
		
		int row = 0;
		int col = 0;
		for (ArrayList<Integer> al : a){
			col = 0;
			for (int i : al){
				array[row][col++] = i;
			}
			row++;
		}
		
		return array;
	}

	private static int searchRow(ArrayList<ArrayList<Integer>> array, int n ){
		int rows = array.size() - 1;
		int cols = array.get(0).size()- 1; // In java columns may vary
		
		int lo = 0;
		int hi = rows;
		int mid = rows;
		int ans = Integer.MIN_VALUE;
		
		System.out.println("Rows :" + rows);
		System.out.println("Columns : " + cols);
		System.out.println("Lo: " + lo + ", Hi: " + hi);
		
		while (lo <= hi){
			mid = (lo + hi)/2;
			System.out.println("Lo: " + lo + ", Hi: " + hi + ", Mid: " + mid);
			
			int lowRow = array.get(mid).get(0);
			int hiRow = array.get(mid).get(cols);
			
			System.out.println("LowRow : " + lowRow + " HiRow: " + hiRow);
			if (n >= lowRow && n<= hiRow){
				// search in this row
				//findInRow(mid, n);
				//return 1;
				ans = mid;
				System.out.println("Possible row : " + mid);
				break;
			}
			
			if (n < lowRow){
				hi = mid - 1;
			}else if ( n > hiRow){
				lo = mid + 1;
			}
		}
		
		if (ans != Integer.MIN_VALUE){
			return findInRow(array, ans, n);
		}
		return 0;
	}
	
	/**
	 * In this function we are trying to find out position/index in array with BST. a
	 * @param array
	 * @param row
	 * @param n
	 * @return
	 */
	static int findInRow(ArrayList<ArrayList<Integer>> array, int row, int n){
		int lo = 0;
		int hi = array.get(row).size() -1; // col size for this row
		int ans = Integer.MIN_VALUE;
		int mid = hi;
		while (lo <= hi){
			mid = (lo + hi)/ 2;
			
			if (n == array.get(row).get(mid)){
				ans = mid;
				System.out.println("Row: " + row + ", col: " + mid + ", Value : " + array.get(row).get(mid));
				break;
			}
			
			if (n < array.get(row).get(mid)){
				hi = mid-1;
			} else {
				lo = mid + 1;
			}
		}
		
		if (ans != Integer.MIN_VALUE){
			return 1;
		}
		
		return 0;
	}
}
