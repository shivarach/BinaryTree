package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *Construct Tree from given Inorder and Preorder traversals
 *T: O(n)
 * @param <Item>
 */
public class BinaryTreeFromInAndPreOrders<Item extends Comparable<Item>> {
	
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
		if(key == null)
			return;
		root = insert(root, key);
	}

	private BinaryTreeFromInAndPreOrders<Item>.Node insert(BinaryTreeFromInAndPreOrders<Item>.Node root2,
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

	private void inOrderTraversal(BinaryTreeFromInAndPreOrders<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void constructFromInAndPreOrders(Item[] in, Item[] pre) {
		root = constructFromInAndPreOrders(in, pre, 0, in.length - 1);
	}

	private BinaryTreeFromInAndPreOrders<Item>.Node constructFromInAndPreOrders(
			Item[] in, Item[] pre, int i, int length) {
		if(i > length)
			return null;
		Item item = pre[preIndex++];
		Node node = new Node(item);
		int inIndex = searchIn(in, item, i, length);
		node.left = constructFromInAndPreOrders(in, pre, i, inIndex - 1);
		node.right = constructFromInAndPreOrders(in, pre, inIndex + 1, length);
		return node;
	}

	private int searchIn(Item[] in, Item item, int start, int length) {
		for (int i = start; i <= length; i++) {
			if(in[i].compareTo(item) == 0)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		BinaryTreeFromInAndPreOrders<Integer> bt = new BinaryTreeFromInAndPreOrders<Integer>();
		Integer[] in = {2, 3, 4, 5, 6, 7, 8};
		Integer[] pre = {5, 3, 2, 4, 7, 6, 8};
		
		
		System.out.println("Converting Binary Tree from in order and pre order traversals....");
		bt.constructFromInAndPreOrders(in, pre);
		bt.inOrderTraversal();
	}

}
