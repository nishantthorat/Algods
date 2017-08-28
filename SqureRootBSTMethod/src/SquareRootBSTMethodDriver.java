/**
 * Square root of number n will always between [1,n], we need to start midway and find a number x for which x*x = n
 * @author nishant_thorat
 *
 */
public class SquareRootBSTMethodDriver {

	public static void main(String[] args) {
		
		System.out.println("SquareRoot of :" + sqrtBST(0));
	}
	
	private static long sqrtBST(int n){
		int low = 1;
		int hi = n;
		int mid = 0;
		int ans = n;
		while (low < hi){
			mid = (low + hi)/2;
			System.out.println("low = " + low + ", mid = " + mid + ", hi = " + hi);
			//sqr = mid * mid = n
				
			if (mid <= (n / mid)){
				ans = mid;
				low = mid + 1;
			}else{
				hi = mid;
			}
		}
		
		return ans;
	}

}
