package org.geeksforgeeks.binarytree;

/**
 * Write a function to print spiral order traversal of a tree.
 * T:O(n^2) where n is no. of nodes
 * @author Shiva
 *
 * @param <Item>
 */
public class LevelOrderTraversalInSpiral<Item extends Comparable<Item>> {
	
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

	private LevelOrderTraversalInSpiral<Item>.Node insert(LevelOrderTraversalInSpiral<Item>.Node root2,
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

	private void inOrderTraversal(LevelOrderTraversalInSpiral<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void levelOrderTraversalInSpiral() {
		int h = findHeight(root);
		for (int i = 0 ; i <= h; i++) {
			// even level traversed from right to left and vice versa
			printGivenLevel(root, i, (i % 2 == 0));
			System.out.println();
		}
			
	}

	private void printGivenLevel(LevelOrderTraversalInSpiral<Item>.Node x,
			int level, boolean isRightToLeft) {
		if (x == null)
			return;
		if(level == 0) {
			System.out.print(x.key + " ");
			return;
		}
		else {
			if(isRightToLeft) {// even level traversed from right to left
				printGivenLevel(x.right, level - 1, isRightToLeft);
				printGivenLevel(x.left, level - 1, isRightToLeft);
			}
			else {// odd level traversed from left to right
				printGivenLevel(x.left, level - 1, isRightToLeft);
				printGivenLevel(x.right, level - 1, isRightToLeft);
			}
		}
		
	}

	private int findHeight(LevelOrderTraversalInSpiral<Item>.Node x) {
		if(x == null)
			return -1;
		return 1 + max(findHeight(x.left), findHeight(x.right));
	}

	private int max(int a, int b) {
		int result = a > b ? a : b;
		return result;
	}

	public static void main(String[] args) {
		LevelOrderTraversalInSpiral<Integer> bt = new LevelOrderTraversalInSpiral<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println("\nLevel order traversal in spiral way: ");
		bt.levelOrderTraversalInSpiral();
	}

}
