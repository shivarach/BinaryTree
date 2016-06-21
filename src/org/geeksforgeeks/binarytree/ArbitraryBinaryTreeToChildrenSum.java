package org.geeksforgeeks.binarytree;

import org.geeksforgeeks.binarytree.BinaryTreeChildrenSum.Node;

/**
 * Given an arbitrary binary tree, convert it to a binary tree that holds
 * Children Sum Property. You can only increment data values in any node (You
 * cannot change structure of tree and cannot decrement value of any node).
 * 
 * T:O(n)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class ArbitraryBinaryTreeToChildrenSum {

	private Node root;

	class Node {
		private Integer key;
		private Node left, right;

		public Node(Integer key) {
			this.key = key;
		}
	}

	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public void convert(Node x) {
		if (x == null || (x.left == null && x.right == null))
			return;
		int leftSum = 0, rightSum = 0;
		convert(x.left);
		convert(x.right);
		if (x.left != null)// if null 0 is used
			leftSum = x.left.key;
		if (x.right != null)
			rightSum = x.right.key;
		int dif = leftSum + rightSum - x.key;
		if (dif >= 0)
			x.key = x.key + dif;
		else {
			
			increment(x, -dif);
		}

	}

	/*
	 * If diff < 0 (node’s data is greater than the node's children sum) then
	 * increment one child’s data. We can choose to increment either left or
	 * right child if they both are not NULL. Let us always first increment the
	 * left child. Incrementing a child changes the subtree’s children sum
	 * property so we need to change left subtree also. So we recursively
	 * increment the left child. If left child is empty then we recursively call
	 * increment() for right child.
	 */
	private void increment(Node x, int i) {
		if (x.left != null) {
			x.left.key += i;
			increment(x.left, i);
		} else if (x.right != null) {
			x.right.key += i;
			increment(x.right, i);
		}

	}

	public static void main(String[] args) {
		ArbitraryBinaryTreeToChildrenSum tree = new ArbitraryBinaryTreeToChildrenSum();
		tree.root = tree.new Node(50);
		tree.root.left = tree.new Node(7);
		tree.root.right = tree.new Node(2);
		tree.root.left.left = tree.new Node(3);
		tree.root.left.right = tree.new Node(5);
		tree.root.right.left = tree.new Node(1);
		tree.root.right.right = tree.new Node(30);

		System.out.println("Before converting.....");
		tree.inOrderTraversal();
		tree.convert(tree.root);
		System.out.println("\nAfter converting.... ");
		tree.inOrderTraversal();
	}

}
