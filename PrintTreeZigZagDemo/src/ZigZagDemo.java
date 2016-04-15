
public class ZigZagDemo {

	
	public static void main(String[] args) {
		
		ZigZagDemo demo = new ZigZagDemo();
		Node       root = demo.createTree();
		//demo.printPreOrder(root); System.out.println("");
		//demo.printPostOrder(root);
		demo.printZigZag(root, true, false);
	}
	
	private Node createTree(){
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
		return root;
	}
	
	private void printZigZag(Node root, boolean directionLeftToRight, boolean isNodePrinted){
		if (null == root){
			return;
		}
	
		if (!isNodePrinted){
			System.out.print(root.getData() + " ");
		}
		
		if (directionLeftToRight){
	
			if (null != root.getLeft()){
				System.out.print(root.getLeft().getData() + " ");
			}
			if (null != root.getRight()){
				System.out.print(root.getRight().getData() + " ");
			}
			
			printZigZag(root.getRight(), !directionLeftToRight, null != root.getRight());
			printZigZag(root.getLeft(), !directionLeftToRight, null != root.getLeft());
		}else{
			if (null != root.getRight()){
				System.out.print(root.getRight().getData() + " ");
			}
			if (null != root.getLeft()){
				System.out.print(root.getLeft().getData() + " ");
			}
			printZigZag(root.getLeft(), !directionLeftToRight, null != root.getLeft());
			printZigZag(root.getRight(), !directionLeftToRight, null != root.getRight());
		}
		//System.out.println("");
	}

	private void printPostOrder(Node root){
		if (null == root){
			return;
		}
		
		printPostOrder(root.getLeft());
		printPostOrder(root.getRight());
		System.out.print(root.getData() + " ");
	}
	
	private void printPreOrder(Node root){
		if (null == root){
			return;
		}
		
		System.out.print(root.getData() + " ");
		printPreOrder(root.getLeft());
		printPreOrder(root.getRight());
	}
}
