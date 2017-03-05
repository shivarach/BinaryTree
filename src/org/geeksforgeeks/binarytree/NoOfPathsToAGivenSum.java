package org.geeksforgeeks.binarytree;

import java.util.HashMap;

/**
 * You are given a binary tree in which each node contains an integer value (which
might be positive or negative). Design an algorithm to count the number of paths that sum to a
given value. The path does not need to start or end at the root or a leaf, but it must go downwards
(traveling only from parent nodes to child nodes).

Source: Cracking the Coding Interview
 * @author Shiva
 *
 *T: O(n)
 *S: O(n)
 * @param <Item>
 */
public class NoOfPathsToAGivenSum {
	
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
	
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	// here path may not need to start at root and end at leaf
	public int countPathsToGivenSum(Node root, int targetSum) {
		return countPathsToGivenSum(root, targetSum, 0, new HashMap<Integer, Integer>());
	}
	// hashmap contains key as running sum and value as the count(that running sum occured)
	private int countPathsToGivenSum(Node x, int targetSum, int runningSum,
			HashMap<Integer, Integer> hashMap) {
		if (x == null)
			return 0;
		runningSum += x.key;
		int totalPaths = hashMap.getOrDefault(x.key, 0);
		//if there is a path from root
		if(runningSum == targetSum)
			totalPaths++;
		// increment hashmap value (count) for corresponding key i.e. running sum
		hashMap.put(x.key, hashMap.getOrDefault(x.key, 0) + 1);
		totalPaths += countPathsToGivenSum(x.left, targetSum, runningSum, hashMap);
		totalPaths += countPathsToGivenSum(x.right, targetSum, runningSum, hashMap);
		// increment hashmap value (count) for corresponding key i.e. running sum
		hashMap.put(x.key, hashMap.getOrDefault(x.key, 0) - 1);
		return totalPaths;
	}

	public static void main(String[] args) {
		NoOfPathsToAGivenSum bt = new NoOfPathsToAGivenSum();
		/*bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);*/
		bt.root = bt.new Node(10);
		bt.root.left = bt.new Node(0);
		bt.root.left.left = bt.new Node(5);
		bt.root.left.left.left = bt.new Node(1);
		bt.root.left.left.left.left = bt.new Node(2);
		bt.root.left.left.left.left.left = bt.new Node(-1);
		bt.root.left.left.left.left.left.left = bt.new Node(-1);
		bt.root.left.left.left.left.left.left.left = bt.new Node(7);
		bt.root.left.left.left.left.left.left.left.left = bt.new Node(1);
		bt.root.left.left.left.left.left.left.left.left.left = bt.new Node(2);
		bt.inOrderTraversal();
		System.out.print("\nNo. of paths with sum 8 is : ");
		System.out.println(bt.countPathsToGivenSum(bt.root, 8));
	}

}
