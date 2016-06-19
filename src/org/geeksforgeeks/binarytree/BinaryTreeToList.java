package org.geeksforgeeks.binarytree;

/**
 * Write a recursive function treeToList(Node root) that takes an ordered binary
 * tree and rearranges the internal pointers to make a circular doubly linked
 * list out of the tree nodes. The "previous" pointers should be stored in the
 * "small" field and the "next" pointers should be stored in the "large" field.
 * The list should be arranged so that the nodes are in increasing order. Return
 * the head pointer to the new list. The operation can be done in O(n) time
 *
 * @param <Item>
 */
public class BinaryTreeToList<Item extends Comparable<Item>> {

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

	private BinaryTreeToList<Item>.Node insert(
			BinaryTreeToList<Item>.Node root2, Item key) {
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

	private void inOrderTraversal(BinaryTreeToList<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);

	}

	public Node treeToList() {
		return treeToList(root);
	}

	private Node treeToList(BinaryTreeToList<Item>.Node x) {
		if (x == null)
			return null;
		x.left = treeToList(x.left);
		x.right = treeToList(x.right);

		Node a = x.left;
		Node c = x.right;
		x.left = x;
		x.right = x;
		return makeList(a, x, c);// Here inputs are always circular double ll

	}

	// appends the circular linked lists namely a, b, c
	private Node makeList(Node a, Node b, Node c) {
		a = makeList(a, b);
		a = makeList(a, c);
		return a;
	}

	private Node makeList(Node a, Node b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		Node tmp = b.left;
		a.left.right = b;
		b.left = a.left;
		a.left = tmp;
		tmp.right = a;
		return a;
	}

	public void printList(Node root) {
		if (root == null)
			return;
		Node tmp = root;
		do {
			System.out.print(tmp.key + " ");
			tmp = tmp.right;
		} while (tmp != root);
	}

	public static void main(String[] args) {
		BinaryTreeToList<Integer> bt = new BinaryTreeToList<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);

		bt.inOrderTraversal();
		BinaryTreeToList.Node root = bt.treeToList();
		System.out.println();
		System.out.println("Doubly ll from ordered binary tree: ");
		bt.printList(root);
	}

}
