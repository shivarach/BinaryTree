package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class BinaryTreeToMirrorTree<Item extends Comparable<Item>> {

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

	private BinaryTreeToMirrorTree<Item>.Node insert(
			BinaryTreeToMirrorTree<Item>.Node root2, Item key) {
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

	private void inOrderTraversal(Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public void convertToMirrorTree() {
		convertToMirrorTree(root);
	}

	private void convertToMirrorTree(BinaryTreeToMirrorTree<Item>.Node x) {
		if (x == null)
			return;
		convertToMirrorTree(x.left);
		convertToMirrorTree(x.right);
		Node tmp = x.left;
		x.left = x.right;
		x.right = tmp;

	}

	public static void main(String[] args) {
		BinaryTreeToMirrorTree<Integer> bt = new BinaryTreeToMirrorTree<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);

		bt.inOrderTraversal();
		System.out.println("\nMirror tree");
		bt.convertToMirrorTree();
		bt.inOrderTraversal();
	}

}
