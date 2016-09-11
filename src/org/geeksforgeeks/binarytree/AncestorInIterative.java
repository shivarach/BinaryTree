package org.geeksforgeeks.binarytree;

import java.util.Stack;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class AncestorInIterative<Item extends Comparable<Item>> {
	
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

	private AncestorInIterative<Item>.Node insert(AncestorInIterative<Item>.Node root2,
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

	private void inOrderTraversal(AncestorInIterative<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void printAncestors(Node x, Item target) {
		if (x == null)
			return;
		Stack<Node> stack = new Stack<Node>();
		while (true) {
			// push all the left items until target is met
			while (x != null && x.key != target) {
				stack.push(x);
				x = x.left;
			}
			if (x != null && x.key == target)
				break;
			if (stack.peek().right == null) {
				x = stack.pop();
				//coming back from leaf to up
				while (!stack.isEmpty() && stack.peek().right == x)
					x = stack.pop();
			}
			x = stack.isEmpty() ? null : stack.peek().right;
		}
		while (!stack.isEmpty())
			System.out.print(" " + stack.pop().key);
	}

	public static void main(String[] args) {
		AncestorInIterative<Integer> bt = new AncestorInIterative<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		bt.inOrderTraversal();
		
		System.out.print("\nAncestors: ");
		bt.printAncestors(bt.root, 10);
		
		
	}

}
