
public class SegmentTree {

	public static void main(String[] args) {
		SegmentTree.buildSumSegmentTree(new int[]{1, 3, 5, 7, 9, 11});
		SegmentTree.buildRMQSegmentTree(new int[]{1, 3, 2, 7, 9, 11});
	}

	public static void buildSumSegmentTree(int[] input){
		// create segment tree with size estimated below
		//
		int[] segment = new int[getSegmentTreeSize(input.length)];
		
		buildSumRecursivelyST(input, segment, 0, 0, input.length-1);
		printArray(segment);
		System.out.println(getSumQuery(segment, 0, 0, input.length - 1, 0, 1));
	}
	
	public static void buildRMQSegmentTree(int[] input){
		// create segment tree with size estimated below
		//
		int[] segment = new int[getSegmentTreeSize(input.length)];
		
		buildRMQRecursivelyST(input, segment, 0, 0, input.length-1);
		printArray(segment);
		System.out.println(getRMQQuery(segment, 0, 0, input.length - 1, 1, 5));
	}
	
	private static int buildSumRecursivelyST(int[] input, int[] segment, int segmentIndex, int left, int right){
		if (left == right){
			segment[segmentIndex] = input[left];
			return segment[segmentIndex];
		}
		
		
		int mid = left + (right - left)/2;
		
		return segment[segmentIndex] =  buildSumRecursivelyST(input, segment, (segmentIndex * 2 ) + 1, left, mid) + 
				buildSumRecursivelyST(input, segment, (segmentIndex * 2 ) + 2,  mid + 1, right);
	}
	
	private static int buildRMQRecursivelyST(int[] input, int[] segment, int segmentIndex, int left, int right){
		if (left == right){
			segment[segmentIndex] = input[left];
			return segment[segmentIndex];
		}
		
		int mid = left + (right - left)/2;
		
		return segment[segmentIndex] =  Math.min( buildRMQRecursivelyST(input, segment, (segmentIndex * 2 ) + 1, left, mid) , 
										buildRMQRecursivelyST(input, segment, (segmentIndex * 2 ) + 2,  mid + 1, right));
	}
	
	public static int getSumQuery(int[] segment, int segmentIndex, int left, int right, int queryLeft, int queryRight){
		if (queryLeft <= left && queryRight >= right){
			return segment[segmentIndex];
		}
		
		if (right < queryLeft || left > queryRight)
	        return 0;
		
		int mid = left + (right - left)/2;
		
		return getSumQuery(segment, (segmentIndex * 2) + 1 , left, mid, queryLeft, queryRight) + 
				getSumQuery(segment, (segmentIndex * 2) + 2 ,  mid + 1, right, queryLeft, queryRight)	;
	}
	
	public static int getRMQQuery(int[] segment, int segmentIndex, int left, int right, int queryLeft, int queryRight){
		if (queryLeft <= left && queryRight >= right){
			return segment[segmentIndex];
		}
		
		if (right < queryLeft || left > queryRight)
	        return Integer.MAX_VALUE;
		
		int mid = left + (right - left)/2;
		
		return Math.min(getRMQQuery(segment, (segmentIndex * 2) + 1 , left, mid, queryLeft, queryRight) ,
				getRMQQuery(segment, (segmentIndex * 2) + 2 ,  mid + 1, right, queryLeft, queryRight))	;
	}
	
	private static void printArray(int[] ar) {
	      for(int n: ar){
	         System.out.print(n+" ");
	      }
	        System.out.println("");
	}
	
	private static int getSegmentTreeSize(int N) {
		int size = 1;
		for (; size < N; size <<= 1);
		return size << 1;
	}
	
	
}
