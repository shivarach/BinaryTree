package org.geeksforgeeks.binarytree;

/**
 * it replaces key
 * @author Shiva
 *
 * @param <Item>
 */
public class ReverseAlternateLevelsOfPerfectBT<Item extends Comparable<Item>> {
	
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

	private ReverseAlternateLevelsOfPerfectBT<Item>.Node insert(ReverseAlternateLevelsOfPerfectBT<Item>.Node root2,
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

	private void inOrderTraversal(ReverseAlternateLevelsOfPerfectBT<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void reverseAlternateLevels(Node root) {
		reverseAlternateLevels(root.left, root.right, 0);
	}
	
	private void reverseAlternateLevels(ReverseAlternateLevelsOfPerfectBT<Item>.Node left,
			ReverseAlternateLevelsOfPerfectBT<Item>.Node right, int level) {
		if(left == null || right == null)
			return;
		if (level % 2 == 0) {
			Item tmp = left.key;
			left.key = right.key;
			right.key = tmp;
		}
		reverseAlternateLevels(left.left, right.right, level + 1);
		reverseAlternateLevels(left.right, right.left, level + 1);
		
	}

	public static void main(String[] args) {
		ReverseAlternateLevelsOfPerfectBT<Integer> bt = new ReverseAlternateLevelsOfPerfectBT<Integer>();
		bt.insert(50);
		bt.insert(30);
		bt.insert(80);
		bt.insert(10);
		bt.insert(40);
		bt.insert(60);
		bt.insert(100);
		bt.insert(0);
		bt.insert(20);
		bt.insert(35);
		bt.insert(45);
		bt.insert(55);
		bt.insert(70);
		bt.insert(90);
		bt.insert(110);
		System.out.println("Actual: ");
		bt.inOrderTraversal();
		
		System.out.println("\nAfter reversal: ");
		bt.reverseAlternateLevels(bt.root);
		bt.inOrderTraversal();
		
	}

}
