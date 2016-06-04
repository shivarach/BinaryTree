package org.geeksforgeeks.binarytree;

/**
 * Two trees are identical when they have same data and arrangement of data is also same
 * @author Shiva
 *
 * @param <Item>
 */
public class IdenticalBinaryTrees<Item extends Comparable<Item>> {

	private Node root;

	class Node {
		private Item key;
		private Node left, right;

		public Node(Item key) {
			this.key = key;
		}
	}

	public void insert(Item key) {
		if (key == null)
			return;
		root = insert(root, key);
	}

	private IdenticalBinaryTrees<Item>.Node insert(
			IdenticalBinaryTrees<Item>.Node root2, Item key) {
		if (root2 == null)
			return new Node(key);
		int cmp = key.compareTo(root2.key);
		if (cmp <= 0)
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}
/**
 * Finds whether two binary trees are identical
 * @param root1
 * @param root2
 * @return
 */
	public boolean isIdentical(Node root1, Node root2) {
		if (root1 == null && root2 == null)
			return true;
		else if (root1 != null && root2 != null)
			return ((root1.key.compareTo(root2.key) == 0)
					&& isIdentical(root1.left, root2.left) && isIdentical(
						root1.right, root2.right));
		else
			return false;
	}

	public static void main(String[] args) {
		IdenticalBinaryTrees<Integer> bt1 = new IdenticalBinaryTrees<Integer>();
		bt1.insert(5);
		bt1.insert(3);
		bt1.insert(8);
		bt1.insert(1);
		bt1.insert(4);
		bt1.insert(6);
		bt1.insert(10);
		
		IdenticalBinaryTrees<Integer> bt2 = new IdenticalBinaryTrees<Integer>();
		bt2.insert(5);
		bt2.insert(3);
		bt2.insert(8);
		bt2.insert(1);
		bt2.insert(4);
		bt2.insert(6);
		bt2.insert(10);
		
		IdenticalBinaryTrees<Integer> bt = new IdenticalBinaryTrees<Integer>();
		System.out.println(bt.isIdentical(bt1.root, bt2.root));
	}

}
