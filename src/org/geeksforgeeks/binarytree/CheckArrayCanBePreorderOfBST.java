package org.geeksforgeeks.binarytree;

import java.util.Stack;

/**
 * 
 * @author Shiva
 * Check if a given array can represent Preorder Traversal of Binary Search Tree
 * T:O(n)
 * S:O(n)
 * @param <Item>
 */
public class CheckArrayCanBePreorderOfBST {
	
	private Node root;
	
	class Node {
		private int key;
		private Node left, right;
		
		public Node(int key) {
			this.key = key;
		}
	}
	
	public void insert(int key) {
		root = insert(root, key);
	}

	private CheckArrayCanBePreorderOfBST.Node insert(CheckArrayCanBePreorderOfBST.Node root2,
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

	private void inOrderTraversal(CheckArrayCanBePreorderOfBST.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public boolean isArrayCanRepresentPreorder(int pre[]) {
		int root = Integer.MIN_VALUE;
		Stack<Integer> s = new Stack<Integer>();
		for(int i = 0 ; i < pre.length; i++) {
			// in preorder after a root elemtn all elements should be greater
			if(pre[i] < root)
				return false;
			// If pre[i] is in right subtree of stack top,
            // Keep removing items smaller than pre[i]
            // and make the last removed item as new
            // root.
			while(!s.isEmpty() && pre[i] > s.peek()) {
				root = s.peek();
				s.pop();
			}
			// At this point either stack is empty or
            // pre[i] is smaller than root, push pre[i]
            s.push(pre[i]);
		}
		return true;
	}

	public static void main(String[] args) {
		CheckArrayCanBePreorderOfBST bt = new CheckArrayCanBePreorderOfBST();
		int[] arr = {5, 3, 1, 4, 8, 7, 10};
		
		System.out.println(bt.isArrayCanRepresentPreorder(arr));
	}

}
