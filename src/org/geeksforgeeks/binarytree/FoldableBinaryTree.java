package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class FoldableBinaryTree<Item extends Comparable<Item>> {
	
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

	private FoldableBinaryTree<Item>.Node insert(FoldableBinaryTree<Item>.Node root2,
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

	private void inOrderTraversal(FoldableBinaryTree<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public boolean isFoldable(Node x) {
		if (x == null)
			return true;
		return isFoldable(x.left, x.right);
	}

	private boolean isFoldable(Node left, Node right) {
		if (left == null && right == null)
			return true;
		if (left == null || right == null)
			return false;
		return isFoldable(left.left, right.right) &&
			isFoldable(left.right, right.left);
	}

	public static void main(String[] args) {
		FoldableBinaryTree<Integer> bt = new FoldableBinaryTree<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println("\nisFoldable : " + bt.isFoldable(bt.root));
	}

}
