package org.geeksforgeeks.binarytree;

/**
 * Given a binary tree, a complete path is defined as a path from root to a
 * leaf. The sum of all nodes on that path is defined as the sum of that path.
 * Given a number K, you have to remove (prune the tree) all nodes which don’t
 * lie in any path with sum>=k.
 * 
 * @author Shiva
 *
 * @param <int>
 */
public class RomoveNodesDontLieInAPathSum {

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

	private RomoveNodesDontLieInAPathSum.Node insert(RomoveNodesDontLieInAPathSum.Node root2, int key) {
		if (root2 == null)
			return new Node(key);
		if (key < root2.key)
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}

	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(RomoveNodesDontLieInAPathSum.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void prune(Node x, int k) {
		prune(x, k, 0);
	}

	private Node prune(RomoveNodesDontLieInAPathSum.Node x, int k, int i) {
		if (x == null)
			return null;
		x.left = prune(x.left, k, x.key + i);
		x.right = prune(x.right, k, x.key + i);
		if (x.left == null && x.right == null && (i + x.key) < k) {
			x = null;
			return x;
		}
		return x;
	}

	public static void main(String[] args) {
		RomoveNodesDontLieInAPathSum bt = new RomoveNodesDontLieInAPathSum();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);

		bt.inOrderTraversal();
		bt.prune(bt.root, 23); 
		System.out.println("\nAfter pruning : ");
		bt.inOrderTraversal();
	}

}
