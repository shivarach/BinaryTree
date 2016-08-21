package org.geeksforgeeks.binarytree;

/**
 * Given a Binary Tree, find the maximum sum path from a leaf to root.
 * 
 * T: O(n)
 * S: O(1)
 * @author Shiva
 *
 * @param <Item>
 */
public class MaxPathFromLeafToRoot {

	private Node root;
	private Node leaf;
	private int max;

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

	private MaxPathFromLeafToRoot.Node insert(MaxPathFromLeafToRoot.Node root2, int key) {
		if (root2 == null)
			return new Node(key);
		if (key <= root2.key)
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}

	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(MaxPathFromLeafToRoot.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void findMaxPath(Node x) {
		findMaxValueandLeaf(x, 0);
		System.out.print("\nMax sum path from leaf to root : ");
		printPathFromLeafToRoot(x, leaf);
		System.out.println("\nMax path value is : " + max);
	}

	private void findMaxValueandLeaf(Node x, Integer sum) {
		if (x == null)
			return;
		if (x.left == null && x.right == null) {
			if (sum + x.key > max) {
				max = sum + x.key;
				leaf = x;
			}
		}
		findMaxValueandLeaf(x.left, sum + x.key);
		findMaxValueandLeaf(x.right, sum + x.key);
	}

	private boolean printPathFromLeafToRoot(Node x, Node targetLeaf) {
		if (x == null)
			return false;
		if (x == targetLeaf || printPathFromLeafToRoot(x.left, targetLeaf)
				|| printPathFromLeafToRoot(x.right, targetLeaf)) {
			System.out.print(x.key + " <- ");
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		MaxPathFromLeafToRoot bt = new MaxPathFromLeafToRoot();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		/*bt.root = bt.new Node(10);
		bt.root.left = bt.new Node(-2);
		bt.root.right = bt.new Node(7);
		bt.root.left.left = bt.new Node(8);
		bt.root.left.right = bt.new Node(-4);*/

		bt.inOrderTraversal();
		bt.findMaxPath(bt.root);
	}

}
