package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class PathOfBinaryTree<Item extends Comparable<Item>> {
	
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

	private PathOfBinaryTree<Item>.Node insert(PathOfBinaryTree<Item>.Node root2,
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

	private void inOrderTraversal(PathOfBinaryTree<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void printPaths() {
		printPaths(root, "");
	}

	private void printPaths(Node x, String path) {
		if (x == null)
			return;
		printPaths(x.left, path + " " + x.key);
		printPaths(x.right, path + " " + x.key);
		if (x.left == null && x.right == null)//makes sure intermediate ones are not printed
			System.out.println(path + " " + x.key);
	}

	public static void main(String[] args) {
		PathOfBinaryTree<Integer> bt = new PathOfBinaryTree<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println("\nPaths from root to leaf.....");
		bt.printPaths();
	}

}
