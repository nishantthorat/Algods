
public class Node {
	private int data;
	private Node left;
	private Node right;
	
	public Node(int data, Node left, Node right){
		setData(data);
		setLeft(left);
		setRight(right);
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	
}
