
public class BSTArrayDemo {

	public static void main(String[] args) {
		BSTArrayDemo demo = new BSTArrayDemo();
		System.out.println(demo.searchFor(new int[] {2, 3, 4, 6}, 4));
	}

	
	private int searchFor(int[] sequence, int searchFor){
		if (sequence == null || sequence.length == 0){
			return -1;
		}
		
		
		int pos = -1;
		
		int low = 0;
		int hi  = sequence.length-1;
		while (low <= hi){
			// get the mid
			int mid = (int) Math.ceil((low+hi)/2);
			
			if (sequence[mid] == searchFor){
				return mid;
			}
			
			if (sequence[mid] > searchFor){ // its is low range
				hi = mid-1;
			}else{
				low = mid+1;
			}
		}
		return pos;
	}
}
