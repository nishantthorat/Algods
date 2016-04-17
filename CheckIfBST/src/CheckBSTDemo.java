
public class CheckBSTDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root = new Node(7);
		CheckBSTDemo demo = new CheckBSTDemo();
		demo.addValidNode(root, new Node(5));
		demo.addValidNode(root, new Node(4));
		demo.addValidNode(root, new Node(9));
		demo.addValidNode(root, new Node(8));
		
		System.out.println("Is BST: " + demo.CheckBST(root));
		Node search = demo.find(root, 9);
		if (null != search){
			System.out.println("Found : " + search.getData());
			demo.addNode(search, new Node(1), true);
		}else{
			System.out.println("Not found");
		}
		demo.printPreOrder(root);
		System.out.println("");
		demo.printInOrder(root);
		System.out.println("Is BST: " + demo.CheckBST(root));
	}
	
	public boolean CheckBST(Node root){
		// recusrsively check each node to ensure that the node value satisfies BST property and structure
		
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isBST(Node node, int min, int max){
		// check for constraints and structure
		if (node == null){
			return true;
		}
		
		if (!((node.getLeft() != null ? node.getData() >= node.getLeft().getData() : true) && (node.getRight() != null ? node.getData() <=node.getRight().getData() : true)
				&& ( node.getData() >= min && node.getData() <= max))){
			return false;
		}
		
		// examine the left and right subtrees
		return isBST(node.getLeft(), min, node.getData()) && isBST(node.getRight(), node.getData(), max);
	}
	
	
	private void addNode(Node root, Node nodeToBeAdded, boolean addToLeft){
		if (root == null || nodeToBeAdded == null){
			return;
		}
		
		if (addToLeft){
			root.setLeft(nodeToBeAdded);
		}else{
			root.setRight(nodeToBeAdded);
		}
		
	}
	
	private void addValidNode(Node root, Node nodeToBeAdded){
		// find node under which new node can be inserted
		if (root == null || nodeToBeAdded == null){
			return;
		}
		Node node       = root;
		Node parentNode = node;
		boolean left = false;
		while(null != node){
			parentNode = node;
			if (nodeToBeAdded.getData() < node.getData()){
				node = node.getLeft();
				left = true;
			}else if(nodeToBeAdded.getData() > node.getData()){
				node = node.getRight();
				left = false;
			}
		}
		
		if (null != parentNode){
			if (left){
				parentNode.setLeft(nodeToBeAdded);
			}else{
				parentNode.setRight(nodeToBeAdded);
			}
			
		}
	}
	
	private Node find(Node node, int searchData){
		if (null == node){
			return null;
		}
		
		while(null != node){
			if (searchData < node.getData()){
				node = node.getLeft();
			}else if (searchData > node.getData()){
				node = node.getRight();
			}else if (searchData == node.getData()){
				return node;
			}
		}
		
		return null;
	}
	
	private void printPreOrder(Node root){
		if (null == root){
			return;
		}
		
		System.out.print(root.getData() + " ");
		printPreOrder(root.getLeft());
		printPreOrder(root.getRight());
	}
	

	private void printInOrder(Node root){
		if (null == root){
			return;
		}
		
		printInOrder(root.getLeft());
		System.out.print(root.getData() + " ");
		printInOrder(root.getRight());
	}
	
	

}
