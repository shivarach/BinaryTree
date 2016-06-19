package org.geeksforgeeks.binarytree;

/**
 * Look {@LevelOrderTraversalInNewLine} which is efficient.
 * Write a function to print level order traversal of a tree. 
 * T:O(n^2) where n is no. of nodes
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class LevelOrderTraversalInNewLine_2<Item extends Comparable<Item>> {

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

	private LevelOrderTraversalInNewLine_2<Item>.Node insert(
			LevelOrderTraversalInNewLine_2<Item>.Node root2, Item key) {
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

	private void inOrderTraversal(LevelOrderTraversalInNewLine_2<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public void levelOrderTraversalInSpiral() {
		int h = findHeight(root);
		for (int i = 0; i <= h; i++) {
			printGivenLevel(root, i);
			System.out.println();
		}

	}

	private void printGivenLevel(LevelOrderTraversalInNewLine_2<Item>.Node x,
			int level) {
		if (x == null)
			return;
		if (level == 0) {
			System.out.print(x.key + " ");
			return;
		}
		printGivenLevel(x.left, level - 1);
		printGivenLevel(x.right, level - 1);
	}

	private int findHeight(LevelOrderTraversalInNewLine_2<Item>.Node x) {
		if (x == null)
			return -1;
		return 1 + max(findHeight(x.left), findHeight(x.right));
	}

	private int max(int a, int b) {
		int result = a > b ? a : b;
		return result;
	}

	public static void main(String[] args) {
		LevelOrderTraversalInNewLine_2<Integer> bt = new LevelOrderTraversalInNewLine_2<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);

		bt.inOrderTraversal();
		System.out.println("\nLevel order traversal in new line: ");
		bt.levelOrderTraversalInSpiral();
	}

}
