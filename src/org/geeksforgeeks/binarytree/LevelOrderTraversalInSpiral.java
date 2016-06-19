package org.geeksforgeeks.binarytree;

import java.util.Stack;

/**
 * Write a function to print spiral order traversal of a tree. T:O(n) where n is
 * no. of nodes S:O(n)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class LevelOrderTraversalInSpiral<Item extends Comparable<Item>> {

	private Node root;

	class Node {
		private Item key;
		private Node left, right;

		public Node(Item key) {
			this.key = key;
		}
	}

	public void insert(Item key) {
		if (key == null)
			return;
		root = insert(root, key);
	}

	private LevelOrderTraversalInSpiral<Item>.Node insert(
			LevelOrderTraversalInSpiral<Item>.Node root2, Item key) {
		if (root2 == null)
			return new Node(key);
		int cmp = key.compareTo(root2.key);
		if (cmp <= 0)
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}

	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(LevelOrderTraversalInSpiral<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public void levelOrderTraversalInSpiral() {
		levelOrderTraversalInSpiral(root);
	}

	/**
	 * The idea is to use two stacks. We can use one stack for printing from
	 * left to right and other stack for printing from right to left. In every
	 * iteration, we have nodes of one level in one of the stacks. We print the
	 * nodes, and push nodes of next level in other stack.
	 * 
	 * @param x
	 */
	private void levelOrderTraversalInSpiral(Node x) {
		if (x == null)
			return;
		Stack<Node> leftToRightStack = new Stack<Node>();
		Stack<Node> rightToLeftStack = new Stack<Node>();
		rightToLeftStack.push(x);

		while (!leftToRightStack.empty() || !rightToLeftStack.empty()) {

			while (!rightToLeftStack.empty()) {
				Node tmp = rightToLeftStack.pop();
				System.out.print(tmp.key + " ");
				// first right node should be passed to get left Node in next
				// iteration
				if (tmp.right != null)
					leftToRightStack.push(tmp.right);
				if (tmp.left != null)
					leftToRightStack.push(tmp.left);
			}

			System.out.println();

			while (!leftToRightStack.empty()) {
				Node tmp = leftToRightStack.pop();
				System.out.print(tmp.key + " ");
				// first right node should be passed to get left Node in next
				// iteration
				if (tmp.left != null)
					rightToLeftStack.push(tmp.left);
				if (tmp.right != null)
					rightToLeftStack.push(tmp.right);
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {
		LevelOrderTraversalInSpiral<Integer> bt = new LevelOrderTraversalInSpiral<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);

		bt.inOrderTraversal();
		System.out.println("\nLevel order traversal in spiral way: ");
		bt.levelOrderTraversalInSpiral();
	}

}
