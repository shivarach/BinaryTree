package org.geeksforgeeks.binarytree;

/**
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/ The diameter of a
 * tree (sometimes called the width) is the number of nodes on the longest path
 * between two leaves in the tree. There may be more than one path with the same
 * diameter which may not include root. The diameter of a tree T is the largest
 * of the following quantities:
 * 
 * the diameter of T’s left subtree the diameter of T’s right subtree the
 * longest path between leaves that goes through the root of T (this can be
 * computed from the heights of the subtrees of T)
 * 
 * T: O(n^2)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class BinaryTreeDiameter<Item extends Comparable<Item>> {

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

	private BinaryTreeDiameter<Item>.Node insert(Node root2, Item key) {
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

	private void inOrderTraversal(BinaryTreeDiameter<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public int findDiameter(Node x) {
		if (x == null)
			return 0;
		int lheight = height(x.left);
		int rheight = height(x.right);
		int lDiameter = findDiameter(x.left);
		int rDiameter = findDiameter(x.right);
		return max(max(lDiameter, rDiameter), (lheight + rheight + 2));
	}

	private int height(Node x) {
		if (x == null)
			return -1;
		return 1 + max(height(x.left), height(x.right));
	}

	private int max(int a, int b) {
		int res = a > b ? a : b;
		return res;
	}

	public static void main(String[] args) {
		BinaryTreeDiameter<Integer> bt = new BinaryTreeDiameter<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		bt.insert(11);

		bt.inOrderTraversal();
		System.out.println("\nDiameter is : " + bt.findDiameter(bt.root));
	}

}
