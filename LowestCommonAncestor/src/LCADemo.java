
public class LCADemo {

	public static void main(String[] args) {
		Node root = new Node(1, null, null);
		root.setLeft(new Node(2, null, null));
		root.setRight(new Node(3, null, null));
		
		root.getLeft().setLeft(new Node(7, null, null));
		root.getLeft().setRight(new Node(6, null, null));
		
		root.getRight().setLeft(new Node(5, null, null));
		root.getRight().setRight(new Node(4, null, null));
		
		root.getLeft().getLeft().setLeft(new Node(8, null, null));
		root.getLeft().getLeft().setRight(new Node(9, null, null));
		
		root.getLeft().getRight().setLeft(new Node(10, null, null));
		root.getLeft().getRight().setRight(new Node(11, null, null));
		
		LCADemo demo = new LCADemo();
		//demo.printInOrder(root);
		System.out.println(demo.lca(root, root.getRight(), root.getLeft()).getData());
	}
	
	private Node lca(Node nodeCurrent, Node nodeA, Node nodeB){
		if (null == nodeCurrent){
			return null;
		}
		
		// if node A or B has found
		if (nodeA == nodeCurrent || nodeB == nodeCurrent){
			return nodeCurrent;
		}
		
		// find recursively 
		Node left = lca(nodeCurrent.getLeft(), nodeA, nodeB);
		Node right = lca(nodeCurrent.getRight(), nodeA, nodeB);
		
		// check if both nodes have found
		if(left != null && right != null){
			  return nodeCurrent;
		}
		
		//
		if (null == left){
			return right;
		}else{
			return right;
		}
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
