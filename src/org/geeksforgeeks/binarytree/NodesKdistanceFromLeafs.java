package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class NodesKdistanceFromLeafs<Item extends Comparable<Item>> {
	
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

	private NodesKdistanceFromLeafs<Item>.Node insert(NodesKdistanceFromLeafs<Item>.Node root2,
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

	private void inOrderTraversal(NodesKdistanceFromLeafs<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void printNodeAtKdistanceFromLeafs(Node x, int k) {
		printNodeAtKdistanceFromLeafsUtil(x, k);
	}
	class MyInteger {
		private int l, r;
		private MyInteger(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}
	private int printNodeAtKdistanceFromLeafsUtil(Node x, int  k) {
		if (x == null)
			return -1;
		int l = 1 + printNodeAtKdistanceFromLeafsUtil(x.left, k);
		int r = 1 + printNodeAtKdistanceFromLeafsUtil(x.right, k);
		if (l == k || r == k)
			System.out.print(" " + x.key);
		return r;
	}

	public static void main(String[] args) {
		NodesKdistanceFromLeafs<Integer> bt = new NodesKdistanceFromLeafs<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println();
		bt.printNodeAtKdistanceFromLeafs(bt.root, 1);
	}

}
