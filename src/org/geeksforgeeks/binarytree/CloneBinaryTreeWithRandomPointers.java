package org.geeksforgeeks.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Shiva
 *	Clone a Binary Tree with Random Pointers. It uses hashmap to clone the tree. This method
 *doesn't modify the original tree. Look another method in geeksforgeeks which is a bit complex.
 * @param <Item>
 */
public class CloneBinaryTreeWithRandomPointers<Item extends Comparable<Item>> {
	
	private Node root;
	
	class Node {
		private Item key;
		private Node left, right, random;
		
		public Node(Item key) {
			this.key = key;
		}
	}
	
	public void insert(Item key) {
		if(key == null)
			return;
		root = insert(root, key);
	}

	private CloneBinaryTreeWithRandomPointers<Item>.Node insert(CloneBinaryTreeWithRandomPointers<Item>.Node root2,
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

	private void inOrderTraversal(CloneBinaryTreeWithRandomPointers<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		//print key of current node and random
		Item randomKey = root.random == null ? null : root.random.key;
		System.out.print("[" + root.key + ", " + randomKey + "]");
		inOrderTraversal(root.right);
	}
	
	public Node clone(Node x) {
		Map<Node, Node> mapOfActualAndClone = new HashMap<Node, Node>();
		Node clonedRoot = cloneWithOutRandomPointers(x, mapOfActualAndClone);
		setRandomPointers(x, clonedRoot, mapOfActualAndClone);
		return clonedRoot;
	}

	private void setRandomPointers(Node x, Node clonedRoot, Map<Node, Node> mapOfActualAndClone) {
		for (Node actualNode : mapOfActualAndClone.keySet()) {
			mapOfActualAndClone.get(actualNode).random = actualNode.random;
		}
	}

	private Node cloneWithOutRandomPointers(Node x, Map<Node, Node> mapOfActualAndClone) {
		if (x == null)
			return null;
		Node cloneNode = new Node(x.key);
		mapOfActualAndClone.put(x, cloneNode);
		cloneNode.left = cloneWithOutRandomPointers(x.left, mapOfActualAndClone);
		cloneNode.right = cloneWithOutRandomPointers(x.right, mapOfActualAndClone);
		return cloneNode;
	}

	public static void main(String[] args) {
		CloneBinaryTreeWithRandomPointers<Integer> bt = new CloneBinaryTreeWithRandomPointers<Integer>();
		CloneBinaryTreeWithRandomPointers<Integer>.Node root = bt.new Node(5);
		root.left = bt.new Node(3);
		root.right = bt.new Node(8);
		root.left.left = bt.new Node(1);
		root.left.right = bt.new Node(4);
		root.right.left = bt.new Node(6);
		root.right.right = bt.new Node(10);
		
		//setting random pointers
		root.random = root.right;
		root.left.random = root;
		root.left.left.random = root.right.right;
		root.right.random = root.right.left;
		root.right.left.random = root.left.right;
		
		System.out.print("Original Tree : ");
		bt.inOrderTraversal(root);
		
		System.out.print("\nCloned Tree : ");
		bt.inOrderTraversal(bt.clone(root));
	}

}
