import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// http://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/

public class MultilevelLinkedList {

	//private Node head = null;
	private Map<Integer, List<Node>> levelColumnListHead = new HashMap<Integer, List<Node>>();
	private Map<Integer, List<Node>>[] levels = (Map<Integer, List<Node>>[]) new HashMap[10];
	
	public static void main(String[] args) {
		MultilevelLinkedList mll = new MultilevelLinkedList();
		Node head = mll.addNextNode(0, 0, 10);
		mll.addNextNode(0, 0, 5);
		mll.addNextNode(0, 0, 12);
		mll.addNextNode(0, 0, 7);
		mll.addNextNode(0, 0, 11);
		
		mll.addNextNode(1, 0, 4);
		mll.addNextNode(1, 0, 20);
		mll.addNextNode(1, 0, 13);
		mll.addNextNode(1, 1, 17);
		mll.addNextNode(1, 1, 6);

		mll.addNextNode(2, 0, 2);
		mll.addNextNode(2, 1, 16);		
		mll.addNextNode(2, 2, 9);
		mll.addNextNode(2, 2, 8);
		
		mll.addNextNode(3, 0, 3);
		mll.addNextNode(3, 1, 19);
		mll.addNextNode(3, 1, 15);
		
		mll.addChildNode(0, 10, 1, 4);
		mll.addChildNode(0, 7, 1, 17);
		
		mll.addChildNode(1, 20, 2, 2);
		mll.addChildNode(1, 13, 2, 16);
		mll.addChildNode(1, 17, 2, 9);
		
		mll.addChildNode(2, 16, 3, 3);
		mll.addChildNode(2, 9, 3, 19);
		
		
		mll.printLevelOrder(head, null);
	}

	private Node addChildNode(int parentLevel, int nodeWithValue, int childLevel, int newNodeValue){
		Node nodeToAddUnto = findNode(parentLevel, nodeWithValue);
		
		if (nodeToAddUnto != null){
			// find node to add as a child
			Node child = findNode(childLevel, newNodeValue);
			nodeToAddUnto.setChild(child);
			return child;
		}
		
		return null;
	}
	private Node addNextNode(int level, int col, int value){
		// get the head for this list
	
		Map<Integer, List<Node>> levelListHeads = levels[level];
		
		if (null == levelListHeads){
			levelListHeads = new HashMap<Integer, List<Node>>();
			levels[level] = levelListHeads;
		}
		
		// now get the heads list for a specific column list
		List<Node> colListHead = levelListHeads.get(col);
		if (null == colListHead){
			colListHead = new LinkedList<Node>();
			levelListHeads.put(col, colListHead);			
		}
		
		// now structure is ready ..		
		// traverse to the end and find the end of list
		// get the end of list
		Node end = null;
		try{
			end = colListHead.get(colListHead.size()-1);
		}catch(IndexOutOfBoundsException e){
			
		}
		
		// base case
		if (end == null){
			end = new Node(value);
			colListHead.add(end);
			return end;
		}else{
			 Node newNode = end.addNextNode(value);
			 colListHead.add(newNode);
			 return newNode;
		}
		
	}
	
	public void printLevelOrder(Node head, Queue<Node> nodes){
		if (head == null){
			return;
		}
		Node nodeToTraverse = head;
		Queue<Node> thisLevelQueue = new LinkedList<Node>();
		
		while(null != nodeToTraverse){
			
			System.out.print(nodeToTraverse.getValue() + " ");
			if (nodeToTraverse!= null && nodeToTraverse.getChild() !=null){
				thisLevelQueue.add(nodeToTraverse.getChild());
			}
			nodeToTraverse = nodeToTraverse.getNext();
			
			// if reached end of list
			if (nodeToTraverse == null && null != nodes){
				nodeToTraverse = nodes.poll();
			}	
			
		}		
		
		// get the head for next level
		printLevelOrder(thisLevelQueue.poll(), thisLevelQueue);
	}
	
	private Node findNode(int level, int value){
		// get the list of lists at level
		Map<Integer, List<Node>> lists = levels[level];
		if (null == lists){
			// no list at this level 
			return null;
		}
		
		//now probe each linked list at this level for this value
		
		for (List<Node> list: lists.values()){
			int idx = list.indexOf(new Node(value));
			if (idx !=-1){
				return list.get(idx);
			}
		}
		
		return null;
	}
		//Sameehan's code :) \'-\'	}
}
	//Sameehan's code :) '[p.....-|'