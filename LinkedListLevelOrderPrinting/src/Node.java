
public class Node {

	private int value;
	private Node next;
	private Node child;
	
	public Node(int value, Node next, Node child){
		setValue(value);
		setNext(next);
		setChild(child);
	}
	
	public Node(int value) {
		setValue(value);		
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node getChild() {
		return child;
	}
	public void setChild(Node child) {
		this.child = child;
	}
	
	public Node addNextNode(int value){
		Node newNode = new Node(value);
		setNext(newNode);		
		return newNode;
	}
	
	public void addChild(Node child){
		setChild(child);
	}
	
	public boolean equals(Object o){
		if (this == o){
			return true;
		}
		
		if (o == null){
			return false;
		}
		
		Node node = null;
		if (o instanceof Node){
			node = (Node) o;
		}
		
		return node.getValue() == getValue();
	}
}
