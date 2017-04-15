package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *T: O(n)
 * @param <Item>
 */
public class MaxPathSumBetweenAnyTwoLeafs {
	
	private Node root;
	
	class Node {
		private int key;
		private Node left, right;
		
		public Node(int key) {
			this.key = key;
		}
	}
	class Result {
		int maxSum;
		public Result(int i) {
			this.maxSum = i;
		}
	}
	
	public void insert(int key) {
		root = insert(root, key);
	}

	private MaxPathSumBetweenAnyTwoLeafs.Node insert(MaxPathSumBetweenAnyTwoLeafs.Node root2,
			int key) {
		if (root2 == null)
			return new Node(key);
		if(key <= root2.key)
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}
	
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(MaxPathSumBetweenAnyTwoLeafs.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public int maxPath(Node x) {
		if(x.left == null || x.right == null)
			return Integer.MIN_VALUE;
		Result res = new Result(0);
		maxPath(x, res);
		return res.maxSum;
	}

	private int maxPath(MaxPathSumBetweenAnyTwoLeafs.Node x, Result res) {
		if (x == null)
			return 0;
		if (x.left == null && x.right == null)
			return x.key;
		int ls = maxPath(x.left, res);
		int rs = maxPath(x.right, res);

		// update sum
		int currentSum = ls + rs + x.key;
		if (res.maxSum < currentSum)
			res.maxSum = currentSum;
		return ls > rs ? (ls + x.key) : (rs + x.key);

	}

	public static void main(String[] args) {
		MaxPathSumBetweenAnyTwoLeafs bt = new MaxPathSumBetweenAnyTwoLeafs();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println("\nmax path distance between two leaf nodes is : " + bt.maxPath(bt.root));
	}

}
