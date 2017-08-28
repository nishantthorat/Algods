import java.util.*;

/** HackerRank VMWare test **/

public class Solution {
	private static class Node {
		private boolean visited;
		private String  value;
		private Set<Node> connections = new HashSet<>();
		
		public Node(String value){
			this.value = value;
		}
		
		public void addConnection(final Node node){
			connections.add(node);
		}
		
		public String toString(){
			return value;
		}
	}
	
	
	
	private static Map<String, Node> nodes = new HashMap<>();
	
	static int minimalCost(int n, String[] pairs) {
		
		for (String pair : pairs){
			String[] p = pair.split("\\s+");
			
			// establish relation between these two nodes
			Node from, to;
			if (nodes.containsKey(p[0])){
				from = nodes.get(p[0]);
			}else{
				from = new Node(p[0]); 
				nodes.put(p[0], from);
			}
			
			if (nodes.containsKey(p[1])){
				to = nodes.get(p[1]);
			}else{
				to = new Node(p[1]); 
				nodes.put(p[1], to);
			}
			
			from.addConnection(to);
			to.addConnection(from);
		}
		
		for(int i=1; i <=n; i++){
			if (!nodes.containsKey(String.valueOf(i))){
				//System.out.println(i);
				nodes.put(String.valueOf(i), new Node(String.valueOf(i)));
			}
		}
		
		// now iterate over nodes, do BFS and calculate cost
		int cost = 0;
		for (String nod : nodes.keySet()){
			cost += Math.ceil(Math.sqrt(doBFS(nodes.get(nod))));
		}
		return cost;
    }
	
	private static int doBFS(Node nc){
		Queue<Node> q = new LinkedList<>();
		int c = 0;
		if (!nc.visited){
			q.add(nc);
		}
		
		while(!q.isEmpty()){
			Node current = q.poll();
			
			if (!current.visited){
				current.visited = true;
				q.addAll(current.connections);
				//System.out.println("Current: " + current.value + " Connections: " + current.connections);
				c++;
			}
		}
		//System.out.println("Cost: " + c);
		return c;
	}
	
	public static void main(String[] args) /*throws IOException*/{
		System.out.println(minimalCost(8, new String[] {"8 1", "5    8", "7 3", "8 6"}));
       /* Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int res;
        int _n;
        _n = Integer.parseInt(in.nextLine().trim());
        
        
        int _pairs_size = 0;
        _pairs_size = Integer.parseInt(in.nextLine().trim());
        String[] _pairs = new String[_pairs_size];
        String _pairs_item;
        for(int _pairs_i = 0; _pairs_i < _pairs_size; _pairs_i++) {
            try {
                _pairs_item = in.nextLine();
            } catch (Exception e) {
                _pairs_item = null;
            }
            _pairs[_pairs_i] = _pairs_item;
        }
        
        res = minimalCost(_n, _pairs);
        bw.write(String.valueOf(res));
        bw.newLine();
        
        bw.close();*/
    }

}
