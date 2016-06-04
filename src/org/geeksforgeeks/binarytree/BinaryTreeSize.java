package org.geeksforgeeks.binarytree;

/**
 * Finds size of a binary tree
 * @author Suresh Baga
 *
 * @param <Item>
 */
public class BinaryTreeSize<Item extends Comparable<Item>> {
	
	private Node root;
	
	class Node {
		private Item key;
		private Node left, right;
		
		public Node(Item key) {
			this.key = key;
		}
	}
	
	public void insert(Item key) {
		if(key == null)
			return;
		root = insert(root, key);
	}

	private BinaryTreeSize<Item>.Node insert(BinaryTreeSize<Item>.Node root2,
			Item key) {
		if (root2 == null)
			return new Node(key);
		int cmp = key.compareTo(root2.key);
		if(cmp <= 0)
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}
	
	public int size() {
		return size(root);
	}

	private int size(BinaryTreeSize<Item>.Node root2) {
		if (root2 == null)
			return 0;
		return size(root2.left) + 1 + size(root2.right);
	}

	public static void main(String[] args) {
		BinaryTreeSize<Integer> bt = new BinaryTreeSize<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		System.out.println("size: " + bt.size());

	}

}
