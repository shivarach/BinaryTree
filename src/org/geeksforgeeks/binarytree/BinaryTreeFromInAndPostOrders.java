package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva Construct Tree from given Inorder and Post-order traversals T:
 *         O(n)
 * @param <Item>
 */
public class BinaryTreeFromInAndPostOrders<Item extends Comparable<Item>> {

	private Node root;
	private static int preIndex = 0;

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

	private BinaryTreeFromInAndPostOrders<Item>.Node insert(
			BinaryTreeFromInAndPostOrders<Item>.Node root2, Item key) {
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

	private void inOrderTraversal(BinaryTreeFromInAndPostOrders<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public void constructFromInAndPostOrders(Item[] in, Item[] pre) {
		preIndex = in.length;
		root = constructFromInAndPostOrders(in, pre, 0, in.length - 1);
	}

	private BinaryTreeFromInAndPostOrders<Item>.Node constructFromInAndPostOrders(
			Item[] in, Item[] pre, int i, int length) {
		if (i > length)
			return null;
		Item item = pre[--preIndex];
		Node node = new Node(item);
		int inIndex = searchIn(in, item, i, length);
		node.right = constructFromInAndPostOrders(in, pre, inIndex + 1, length);
		node.left = constructFromInAndPostOrders(in, pre, i, inIndex - 1);
		return node;
	}

	private int searchIn(Item[] in, Item item, int start, int length) {
		for (int i = start; i <= length; i++) {
			if (in[i].compareTo(item) == 0)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		BinaryTreeFromInAndPostOrders<Integer> bt = new BinaryTreeFromInAndPostOrders<Integer>();
		Integer[] in = { 2, 3, 4, 5, 6, 7, 8 };
		Integer[] post = { 2, 4, 3, 6, 8, 7, 5 };

		System.out
				.println("Constructing Binary Tree from in order and post order traversals....");
		bt.constructFromInAndPostOrders(in, post);
		bt.inOrderTraversal();
	}

}
