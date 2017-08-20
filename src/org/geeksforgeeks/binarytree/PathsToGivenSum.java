package org.geeksforgeeks.binarytree;

import java.util.HashMap;

/**
 * 
 * @author Shiva
 * 
 * Cracking the coding interview
 * 
 *You are given a binary tree in which each node contains an integer value (which
might be positive or negative). Design an algorithm to count the number of paths that sum to a
given value. The path does not need to start or end at the root or a leaf, but it must go downwards
(traveling only from parent nodes to child nodes).
 * @param <int>
 */
public class PathsToGivenSum {
	
	private Node root;
	
	class Node {
		private int key;
		private Node left, right;
		
		public Node(int key) {
			this.key = key;
		}
	}
	
	public void insert(int key) {
		root = insert(root, key);
	}

	private Node insert(Node root2,
			int key) {
		if (root2 == null)
			return new Node(key);
		if(key <= root2.key)
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}
	
	public int pathsToGivenSum(Node root, int sum) {
		return pathsToGivenSum(root, sum, 0, new HashMap<Integer, Integer>());
	}
	
	private int pathsToGivenSum(Node x, int targetSum, int curSum, HashMap<Integer, Integer> pathCount) {
		if(x == null)
			return 0;
		curSum += x.key;
		int lookUpSum = curSum - targetSum;
		int paths = pathCount.getOrDefault(lookUpSum, 0);
		if (curSum == targetSum)
			paths++;
		incrementHash(pathCount, curSum, 1);
		paths += pathsToGivenSum(x.left, targetSum, curSum, pathCount);
		paths += pathsToGivenSum(x.right, targetSum, curSum, pathCount);
		incrementHash(pathCount, curSum, -1);
		return paths;
	}

	private void incrementHash(HashMap<Integer, Integer> pathCount, int curSum, int delta) {
		int newCount = pathCount.getOrDefault(curSum, 0) + delta;
		if(newCount == 0)
			pathCount.remove(curSum);
		else
			pathCount.put(curSum, newCount);
	}

	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public static void main(String[] args) {
		PathsToGivenSum bt = new PathsToGivenSum();
		bt.root = bt.new Node(10);
		bt.root.left = bt.new Node(5);
		bt.root.left.right = bt.new Node(2);
		bt.root.left.right.right = bt.new Node(1);
		bt.root.left.left = bt.new Node(3);
		bt.root.left.left.right = bt.new Node(-2);
		bt.root.left.left.left = bt.new Node(-3);
		bt.root.left.left.left.left = bt.new Node(-5);
		bt.root.left.left.left.left.left = bt.new Node(8);
		
		bt.root.right = bt.new Node(-3);
		bt.root.right.right = bt.new Node(11);
		
		bt.inOrderTraversal();
		System.out.println("\nNo. of valid paths with sum 8 are : "  + bt.pathsToGivenSum(bt.root, 8));
	}

}
