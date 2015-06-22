package com.demo;
/*
 * You are given a matrix with m rows and n columns of cells, each of which contains either 1 or 0. 
 * Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally. 
 * The connected and filled (i.e. cells that contain a 1) cells form a region. 
 * There may be several regions in the matrix. Find the number of cells in the largest region in the matrix.

	Input Format
	--------------
	~There will be three parts of t input:
	~The first line will contain m, the number of rows in the matrix.
	~The second line will contain n, the number of columns in the matrix.
	~This will be followed by the matrix grid: the list of numbers that make up the matrix.

	Output Format
	--------------
	~Print the length of the largest region in the given matrix.

Constraints
0<m<10
0<n<10
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	private static class Node{
		private int row;
		private int col;
		private int value;
		private boolean vistited;
		
		public Node(int row, int col, int val){
			this.row = row;
			this.col = col;
			this.value = val;
		}

		public Node(Node node) {
			this.row = node.row;
			this.col = node.col;
			this.value = node.value;
			this.vistited = node.vistited;
		}
	};
	
	public static void main(String[] args) {
		  Scanner in = new Scanner(System.in);
	      int rows = in.nextInt();
	      int columns = in.nextInt();

	      
	      Node[][] matrix = new Node[rows][columns];
	      
	      for(int iRow = 0; iRow < rows; iRow++){
	    	  for(int iCol = 0; iCol < columns; iCol++){
	    		  int value = in.nextInt();
	    		  
	    		  if (value == 1){
	    			  /*String nodeId = String.valueOf(iRow) + String.valueOf(iCol);
	    			  Node node = new Node(nodeId);
	    			  mapNodes.put(nodeId, node);*/
	    		  }
	    		  
	    		  matrix[iRow][iCol] = new Node(iRow, iCol, value);	    		  
	    	  }
	      }
	      
	      int longest = findLongestRegionLength(matrix, rows, columns);
	      System.out.println(longest);
	}

	private static int findLongestRegionLength(Node[][] m, int rows, int Columns) {
		int longest = 0;
		Map<String, Boolean> processedNodes = new HashMap<String, Boolean>();
		for(int iRow = 0; iRow < rows; iRow++){
			for(int iCol = 0; iCol < Columns; iCol++){
				int lengthSoFar = 0;
				Stack<Node> nodesToProcess = new Stack<Node>();
			    // get start node
				Node[][] matrix = m.clone();
				
				Node startNode = null;
				if (matrix[iRow][iCol].value == 1 && !matrix[iRow][iCol].vistited){
					startNode =  matrix[iRow][iCol];
					startNode.vistited = true;
					lengthSoFar++;
					nodesToProcess.push(startNode);
				}else{
					continue;
				}
				
				
				while(!nodesToProcess.isEmpty()){
					Node node = nodesToProcess.pop();
					int r = node.row;
					int c = node.col;
					if (c + 1 < Columns){ // next
						if (matrix[r][c+1].value == 1 && !matrix[r][c+1].vistited){
							lengthSoFar ++;
							matrix[r][c+1].vistited = true;
							// push the next node
							nodesToProcess.push(matrix[r][c+1]);
						}
					}
					if (c - 1 >= 0 ){ // back
						if (matrix[r][c - 1].value == 1 && !matrix[r][c - 1].vistited){
							lengthSoFar ++;
							matrix[r][c - 1].vistited = true;
							// push the next node
							nodesToProcess.push(matrix[r][c - 1]);
						}
					}	
					
					if (r + 1 < rows){ // down
						if (matrix[r+1][c].value == 1 && !matrix[r+1][c].vistited){
							lengthSoFar ++;
							matrix[r+1][c].vistited = true;
							// push the next node
							nodesToProcess.push(matrix[r+1][c]);
						}
					}
					
					if (r - 1 >= 0){ // up
						if (matrix[r - 1][c].value == 1 && !matrix[r - 1][c].vistited){
							lengthSoFar ++;
							matrix[r - 1][c].vistited = true;
							// push the next node
							nodesToProcess.push(matrix[r - 1][c]);
						}
					}
					
					if (c + 1 < Columns && r + 1 < rows){ // next down diagonal
						if (matrix[r+1][c+1].value == 1 && !matrix[r+1][c+1].vistited){
							lengthSoFar ++;
							matrix[r+1][c+1].vistited = true;
							// push the next node
							nodesToProcess.push(matrix[r+1][c+1]);
						}
					}		
					
					if (c + 1 < Columns && r - 1 >= 0){ // next up diagonal 
						if (matrix[r-1][c+1].value == 1 && !matrix[r-1][c+1].vistited){
							lengthSoFar ++;
							matrix[r-1][c+1].vistited = true;
							// push the next node
							nodesToProcess.push(matrix[r-1][c+1]);
						}
					}
					

					if (c - 1 >= 0 && r - 1 >= 0){ // previous up diagonal 
						if (matrix[r-1][c-1].value == 1 && !matrix[r-1][c-1].vistited){
							lengthSoFar ++;
							matrix[r-1][c-1].vistited = true;
							// push the next node
							nodesToProcess.push(matrix[r-1][c-1]);
						}
					}
				
					if (c - 1 >=0 && r + 1 < rows){ // next down diagonal
						if (matrix[r+1][c-1].value == 1 && !matrix[r+1][c-1].vistited){
							lengthSoFar ++;
							matrix[r+1][c-1].vistited = true;
							// push the next node
							nodesToProcess.push(matrix[r+1][c-1]);
						}
					}	
					
					
					if (lengthSoFar > longest){
						longest = lengthSoFar;
					}
				}				
			}			
		}	
		
		return longest ;
	}


}