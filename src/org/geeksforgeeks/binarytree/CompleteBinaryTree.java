package org.geeksforgeeks.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Check recursive solution which is also easy http://www.geeksforgeeks.org/check-whether-binary-tree-complete-not-set-2-recursive-solution/
 * Given a Binary Tree, write a function to check whether the given Binary Tree
 * is Complete Binary Tree or not.
 * 
 * A complete binary tree is a binary tree in which every level, except possibly
 * the last, is completely filled, and all nodes are as far left as possible.
 * T: O(n)
 * S: O(1)
 * @author Shiva
 *
 * @param <Item>
 */
public class CompleteBinaryTree<Item extends Comparable<Item>> {

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

	private CompleteBinaryTree<Item>.Node insert(CompleteBinaryTree<Item>.Node root2, Item key) {
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

	private void inOrderTraversal(CompleteBinaryTree<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public boolean isComplete(Node x) {
		if (x == null)
			return true;
		Queue<Node> q = new LinkedList<Node>();
		q.add(x);
		return isComplete(q);
	}

	private boolean isComplete(Queue<Node> q) {
		while (!q.isEmpty()) {
			Node x = q.poll();
			// check whether x is not full
			if (!(x.left != null && x.right != null)) {
				// if x has any child, it should be left otherwise it's not
				// complete
				if (x.right != null)
					return false;
				return validateLeafNodes(x, q);
			}
			q.add(x.left);
			q.add(x.right);
		}
		return true;
	}

	// return true if all the nodes in queue are leaf nodes
	private boolean validateLeafNodes(CompleteBinaryTree<Item>.Node x, Queue<CompleteBinaryTree<Item>.Node> q) {
		// all this nodes must be leaf nodes otherwise it's not complete
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			if (!(tmp.left == null && tmp.right == null)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		CompleteBinaryTree<Integer> bt = new CompleteBinaryTree<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);

		bt.inOrderTraversal();
		System.out.println("\nIs complete : " + bt.isComplete(bt.root));
	}

}
