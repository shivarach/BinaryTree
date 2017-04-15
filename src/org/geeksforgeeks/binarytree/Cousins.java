package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 * T: O(n)
 * @param <Item>
 */
public class Cousins<Item extends Comparable<Item>> {
	
	private Node root;
	private int level;
	
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

	private Cousins<Item>.Node insert(Cousins<Item>.Node root2,
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

	private void inOrderTraversal(Cousins<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
		
	}
	
	public boolean areCousins(Node x, Item a, Item b) {
		if (x.key.equals(a) || x.key.equals(b))
			return false;
		return areCousins(x, a, b, 0);
	}

	private boolean areCousins(Cousins<Item>.Node x, Item a, Item b, int currentLev) {
		if ( x == null || x.left == null || x.right == null)
			return false;
		if (x.left.key.equals(a)) {
			if(x.right.key.equals(b))
				return false;
			if (level != 0) {
				return level == currentLev ? true : false;
			}
			else level = currentLev;
		}
		else if (x.right.key.equals(a)) {
			if(x.left.key.equals(b))
				return false;
			if (level != 0) {
				return level == currentLev ? true : false;
			}
			else level = currentLev;
		}
		else if (x.left.key.equals(b)) {
			if(x.right.key.equals(a))
				return false;
			if (level != 0) {
				return level == currentLev ? true : false;
			}
			else level = currentLev;
		}
		else if (x.right.key.equals(b)) {
			if(x.left.key.equals(a))
				return false;
			if (level != 0) {
				if (level == currentLev)
					return true;
				else
					return false;
			}
			else level = currentLev;
		}
		return areCousins(x.left, a, b, currentLev + 1) ||
				areCousins(x.right, a, b, currentLev + 1);
	}
	
/*	private int checkLevels(int level, int currentLevel) {
		if (level != 0) {
			if (level == currentLevel)
				return 1;
			else
				return 0;
		}
		else level = currentLev;
	}*/

	public static void main(String[] args) {
		Cousins<Integer> bt = new Cousins<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.print("\nAre cousins : ");
		System.out.println(bt.areCousins(bt.root, 1, 6));
	}

}
