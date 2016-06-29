package org.geeksforgeeks.binarytree;


/**
 * to determine if a binary tree is height-balanced
 * T: O(n)
 * @author Shiva
 *
 * @param <Item>
 */
public class BalancedBinaryTree2<Item extends Comparable<Item>> {
	
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

	private BalancedBinaryTree2<Item>.Node insert(BalancedBinaryTree2<Item>.Node root2,
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
	
	
	private void inOrderTraversal(Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public Result isBalanced(Node x) {
		if (x == null)
			return new Result(-1, true);
		
		Result left = isBalanced(x.left);
		Result right = isBalanced(x.right);
		
		Result res = new Result();
		res.h = max(left.h, right.h) + 1;
		res.balanced = ( Math.abs(left.h - right.h) <=1 ) && left.balanced && right.balanced;
		return res;
	}
	
	private class Result {
		int h;
		boolean balanced;
		
		Result(int h, boolean balanced) {
			this.h = h;
			this.balanced = balanced;
		}

		public Result() {
			// TODO Auto-generated constructor stub
		}
	}
	
	public int height() {
		return height(root);
	}

	private int height(BalancedBinaryTree2<Item>.Node root2) {
		if(root2 == null)
			return -1;
		return 1 + max( height(root2.left), height( root2.right ));
	}
	
	private int max(int a, int b) {
		int result = a > b ?  a :  b;
		return result;
	}

	public static void main(String[] args) {
		BalancedBinaryTree2<Integer> bt = new BalancedBinaryTree2<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		bt.inOrderTraversal(bt.root);
		System.out.println("\nIs balanced ? " + bt.isBalanced(bt.root).balanced);
	}

}
