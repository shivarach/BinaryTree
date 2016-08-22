package org.geeksforgeeks.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class CompletBTCreation<Item extends Comparable<Item>> {
	
	private Node root;
	
	private Queue<Node> q = new LinkedList<Node>();
	
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
		if (q.isEmpty()) {
			this.root = new Node(key);
			q.add(this.root);
			return;
		}
		Node tmp = q.peek();//retrieves head
		if (tmp.left == null) {
			tmp.left = new Node(key);
			q.add(tmp.left);
		}
		else {
			tmp.right = new Node(key);
			q.add(tmp.right);
			// removes head of the queue since it is full(means has left and right children)
			q.poll();
		}
	}

	private CompletBTCreation<Item>.Node insert(CompletBTCreation<Item>.Node root2,
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

	private void inOrderTraversal(CompletBTCreation<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	// test complete binary tree
	public boolean isComplete(Node x) {
		if (x == null)
			return true;
		Queue<Node> q = new LinkedList<Node>();
		q.add(x);
		return isComplete(q);
	}

	private boolean isComplete(Queue<Node> q) {
		while (!q.isEmpty()) {
			Node x = q.poll();
			// check whether x is not full
			if (!(x.left != null && x.right != null)) {
				// if x has any child, it should be left otherwise it's not
				// complete
				if (x.right != null)
					return false;
				return validateLeafNodes(x, q);
			}
			q.add(x.left);
			q.add(x.right);
		}
		return true;
	}

	// return true if all the nodes in queue are leaf nodes
	private boolean validateLeafNodes(Node x, Queue<Node> q) {
		// all this nodes must be leaf nodes otherwise it's not complete
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			if (!(tmp.left == null && tmp.right == null)) {
				return false;
			}
		}
		return true;
	}
	

	public static void main(String[] args) {
		CompletBTCreation<Integer> bt = new CompletBTCreation<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println("\nIs complete : " + bt.isComplete(bt.root));
	}

}
