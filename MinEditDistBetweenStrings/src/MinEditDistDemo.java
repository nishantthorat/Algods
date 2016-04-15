
public class MinEditDistDemo {

	private int distMatrix[][];
	
	public static void main(String[] args) {
		MinEditDistDemo demo = new MinEditDistDemo();
		System.out.println("Min Dist: " + demo.calcMinEditDistance("Nishant", "nishant.thorat"));
		
	}
	
	private void initializeDistMatrix(int n1, int n2){
		for (int i = 0; i < n2; i++){
			distMatrix[0][i] = i;
		}
		
		for (int j=0; j < n1; j++){
			distMatrix[j][0] = j;
		}
	}
	
	private void printDistMatrix(int n1, int n2){
		for (int row=0; row < n2; row++){
			for(int col = 0; col < n1; col++){
				System.out.print(distMatrix[row][col] + " ");
			}
			System.out.println("");
		}
	}
	
	private int calcMinEditDistance(String str1, String str2){
		if (null == str1 || null == str2){
			return 0;
		}
		
		if (str1.isEmpty()){
			return str2.length();
		}
		
		if (str2.isEmpty()){
			return str1.length();
		}
		
		distMatrix = new int[str2.length() + 1][str1.length() + 1];
		System.out.println("Size: " + (str2.length() + 1) + " , " + (str1.length() + 1));
		//printDistMatrix(str2.length() + 1, str1.length() + 1);
		initializeDistMatrix(str2.length() + 1, str1.length() + 1);
		
		// calculate
		for (int row = 1; row < str2.length() + 1; row++){
			for (int col = 1; col < str1.length() + 1; col++){
				int minDist = 0;
				if (str1.charAt(col-1) == str2.charAt(row-1)){
					// if both are equal - 
					minDist = distMatrix[row-1][col-1];
				}else{
					// we have three choices - we need a min of the cost
					// insertion   - row-1, col
					// deletion    - row, col-1
					// replacement - row-1, col-1
					minDist = Math.min(distMatrix[row-1][col]+1, distMatrix[row][col-1]+1);
					minDist = Math.min(minDist, distMatrix[row-1][col-1]+1);
				}
				
				// update distance matrix
				distMatrix[row][col] = minDist;
			}
		}
		printDistMatrix(str1.length() + 1, str2.length() + 1);
		return distMatrix[str2.length()][str1.length()];
	}

}
