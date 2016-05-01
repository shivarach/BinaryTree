package org.geeksforgeeks.binarytree;

import java.util.LinkedList;
import java.util.Queue;
/**
 * A Tree is typically traversed in two ways:

1) Breadth First Traversal (Or Level Order Traversal)
2) Depth First Traversals
		Inorder Traversal (Left-Root-Right)
		Preorder Traversal (Root-Left-Right)
		Postorder Traversal (Left-Right-Root)
T: O(n) where n is number of nodes in the tree
S: O(n) in worst case(queue required for BFS and recursion stacks for DFS

BFS: Worst case occurs(in terms of space) when tree is perfectly balanced
DFS: Worst case occurs when tree is a skew tree(like linked list)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class BinaryTreeTraversal_1<Item extends Comparable<Item>> {
	private Node root = null;

	class Node {
		private Item data;
		private Node left, right;

		Node(Item data) {
			this.data = data;
		}
	}

	public void insert(Item data) {
		root = insert(root, data);
	}

	private Node insert(BinaryTreeTraversal_1<Item>.Node root, Item data) {
		if (root == null)
			return new Node(data);
		int cmp = data.compareTo(root.data);
		if (cmp <= 0)
			root.left = insert(root.left, data);
		else
			root.right = insert(root.right, data);
		return root;
	}
	
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(BinaryTreeTraversal_1<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.data + " ");
		inOrderTraversal(root.right);
	}
	
	public void preOrderTraversal() {
		preOrderTraversal(root);
	}

	private void preOrderTraversal(BinaryTreeTraversal_1<Item>.Node root) {
		if (root == null) return;
		System.out.print(root.data + " ");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	
	public void postOrderTraversal() {
		postOrderTraversal(root);
	}

	private void postOrderTraversal(BinaryTreeTraversal_1<Item>.Node root) {
		if (root == null) return;
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.print(root.data + " ");
	}
	
	public void levelOrderTraversal() {
		Queue<Node> queue = new LinkedList<BinaryTreeTraversal_1<Item>.Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			System.out.print(tmp.data + " ");
			if (tmp.left != null)
				queue.add(tmp.left);
			if (tmp.right != null)
				queue.add(tmp.right);
		}
	}

	public static void main(String[] args) {
		BinaryTreeTraversal_1<Integer> bt = new BinaryTreeTraversal_1<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println("---> Inorder Traversal");
		bt.preOrderTraversal();
		System.out.println("---> Preorder Traversal");
		bt.postOrderTraversal();
		System.out.println("---> Postorder Traversal");
		bt.levelOrderTraversal();
		System.out.println("---> Levelorder Traversal");
	}

}
