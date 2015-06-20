
public class MergeSort {

	public static void main(String[] args) {
		int[] ar = {78,4,89,67,45,3,1,999,1,45,3,7};
		printArray(ar);
		mergeSort(ar, 0, ar.length-1);
		printArray(ar);
	}

	public static void mergeSort(int[] ar, int lo, int hi){
		// prepare two arrays from input array by halving
		
		if (lo < hi){
			int mid = (lo+hi+1)/2;
			int[] left = new int[mid-lo];
			int[] right = new int[hi-mid+1];
			
			// now copy elements from original input into these partitions
			for(int i=lo; i < mid;i++){
				left[i-lo] = ar[i];
			}
			
			for(int j=mid; j <= hi;j++){
				right[j-mid] = ar[j];
			}
			
			// now call recursively on two halves and merge partitions
			mergeSort(left, 0, mid-lo-1);
			mergeSort(right,0, hi-mid);
			mergeArrays(ar, left, right, lo, hi);
		}
	}
	
	public static void mergeArrays(int[]ar, int[] left, int[] right, int lo, int hi){
		// now pick up least element from left/right partition and place into original array
		int i,j,k;
		int mid = (lo+hi+1)/2;
		i = 0; j = 0; k = lo;
		while(i <(mid - lo) && j <= (hi-mid)){
			if (left[i] < right[j]){
				ar[k++] = left[i++];
			}else{
				ar[k++] = right[j++];
			}
		}
		
		// copy any left-overs
		// check if any in right
		if (i == (mid-lo) && j <= (hi-mid)){
			while(j <= (hi-mid)){
				ar[k++] = right[j++];
			}
		}else {
			while(i < (mid-lo)){
				ar[k++] = left[i++];
			}
		}
	}
	
	private static void printArray(int[] ar) {
	      for(int n: ar){
	         System.out.print(n+" ");
	      }
	        System.out.println("");
	}
	    
}
