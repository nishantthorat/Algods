
public class TraingleMinSumPathTopToBottomDriver {

	public static void main(String[] args) {
		int data[][] = {
				{2},
				{3, 4},
				{6, 5, 7},
				{4, 1, 8, 3}
		};
		
		int data1[][] = {
				{1},
				{1, 2 },
				{7, 2, 1},
				{10, 7, 1, 8}
		};
		System.out.println(getMinSumPathTopBottom(data));
		System.out.println(getMinSumPathTopBottom(data1));

	}
	
	static int getMinSumPathTopBottom(int[][] data){
		int[][] path = new int[data.length][data.length];
		
		for (int row = data.length-1, col = 0; col < path.length; col++){
			path[row][col] = data[row][col]; 
		}
		
		for (int row=data.length-2, cols = data.length-1; row >=0; row--,cols--){
			for (int col=0; col < cols; col++){
				if (col == 0){
					path[row][col] = Math.min(path[row+1][col], path[row+1][col+1]) + data[row][col];
				}else if (col == cols-1){
					path[row][col] = Math.min(path[row+1][col], path[row+1][col-1]) + data[row][col];
				}else{
					path[row][col] = Math.min(Math.min(path[row+1][col-1], path[row+1][col]) , Math.min(path[row+1][col], path[row+1][col+1]))+ data[row][col];
				}
			}
		}
		
		return path[0][0];
	}

}
