/*
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the minimum starting gas station’s index if you can travel around the circuit once, otherwise return -1.

You can only travel in one direction. i to i+1, i+2, ... n-1, 0, 1, 2..
Completing the circuit means starting at i and ending up at i again.
Example :

Input :
      Gas :   [1, 2]
      Cost :  [2, 1]

Output : 1 

If you start from index 0, you can fill in gas[0] = 1 amount of gas. Now your tank has 1 unit of gas. But you need cost[0] = 2 gas to travel to station 1. 
If you start from index 1, you can fill in gas[1] = 2 amount of gas. Now your tank has 2 units of gas. You need cost[1] = 1 gas to get to station 0. So, you travel to station 0 and still have 1 unit of gas left over. You fill in gas[0] = 1 unit of additional gas, making your current gas = 2. It costs you cost[0] = 2 to get to station 1, which you do and complete the circuit. 

 */
public class GasStationDriver {

	public static void main(String[] args) {
		System.out.println(getMinStartIndex(new int[]{1,2}, new int[]{2,1}));
		System.out.println(getMinStartIndex(new int[]{1,5, 4}, new int[]{5,4,1}));
		int gas[] = {204, 918, 18, 17, 35, 739, 913, 14, 76, 555, 333, 535, 653, 667, 52, 987, 422, 553, 599, 765, 494, 298, 16, 285, 272, 485, 989, 627, 422, 399, 258, 959, 475, 983, 535, 699, 663, 152, 606, 406, 173, 671, 559, 594, 531, 824, 898, 884, 491, 193, 315, 652, 799, 979, 890, 916, 331, 77, 650, 996, 367, 86, 767, 542, 858, 796, 264, 64, 513, 955, 669, 694, 382, 711, 710, 962, 854, 784, 299, 606, 655, 517, 376, 764, 998, 244, 896, 725, 218, 663, 965, 660, 803, 881, 482, 505, 336, 279};
		int cost[] = {273, 790, 131, 367, 914, 140, 727, 41, 628, 594, 725, 289, 205, 496, 290, 743, 363, 412, 644, 232, 173, 8, 787, 673, 798, 938, 510, 832, 495, 866, 628, 184, 654, 296, 734, 587, 142, 350, 870, 583, 825, 511, 184, 770, 173, 486, 41, 681, 82, 532, 570, 71, 934, 56, 524, 432, 307, 796, 622, 640, 705, 498, 109, 519, 616, 875, 895, 244, 688, 283, 49, 946, 313, 717, 819, 427, 845, 514, 809, 422, 233, 753, 176, 35, 76, 968, 836, 876, 551, 398, 12, 151, 910, 606, 932, 580, 795, 187};
		
		System.out.println(getMinStartIndex(gas, cost));

	}
	
	static int getMinStartIndex(int[] arrGas, int[] arrCost){
		
		for (int i= 0; i <= arrGas.length;i++){
			
			int startIndex = i;
			
			int currentIndex = startIndex;
			
			if (currentIndex == arrGas.length){
				currentIndex = 0;
				startIndex = 0;
			}
			boolean hasNext = true;
			int currentGas = 0;
			
			while(hasNext){
				boolean positiveSide = false;
				
				currentGas = (currentGas + arrGas[currentIndex] - arrCost[currentIndex]);
				
				if (currentGas < 0){
					break;
				}
				
				currentIndex++;
				if (currentIndex == arrGas.length){
					currentIndex = 0;
				}
				
				if (currentIndex > startIndex){
					positiveSide = true;
				}
			
				hasNext = positiveSide ? currentIndex > startIndex && currentIndex <= arrGas.length : currentIndex >= 0 && currentIndex < startIndex;
			}
			System.out.println("Current Gas: " + currentGas);
			if (currentGas >= 0){
				return startIndex;
			}
		}
		return -1;
	}

}
