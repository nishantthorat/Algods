/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is 
 * that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into 
 * on the same night.

 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money 
 * you can rob tonight without alerting the police.
 * 
 * 3 5 4 2 1 3 2 1
 * 
 * [3 4 1 2] [5 2 3 1]
 * 10         11
 * 
 * 1 0 0 1 0 0 
 * [1 1] [0 0 0 0] ==> and hence DP
 * @author nishant_thorat
 *
 */
public class HouseRobberDriver {

	public static void main(String[] args) {
		System.out.println(getMaxLoot(new int[]{3,5,4,2,1,3,2,1}));
		System.out.println(getMaxLoot(new int[]{1,0,0,1,0,0}));
	}
	
	
	static int getMaxLoot(int[] houses){
		// validation
		if (null == houses){
			return 0;
		}
		
		int [] loot = new int[houses.length+1];
		
		if (1 == houses.length){
			return houses[0];
		}
		
		if (2 == houses.length){
			return Math.max(houses[0], houses[1]);
		}
		
		loot[0] = 0;
		loot[1] = houses[0];
		loot[2] = Math.max(houses[1], loot[1]);
		
		for (int house=2; house < houses.length; house++){
			// determine maximum loot possible while standing infront of this house
			// it would be = maximum of loot possible till previous house or (loot from this house + loot possible till houses before previous one)
			loot[house+1] = Math.max(loot[house], houses[house] + loot[house-1]);
			
			
		}
		return loot[houses.length];
	}

}
