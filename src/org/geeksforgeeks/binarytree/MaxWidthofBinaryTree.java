package org.geeksforgeeks.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, write a function to get the maximum width of the given
 * tree. Width of a tree is maximum of widths of all levels. Look MaxWidthofBinaryTree2
 * 
 * T: O(n)
 * S: O(n)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class MaxWidthofBinaryTree<Item extends Comparable<Item>> {

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

	private MaxWidthofBinaryTree<Item>.Node insert(
			MaxWidthofBinaryTree<Item>.Node root2, Item key) {
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

	private void inOrderTraversal(MaxWidthofBinaryTree<Item>.Node root) {
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
		int width = 1;
		Queue<Node> q = new LinkedList<Node>();
		q.add(x);
		for (int i = 0; i < h; i++) {
			int qLength = q.size();
			while (qLength > 0) {
				Node tmp = q.poll();
				if (tmp == null)
					break;
				if (tmp.left != null)
					q.add(tmp.left);
				if (tmp.right != null)
					q.add(tmp.right);
				--qLength;
			}
			width = q.size() > width ? q.size() : width;
		}
		return width;
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
		MaxWidthofBinaryTree<Integer> bt = new MaxWidthofBinaryTree<Integer>();
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
