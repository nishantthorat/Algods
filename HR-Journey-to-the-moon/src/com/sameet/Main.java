package com.sameet;

import java.util.Set;

public class Main {

	public static void main(String[] args) {
		DisjointSetForest<Character> djsf = new DisjointSetForest<Character>();

		for (char c = 'a'; c <= 'e'; c++){
			
			djsf.makeSet(c);
		}
		
		// add edge - bd
		addEdge(djsf, 'b', 'd');
		addEdge(djsf, 'a', 'c');
		addEdge(djsf, 'a', 'b');
		addEdge(djsf, 'c', 'b');
		

		assert(IsSameComponent(djsf, 'b', 'c'));
		assert(IsSameComponent(djsf, 'b', 'a'));
		assert(IsSameComponent(djsf, 'd', 'c'));
		assert(!IsSameComponent(djsf, 'e', 'c'));
		assert(!IsSameComponent(djsf, 'b', 'e'));
		
		Set<Set<Character>> sets = djsf.getDisjointSets();
		for (Set<Character> s : sets){
			System.out.println(s);
		}
		
		Set<Character> setForItem = djsf.getDisjointSetFor('d');
		System.out.println(setForItem);
	}

	private static void addEdge(DisjointSetForest<Character> djsf, char a, char b){
		char rep1 =  djsf.find(a);
		char rep2 = djsf.find(b);
		
		if (rep1 != rep2){
			djsf.union(a, b);
		}
	}
	
	private static boolean IsSameComponent(DisjointSetForest<Character> djsf, char a, char b){
		if (djsf.find(a) == djsf.find(b)){
			return true;
		}
		
		return false;
	}
}
