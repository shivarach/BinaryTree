package org.geeksforgeeks.binarytree;

/**
 * Write a function that returns true if the given Binary Tree is SumTree else
 * false. A SumTree is a Binary Tree where the value of a node is equal to sum
 * of the nodes present in its left subtree and right subtree. An empty tree is
 * SumTree and sum of an empty tree can be considered as 0. A leaf node is also
 * considered as SumTree.
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class SumTree {

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

	private SumTree.Node insert(SumTree.Node root2, int key) {
		if (root2 == null)
			return new Node(key);
		if (key <= root2.key)
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}

	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(SumTree.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	class Result {
		int sum;
		private boolean isSumTree;
		Result(){
		
		}
		Result(int sum, boolean st) {
			this.sum = sum;
			this.isSumTree = st;
		}
	}
	
	public boolean isSumTree() {
		Result result = isSumTree(this.root);
		return result.isSumTree;
	}

	private SumTree.Result isSumTree(SumTree.Node x) {
		if(x == null)
			return new Result(0, true);
		if(x.left == null && x.right == null)
			return new Result(x.key, true);
		
		Result l = isSumTree(x.left);
		Result r = null;
		if(l.isSumTree)
			r = isSumTree(x.right);
		
		//if l or r is not sum tree
		if(r == null || !r.isSumTree)
			//since not sum tree sum doesn't matter(u can pass anything instead of 0)
			return new Result(0, false);
		//when control comes here it means both l and r are sum trees
		Result res = new Result();
		if(x.key == l.sum + r.sum) {
			res.isSumTree = true;
			res.sum = 2 * x.key;
		}
		else 
			res.isSumTree = false;
		return res;
	}

	public static void main(String[] args) {
		SumTree bt = new SumTree();
		bt.insert(26);
		bt.root.left = bt.new Node(10);
		bt.root.right = bt.new Node(3);
		bt.root.left.left = bt.new Node(4);
		bt.root.left.right = bt.new Node(6);
		bt.root.right.right = bt.new Node(3);

		bt.inOrderTraversal();
		System.out.println("Is sum Tree ? " + bt.isSumTree());
		
	}

}
