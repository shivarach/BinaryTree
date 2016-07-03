package org.geeksforgeeks.binarytree;


/**
 * Given a binary tree, write a function to get the maximum width of the given
 * tree. Width of a tree is maximum of widths of all levels. Look MaxWidthofBinaryTree2
 * 
 * T: O(n)
 * S: O(log n)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class MaxWidthofBinaryTree_2<Item extends Comparable<Item>> {

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

	private MaxWidthofBinaryTree_2<Item>.Node insert(
			MaxWidthofBinaryTree_2<Item>.Node root2, Item key) {
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

	private void inOrderTraversal(MaxWidthofBinaryTree_2<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public int maxWidth(Node x) {
		if (x == null)
			return 0;
		int h = height(x);
		int[] countArray = new int[h + 1];
		findNodesCountAtEachlevel(x, countArray, 0);
		return findMax(countArray);
	}

	private int findMax(int[] countArray) {
		int max = 0;
		for(int i : countArray)
			max = max > i ? max : i;
		return max;
	}

	private void findNodesCountAtEachlevel(Node x,
			int[] countArray, int level) {
		if (x != null) {
			countArray[level]++;
			if(x.left != null)
				findNodesCountAtEachlevel(x.left, countArray, level + 1);
			if(x.right != null)
				findNodesCountAtEachlevel(x.right, countArray, level + 1);
		}
		
	}

	private int height(Node x) {
		if (x == null)
			return -1;
		return 1 + max(height(x.left), height(x.right));
	}

	private int max(int height, int height2) {
		int res = height > height2 ? height : height;
		return res;
	}

	public static void main(String[] args) {
		MaxWidthofBinaryTree_2<Integer> bt = new MaxWidthofBinaryTree_2<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);

		bt.inOrderTraversal();
		System.out.println("\nmax width is : " + bt.maxWidth(bt.root));
	}

}
