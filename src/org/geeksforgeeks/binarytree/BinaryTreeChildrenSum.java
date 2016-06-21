package org.geeksforgeeks.binarytree;

/**
 * Given a binary tree, write a function that returns true if the tree satisfies
 * below property.
 * 
 * For every node, data value must be equal to sum of data values in left and
 * right children. Consider data value as 0 for NULL children.
 * 
 * T:O(n)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class BinaryTreeChildrenSum {

	private Node root;

	class Node {
		private Integer key;
		private Node left, right;

		public Node(Integer key) {
			this.key = key;
		}
	}

	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	public boolean isChildrenSumProperty(Node x) {
		if (x == null || (x.left == null && x.right == null))
			return true;
		int leftSum = 0, rightSum = 0;
		;
		if (x.left != null)// if null 0 is used
			leftSum = x.left.key;
		if (x.right != null)
			rightSum = x.right.key;

		if ((x.key == leftSum + rightSum) && isChildrenSumProperty(x.left)
				&& isChildrenSumProperty(x.right))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		BinaryTreeChildrenSum tree = new BinaryTreeChildrenSum();
		tree.root = tree.new Node(10);
		tree.root.left = tree.new Node(8);
		tree.root.right = tree.new Node(2);
		tree.root.left.left = tree.new Node(3);
		tree.root.left.right = tree.new Node(5);
		tree.root.right.right = tree.new Node(2);

		tree.inOrderTraversal();
		if (tree.isChildrenSumProperty(tree.root))
			System.out.println("\nChildren sum property satisfied.");
		else
			System.out.println("\nChildren sum property not satisfied. ");
	}

}
