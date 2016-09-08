package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class BinaryTreeToDLL<Item extends Comparable<Item>> {
	
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

	private BinaryTreeToDLL<Item>.Node insert(BinaryTreeToDLL<Item>.Node root2,
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

	public Node convertBinaryTreeToDLL(Node root) {
		Node tmp = binaryToDLL(root);
		if (tmp == null)
			return tmp;
		while (tmp.left != null)
			tmp = tmp.left;
		return tmp;//head
	}
	
	private Node binaryToDLL(Node x) {
		if (x == null)
			return x;
		Node a = binaryToDLL(x.left);
		if (a != null) {
			// move a towards right most
			while (a.right != null)
				a = a.right;
			a.right = x;
			x.left = a;
		}
		Node b = binaryToDLL(x.right);
		if (b != null) {
			// move a towards right most
			while (b.left != null)
				b = b.left;
			x.right = b;
			b.left = x;
		}
		return x;
	}
	
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(BinaryTreeToDLL<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public static void main(String[] args) {
		BinaryTreeToDLL<Integer> bt = new BinaryTreeToDLL<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		System.out.print("Binary Tree : ");
		bt.inOrderTraversal();
		
		BinaryTreeToDLL<Integer>.Node head = bt.convertBinaryTreeToDLL(bt.root);
		System.out.print("\nDouble linked list : ");
		while(head != null) {
			System.out.print(head.key + " ");
			head = head.right;
		}
	}

}
