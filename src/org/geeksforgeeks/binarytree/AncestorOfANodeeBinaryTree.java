package org.geeksforgeeks.binarytree;

/**
 * Given a Binary Tree and a key, write a function that prints all the ancestors of the key in the given binary tree.
 * T: O(N)
 * @author Shiva
 *
 * @param <Item>
 */
public class AncestorOfANodeeBinaryTree<Item extends Comparable<Item>> {
	
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

	private AncestorOfANodeeBinaryTree<Item>.Node insert(AncestorOfANodeeBinaryTree<Item>.Node root2,
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

	private void inOrderTraversal(AncestorOfANodeeBinaryTree<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	public boolean printAncestors(Node x, Item target) {
		if (x == null)
			return false;
		if(x.key.equals(target))
			return true;
		if(printAncestors(x.left, target) || printAncestors(x.right, target)) {
			System.out.println(x.key);
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		AncestorOfANodeeBinaryTree<Integer> bt = new AncestorOfANodeeBinaryTree<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		bt.insert(11);
		bt.insert(12);
		bt.insert(13);
		
		bt.inOrderTraversal();
		System.out.println();
		System.out.println("\nAncestors are : ");
		bt.printAncestors(bt.root, 13);
	}

}
