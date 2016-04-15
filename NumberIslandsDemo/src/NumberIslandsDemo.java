

public class NumberIslandsDemo {
	private int matrix[][] = {
			{1, 1, 0, 1, 0, 0, 0, 1},
			{0, 0, 1, 0, 1, 1, 0, 0},
			{1, 0, 0, 1, 0 ,0, 0, 1},
			{0, 0, 0, 0, 0, 1, 1, 0},
			{0, 1, 0, 1, 0, 0, 0, 1}
	};
	
	private static int ROWS = 5;
	private static int COLS = 8;
	
	private int islands = 0;
	
	class RowCol{
		public RowCol(int row, int col) {
			this.row = row;
			this.col = col;
		}
		int row;
		int col;
		
	}
	
	private boolean visited[][] = new boolean[ROWS][COLS];
	
	private void printMatrix(){
		if (null == matrix){
			return;
		}
		for (int row = 0; row < ROWS;  row++){
			for (int col = 0; col < COLS; col++){
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println("");
		}
	}
	
	private void printVisitedMatrix(){
		if (null == visited){
			return;
		}
		for (int row = 0; row < ROWS;  row++){
			for (int col = 0; col < COLS; col++){
				System.out.print(visited[row][col] + " ");
			}
			System.out.println("");
		}
	}
	private void calNumberIslands(){
		// start from each node and see if its visited or not - if not do a DFS and keep doing it until we keep reaching 
		// a node which is having value as 1 and which is not visited
		for(int row = 0; row < ROWS;  row++){
			for (int col = 0; col < COLS; col++){
				if (!visited[row][col] && matrix[row][col] == 1){
					visited[row][col] = true;
					doDFS(row, col);
					// we are end of the island
					islands++;
				}
			}
		}
		
		System.out.println("No of islands: " + islands);
	}
	
	private boolean hasLeft(int col){
		if (col-1 >= 0){
			return true;
		}
		return false;
	}
	
	private boolean hasRight(int col){
		if (col+1 < COLS){
			return true;
		}
		return false;
	}
	
	private boolean hasUp(int row){
		if (row - 1 >=0){
			return true;
		}
		return false;
	}
	
	private boolean hasDown(int row){
		if (row + 1 < ROWS){
			return true;
		}
		return false;
	}
	
	private boolean hasLeftDown(int rowCurrent, int colCurrent) {
		if (hasLeft(colCurrent) && hasDown(rowCurrent)){
			return true;
		}
		return false;
	}

	private boolean hasLeftUp(int rowCurrent, int colCurrent) {
		if (hasLeft(colCurrent) && hasUp(rowCurrent)){
			return true;
		}
		return false;
	}

	private boolean hasRightDown(int rowCurrent, int colCurrent) {
		if (hasRight(colCurrent) && hasDown(rowCurrent)){
			return true;
		}
		return false;
	}

	private boolean hasRightUp(int rowCurrent, int colCurrent) {
		if (hasRight(colCurrent) && hasUp(rowCurrent)){
			return true;
		}
		return false;
	}
	
	private boolean isVisited(int row, int col){
		return visited[row][col];
	}
	
	private boolean hasOne(int row, int col){
		return matrix[row][col] == 1;
	}
	
	private RowCol advanceToNextMove(int rowCurrent, int colCurrent){
		if (hasRight(colCurrent) && hasOne(rowCurrent, colCurrent+1) && !isVisited(rowCurrent, colCurrent+1)){
			return new RowCol(rowCurrent, colCurrent + 1);
		}else if (hasLeft(colCurrent) && hasOne(rowCurrent, colCurrent-1) && !isVisited(rowCurrent, colCurrent-1)){
			return new RowCol(rowCurrent, colCurrent -1);
		}else if (hasUp(rowCurrent) && hasOne(rowCurrent-1, colCurrent) && !isVisited(rowCurrent-1, colCurrent)){
			return new RowCol(rowCurrent-1, colCurrent);
		}else if (hasDown(rowCurrent) && hasOne(rowCurrent+1, colCurrent) && !isVisited(rowCurrent+1, colCurrent)){
			return new RowCol(rowCurrent+1, colCurrent);
		}else if (hasRightUp(rowCurrent, colCurrent) && hasOne(rowCurrent-1, colCurrent+1) && !isVisited(rowCurrent-1, colCurrent+1)){
			return new RowCol(rowCurrent-1, colCurrent+1);
		}else if (hasRightDown(rowCurrent, colCurrent) && hasOne(rowCurrent+1, colCurrent+1) && !isVisited(rowCurrent+1, colCurrent+1)){
			return new RowCol(rowCurrent+1, colCurrent+1);
		}else if (hasLeftUp(rowCurrent, colCurrent) && hasOne(rowCurrent-1, colCurrent-1) && !isVisited(rowCurrent-1, colCurrent-1)){
			return new RowCol(rowCurrent-1, colCurrent-1);
		}else if (hasLeftDown(rowCurrent, colCurrent) && hasOne(rowCurrent+1, colCurrent-1) && !isVisited(rowCurrent+1, colCurrent-1)){
			return new RowCol(rowCurrent+1, colCurrent-1);
		}
		return null;
	}

	

	private void doDFS(int row, int col){
		// check around
	
		for (int i = 0 ; i < 8; i++){
			RowCol nextRC = advanceToNextMove(row, col);
			if (null != nextRC && !visited[nextRC.row][nextRC.col] && matrix[nextRC.row][nextRC.col] == 1){
				// mark it as visited
				visited[nextRC.row][nextRC.col] = true;
				doDFS(nextRC.row, nextRC.col);
			}
		}
	}
	
	public static void main(String[] args){
		NumberIslandsDemo demo = new NumberIslandsDemo();
		demo.printMatrix();
		demo.calNumberIslands();
		//demo.printVisitedMatrix();
	}
	
	
}
