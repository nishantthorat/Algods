
public class Demo {
	private int[][] matrix = {
			{1, 2, 3, 4, 5},
			{6, 7, 8, 9, 10},
			{11, 12, 13, 14, 15},
			{16, 17, 18, 19, 20}
	};
	
	private void printMatrix(){
		for (int row = 0; row < 4; row++){
			for (int col = 0; col < 5; col++){
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println("");
		}
		
	}
	
	private void printDiagonal(){
		// maintain  variables which control from where to begin and end for row and columns
		int rowStart = 0, rowEnd = 3;
		int colStart = 0, colEnd = 4;
		
		// print column first - then row
		// once done rowStart to be incremented and rowEnd to be decremented
		//           colStart to be incremented and colEnd to be decremented
		
		// this has be done until rowStart and colStart past ends
		while(rowStart <= rowEnd && colStart <= colEnd ){
			
			printThisColumnFromStartToEndRow(colStart, rowStart, rowEnd);
			colStart++;
			printThisRowFromStartToEndColumn(rowEnd, colStart, colEnd);
			rowEnd--;
		}
	}
	private void printThisColumnFromStartToEndRow(int colIndex, int startRow, int endRow){
		for (int i = startRow; i <= endRow; i++){
			System.out.print(matrix[i][colIndex] + " ");
		}
	}
	
	private void printThisRowFromStartToEndColumn(int rowIndex, int startCol, int endCol){
		for (int i= startCol; i <= endCol; i++){
			System.out.print(matrix[rowIndex][i] + " ");
		}
	}
	
	


	public static void main(String[] args) {
		Demo demo = new Demo();
		//demo.printMatrix();
		//demo.printRow(0, 0, 4);
		//demo.printCol(1, 1, 1);
		//demo.printThisColumnFromStartToEndRow(1, 0, 4);
		//demo.printThisRowFromStartToEndColumn(1, 1, 4);
		demo.printDiagonal();
	}

}
