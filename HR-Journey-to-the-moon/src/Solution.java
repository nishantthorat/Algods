import java.io.*;
import java.util.*;




public class Solution {
   

	public class DisjointSetForest<T> {

		private class Node{
			int rank;
			T parent;
			
			
			public Node(T parent, int rank){
				this.parent = parent;
				this.rank = rank;
			}
		};
		
		// item to set mapping (to be updated in union operation)
		private Map<T, Set<T>> reprToSet = new HashMap<T, Set<T>>();
		
		// maintain data into hashmap
		private Map<T, Node> data = new HashMap<T, Node>();

		public T makeSet(T item){
			
			// if already created then fail
			if (data.containsKey(item)){
				return null;
			}
			
			data.put(item,  new Node(item, 0));
			Set<T> mergeSet = new HashSet<T>();
			mergeSet.add(item);
			reprToSet.put(item, mergeSet);
			return item;
		}
		
		public T find(T item){
			Node node = data.get(item);
			
			if (null != node){			
				if (item != node.parent){
					return node.parent = find(node.parent);
				}else{
					return item;
				}
			}
			return null;
		}
		
		public T union(T x, T y){
			final T xRoot = find(x);
			final T yRoot = find(y);
			
			if (xRoot == yRoot || xRoot == null || yRoot == null){
				return null;
			}
			
			/// - START - Making sure we update set information
			Set<T> xSet = reprToSet.get(xRoot);
			Set<T> ySet = reprToSet.get(yRoot);			
			/// - END - Making sure we update set information
			
			
			// now get the node
			final Node xNode = data.get(xRoot);
			final Node yNode = data.get(yRoot);
			
			// now merge the smaller into larger
			
			if (xNode.rank > yNode.rank){
				yNode.parent = xRoot;
				xSet.addAll(ySet);				
				return xRoot;
			}else if (yNode.rank > xNode.rank){
				xNode.parent = yRoot;			
				ySet.addAll(xSet);;
				return yRoot;
			}else{
				yNode.parent = xRoot;
				xNode.rank++;
				xSet.addAll(ySet);
				return xRoot;
			}				
		}
		
		public Set<Set<T>> getDisjointSets(){
			Map<T, Set<T>> set = new HashMap<T, Set<T>>();
			for(T t : data.keySet()){
				T repr = find(t);
				
				Set<T> reprSet = set.get(repr);
				if (reprSet == null) set.put(repr, reprSet = new HashSet<T>());
				reprSet.add(t);
			}
			
			return new HashSet<Set<T>>(set.values());		
		}
					
		public Set<T> getDisjointSetFor(T item){
			// get the representative first and then use it fetch set
			T repr = find(item);
			return reprToSet.get(repr);
		}
	}


   public static void main(String[] args) throws Exception{
	    Solution sol = new Solution();
	    DisjointSetForest<Integer> djsf = sol.new DisjointSetForest<Integer>();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bfr.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int I = Integer.parseInt(temp[1]);      

        // singleton items
        for (int item = 1; item <= N; item++){
        	djsf.makeSet(item);
        }
        
        for(int i = 0; i < I; i++){
            temp = bfr.readLine().split(" ");
            String op = temp[0];
            
            if (op.equalsIgnoreCase("M")){
            	int a = Integer.parseInt(temp[1]);
                int b = Integer.parseInt(temp[2]);
                addPair(djsf, a, b);
            }else if (op.equals("Q")){
            	// query operation
            	int a = Integer.parseInt(temp[1]);
            	Set<Integer> set = djsf.getDisjointSetFor(a);
            	if (null != set){
            		//System.out.println(set);
            		System.out.println(set.size());
            	}
            }
         
           
        }
        
       
    }
   
   private static void addPair(DisjointSetForest<Integer> djsf, Integer a, Integer b){
		Integer rep1 =  djsf.find(a);
		Integer rep2 = djsf.find(b);
		
		if (rep1 != rep2){
			djsf.union(a, b);
		}
	}
}

 

