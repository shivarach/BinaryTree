package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class BinaryTreeToDLL2<Item extends Comparable<Item>> {
	
	private Node root;
	
	private Node head;
	
	private Node prev;
	
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

	private BinaryTreeToDLL2<Item>.Node insert(BinaryTreeToDLL2<Item>.Node root2,
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

	public void binaryToDLL(Node x) {
		if (x == null)
			return;
		binaryToDLL(x.left);
		if (prev == null)
			head = x;
		else {
			prev.right = x;
			x.left = prev;
		}
		prev = x;
		binaryToDLL(x.right);
	}
	
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(BinaryTreeToDLL2<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public static void main(String[] args) {
		BinaryTreeToDLL2<Integer> bt = new BinaryTreeToDLL2<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		System.out.print("Binary Tree : ");
		bt.inOrderTraversal();
		
		bt.binaryToDLL(bt.root);
		System.out.print("\nDouble linked list : ");
		BinaryTreeToDLL2<Integer>.Node tmp = bt.head;
		while(tmp != null) {
			System.out.print(tmp.key + " ");
			tmp = tmp.right;
		}
	}

}
