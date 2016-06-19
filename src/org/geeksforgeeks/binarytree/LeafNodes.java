package org.geeksforgeeks.binarytree;

/**
 * No of leaf nodes in a binary tree
 * T: O(n) where n = no. of nodes
 * @author Shiva
 *
 * @param <Item>
 */
public class LeafNodes<Item extends Comparable<Item>> {
	
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

	private LeafNodes<Item>.Node insert(LeafNodes<Item>.Node root2,
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
	
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(LeafNodes<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public int getLeafNodesCount() {
		return getLeafNodesCount(root);
	}

	private int getLeafNodesCount(Node x) {
		if (x == null)
			return 0;
		else if (x.left == null && x.right == null)
			return 1;
		return getLeafNodesCount(x.left) + getLeafNodesCount(x.right);
		
	}

	public static void main(String[] args) {
		LeafNodes<Integer> bt = new LeafNodes<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println("No. of leaf nodes are : ");
		System.out.println(bt.getLeafNodesCount());
	}

}
