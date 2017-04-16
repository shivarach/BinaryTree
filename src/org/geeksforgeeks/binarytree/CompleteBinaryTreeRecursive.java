package org.geeksforgeeks.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/* Also check {@CompleteBinaryTree.java}
 * @author Shiva
 *
 * @param <Item>
 */
public class CompleteBinaryTreeRecursive<Item extends Comparable<Item>> {

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

	private CompleteBinaryTreeRecursive<Item>.Node insert(CompleteBinaryTreeRecursive<Item>.Node root2, Item key) {
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

	private void inOrderTraversal(CompleteBinaryTreeRecursive<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public boolean isComplete(Node x) {
		int n = numberOfNodes(root);
		return isComplete(root, 0, n);
	}

	private int numberOfNodes(CompleteBinaryTreeRecursive<Item>.Node x) {
		if (x == null)
			return 0;
		return 1 + numberOfNodes(x.left) + numberOfNodes(x.right);
	}

	private boolean isComplete(Node x, int index, int numOfNodes) {
		if (x == null)
			return true;
		if (index >= numOfNodes)
			return false;
		return isComplete(x.left, 2 * index + 1, numOfNodes) && 
				isComplete(x.right, 2 * index + 2, numOfNodes);
	}

	

	public static void main(String[] args) {
		CompleteBinaryTreeRecursive<Integer> bt = new CompleteBinaryTreeRecursive<Integer>();
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
