// Please see https://medium.com/@bearsandsharks/i-failed-a-twitter-interview-52062fbb534b
// https://gist.github.com/mkozakov/59af0fd5bddbed1a0399
public class Solution {

	//solution is basically to find the two tallest towers 
	// that are furthest left and furthest right of the diagram, and then calculate the volume between them.
	public static void main(String[] args) {
		
		int towers[] = {3, 4, 2, 5};
		int towers1[] =  {6, 2, 5, 4, 5, 2, 6};
		int towers2[] = {2,5,1,3,1,2,1,7,7,6};
		System.out.println(Solution.calcVaolume(towers));
		System.out.println(Solution.calcVaolume(towers1));
		System.out.println(Solution.calcVaolume(towers2));
	}
	
	public static int calcVaolume(int[] towerHeights){
		int left  = 0;
		int right = towerHeights.length -1;
		int volume = 0, leftMax = 0,  rightMax = 0;
		
		while(left < right){
			// find maximum height at left side till now
			if (towerHeights[left] > leftMax){
				leftMax = towerHeights[left];
			}
			
			// find maximum tower height at left side till now
			if(towerHeights[right] > rightMax){
				rightMax = towerHeights[right];
			}
			
			if (leftMax >= rightMax){
				volume += rightMax - towerHeights[right--];
			}else{
				volume += leftMax - towerHeights[left++];
			}
		}
		
		return volume;
	}

}
