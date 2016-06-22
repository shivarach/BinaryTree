package org.geeksforgeeks.binarytree;

/**
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/ The diameter of a
 * tree (sometimes called the width) is the number of nodes on the longest path
 * between two leaves in the tree. There may be more than one path with the same
 * diameter which may not include root. The diameter of a tree T is the largest
 * of the following quantities:
 * 
 * the diameter of T’s left subtree the diameter of T’s right subtree the
 * longest path between leaves that goes through the root of T (this can be
 * computed from the heights of the subtrees of T)
 * 
 * T: O(n)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class BinaryTreeDiameter2<Item extends Comparable<Item>> {

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

	private BinaryTreeDiameter2<Item>.Node insert(Node root2, Item key) {
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

	private void inOrderTraversal(BinaryTreeDiameter2<Item>.Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public int findDiameter(Node x) {
		return findDiameter1(x).maxPath;
	}

	private Result findDiameter1(Node x) {
		if (x == null)
			return new Result(-1, 0);

		Result left = findDiameter1(x.left);
		Result right = findDiameter1(x.right);

		int h = max(left.h, right.h) + 1;
		int maxPath = max(max(left.maxPath, right.maxPath),
				(left.h + right.h + 2));
		return new Result(h, maxPath);
	}

	private class Result {
		int h;
		int maxPath;// diameter

		Result(int h, int maxPath) {
			this.h = h;
			this.maxPath = maxPath;
		}

	}

	private int max(int a, int b) {
		int res = a > b ? a : b;
		return res;
	}

	public static void main(String[] args) {
		BinaryTreeDiameter2<Integer> bt = new BinaryTreeDiameter2<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		bt.insert(11);

		bt.inOrderTraversal();
		System.out.println("\nDiameter is : " + bt.findDiameter(bt.root));
	}

}
