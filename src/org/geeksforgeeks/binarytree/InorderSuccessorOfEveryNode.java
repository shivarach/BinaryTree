package org.geeksforgeeks.binarytree;

/**
 * Given a Binary Tree where each node has following structure, write a function
 * to populate next pointer for all nodes
 * 
 * T:O(n)
 * S:O(1)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class InorderSuccessorOfEveryNode<Item extends Comparable<Item>> {

	private Node root;
	static InorderSuccessorOfEveryNode.Node tmp = null;

	class Node {
		private Item key;
		private Node left, right, nextInorder;

		public Node(Item key) {
			this.key = key;
		}
	}

	public void insert(Item key) {
		if (key == null)
			return;
		root = insert(root, key);
	}

	private InorderSuccessorOfEveryNode<Item>.Node insert(
			InorderSuccessorOfEveryNode<Item>.Node root2, Item key) {
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

	private void inOrderTraversal(InorderSuccessorOfEveryNode<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public Node setInorderSuccessor(Node x) {
		if (x == null)
			return null;
		setInorderSuccessor(x.left);
		if (tmp != null)
			tmp.nextInorder = x;
		tmp = x;
		setInorderSuccessor(x.right);
		return x;
	}

	public static void main(String[] args) {
		InorderSuccessorOfEveryNode<Integer> bt = new InorderSuccessorOfEveryNode<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		bt.insert(1);
		bt.insert(2);
		bt.insert(4);
		bt.insert(5);
		bt.insert(6);
		bt.insert(7);
		bt.insert(10);
		bt.insert(11);

		bt.inOrderTraversal();
		System.out.println("\nInorder of every node is :");
		InorderSuccessorOfEveryNode<Integer>.Node x = bt
				.setInorderSuccessor(bt.root);

		while (x.left != null)
			x = x.left;
		while (x != null) {
			System.out.print(x.key + " ->");
			x = x.nextInorder;
		}
	}

}
