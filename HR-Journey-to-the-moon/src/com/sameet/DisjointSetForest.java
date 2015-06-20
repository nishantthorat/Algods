package com.sameet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

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
		Set<T> xSet = reprToSet.get(x);
		Set<T> ySet = reprToSet.get(y);
		
		if (xSet == null){
			xSet = new HashSet<T>();
			xSet.add(x);
		}
		if (ySet == null){
			ySet = new HashSet<T>();
			ySet.add(y);
		}
		
		// now merge xSet and ySet - union 
		Set<T> mergeSet = new HashSet<T>();
		mergeSet.addAll(xSet);
		mergeSet.addAll(ySet);
		
		
		/// - END - Making sure we update set information
		
		
		// now get the node
		final Node xNode = data.get(xRoot);
		final Node yNode = data.get(yRoot);
		
		// now merge the smaller into larger
		
		if (xNode.rank > yNode.rank){
			yNode.parent = xRoot;
			reprToSet.put(xRoot, mergeSet);
			return xRoot;
		}else if (yNode.rank > xNode.rank){
			xNode.parent = yRoot;
			reprToSet.put(yRoot, mergeSet);
			return yRoot;
		}else{
			yNode.parent = xRoot;
			xNode.rank++;
			reprToSet.put(xRoot, mergeSet);
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
	
	/*public Set<T> getDisjointSetFor(T item){
		T repForItem = null; // representative for item in set
		Map<T, Set<T>> set = new HashMap<T, Set<T>>();
		for(T t : data.keySet()){
			T repr = find(t);
			
			if (t == item){
				repForItem = repr;
			}
			Set<T> reprSet = set.get(repr);
			if (reprSet == null) set.put(repr, reprSet = new HashSet<T>());
			reprSet.add(t);
		}
		
		return set.get(repForItem);		
	}*/
	
	public Set<T> getDisjointSetFor(T item){
		// get the representative first and then use it fetch set
		T repr = find(item);
		return reprToSet.get(repr);
	}
}
