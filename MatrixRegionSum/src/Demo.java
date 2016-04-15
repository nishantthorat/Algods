// Refer : http://www.ardendertat.com/2011/09/20/programming-interview-questions-2-matrix-region-sum/
public class Demo {
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		int matrix[][] = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		};
		int [][] preComputedSumMatrix = demo.precomputeSums(matrix, 3, 3);
		System.out.println("Sum for this matrix: " + demo.getRectangeSum(preComputedSumMatrix, 1, 1, 2, 2));

	}

	private int getRectangeSum(int[][] preComputedSumMatrix, int topRow, int topLeftCol, int bottomRow, int bottomRightCol){
		int sum = 0;
		
		
		// sum (ABCD) = Sum (OD) - Sum(OB) - Sum(OC) + Sum(OA)
		// Refer : http://www.ardendertat.com/2011/09/20/programming-interview-questions-2-matrix-region-sum/
		int OA = 0;
		if (topRow == 0 || topLeftCol == 0){
			OA = 0;
		}else{
			OA = preComputedSumMatrix[topRow -1][topLeftCol -1];
		}
		
		// calc OC 
		int OC = preComputedSumMatrix[bottomRow][topLeftCol-1];
		int OB = preComputedSumMatrix[topRow-1][bottomRightCol];
		int OD = preComputedSumMatrix[bottomRow][bottomRightCol];
		
		sum = OD - OB - OC + OA;
		return sum;
	}
	
	private int[][] precomputeSums(int[][] matrix, int rows, int cols){
		if (null == matrix || rows <=0 || cols <= 0){
			return null;
		}
		
		int [][]precomputeMatrix = new int[rows][cols];
		
		precomputeMatrix[0][0] = matrix[0][0];
		// initialize the rows with computed sum
		for (int col = 1; col < cols; col++){
			precomputeMatrix[0][col] = precomputeMatrix[0][col-1] + matrix[0][col];
		}
		
		for (int row = 1; row < rows; row++){
			precomputeMatrix[row][0] = precomputeMatrix[row-1][0] + matrix[row][0];
		}
		
		for (int row=1; row < rows; row++){
			for (int col = 1; col < cols; col++){
				precomputeMatrix[row][col] = precomputeMatrix[row][col-1] + precomputeMatrix[row-1][col] - precomputeMatrix[row-1][col-1] + matrix[row][col]; 
			}
		}
		return precomputeMatrix;
	}
}
