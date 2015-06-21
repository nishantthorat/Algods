
// Solution for http://www.geeksforgeeks.org/flipkart-interview-experience-set-23/
// Round 1: Machine coding:  question
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Trie {

	private Node root = new Node();

	private static class Node{
		private ArrayList<Integer> value = null;
		private Map<Character, Node> next= new HashMap<Character, Node>(); //Node[] next = new Node[ALPHABETS];
		private boolean flag;
		private char op;
	}

	private static class ValidOperator {

		public static final char GREATER_THAN = '>';
		public static final char EQUAL_TO = '=';
		public static final char A = 'a';
		public static final char B = 'b';
		public static final char C = 'c';
		public static final char D = 'd';
		public static final char E = 'e';
		public static final char F = 'f';

		public static boolean isValid(char ch) {
			if (ch == GREATER_THAN || ch == A || ch == B || ch == C || ch == D
					|| ch == E || ch == F || ch == EQUAL_TO)
				return true;
			return false;
		}

		public static boolean isAlphabet(char ch) {
			if (ch == GREATER_THAN || ch == EQUAL_TO)
				return false;
			return true;
		}

		public static boolean isGreatertThan(char ch) {
			if (ch == GREATER_THAN)
				return true;
			return false;

		}

		public static boolean isEqualTo(char ch) {
			if (ch == EQUAL_TO)
				return true;
			return false;

		}
	}

	public void put(String key){
		insertExpression(key);
	}

	public void insertExpression(final String key){
		Node parentNode  = root;
		Node currentNode = null;
		String exprToInsert = new String(key).toLowerCase();
		System.out.println("Processing : " + exprToInsert);
		for(int i=0; i < exprToInsert.length(); i++){
			char ch = exprToInsert.charAt(i);

			if (ValidOperator.isValid(ch)){
				// get node is already present

				if(parentNode.next.containsKey(ch)){
					currentNode = parentNode.next.get(ch);
				}
				// make sure we follow constraints
				// 1. value assignment only if parent node is a key node
				if (ValidOperator.isEqualTo(ch)){
					System.out.println("Extracted value: " +exprToInsert.substring(i+1));
					// two possibilities here: either node is a key or non key
					if (null == currentNode){
						// new node
						if (parentNode.next.isEmpty()){

							if (null == parentNode.value)
								parentNode.value = new ArrayList<Integer>();
							parentNode.value.add(Integer.parseInt(exprToInsert.substring(i+1)));							
							parentNode.flag = true;
							break;
						}else{
							// invalid
							System.out.println("Discarded: " + exprToInsert); break;
						}

					}else{

						// make sure its a key node
						if (parentNode.flag){
							// assign extracted value
							parentNode.value.add(Integer.parseInt(exprToInsert.substring(i+1)));
						}else{
							// invalid
							System.out.println("Discarded: " + exprToInsert); break;
						}

					}				
				}else{
					// any other character being processed
					// create child node only if parent node is non key and has a nonempty children list
					if (!parentNode.flag){	
						if (null == currentNode){
							currentNode = new Node();									
						}
						currentNode.op = ch;
						parentNode.next.put(ch, currentNode);							
					}else{
						// invalid
						System.out.println("Discarded: " + exprToInsert);break;
					}
				}

			} // if valid
			parentNode = currentNode;
			currentNode = null;
		}
	}



	public void generateJSON(){
		Node parentNode = root;
		//
		System.out.println("{");
		emitJSONNode(parentNode, 0);
		System.out.println("}");
	}

	private void emitJSONNode(Node root, int noOfSiblings)
	{
		/*if (root == null)
            return;*/
		int size =  root.next.values().size();
		for (Node nodeChild : root.next.values()){            

			if (ValidOperator.isGreatertThan( nodeChild.op)){
				System.out.print("{ "); 
				}else{
					System.out.print("\"" + nodeChild.op + "\"" + ": ");
				}

			emitJSONNode(nodeChild, --size);    
			if (ValidOperator.isGreatertThan( nodeChild.op)){
				System.out.print(" }"); 

				if (noOfSiblings > 0){
					System.out.print(",");
				}
			}
		}

		if (root.flag)
		{            
			System.out.println(root.value);   
			if (noOfSiblings > 0){
				System.out.print(",");
			}
		}     

	}

	public static void main(String[] args){
		Trie trie = new Trie();
		String[] expressions = {"a>b=1", "a>b=2", "a>c>e=3", "a>c>f=4", "b>a=5", "a>b>c=5", "b=7", "a>b>c>d=99",  "a>b=99"};
		for (String expr : expressions){
			trie.put(expr);
		}

		trie.generateJSON();
	}


}
