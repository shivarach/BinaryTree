package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class Subtree<Item extends Comparable<Item>> {
	
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

	private Subtree<Item>.Node insert(Subtree<Item>.Node root2,
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

	private void inOrderTraversal(Subtree<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public boolean isSubTree(Node t, Node s) {
		if (s == null)
			return true;
		Node tmp = findSubTreeRootNodeInTree(t, s);
		if (tmp == null)
			return false;
		return areIdentical(tmp, s);
	}
	
	public Node findSubTreeRootNodeInTree(Node x, Node rootS) {
		if (x == null)
			return null;
		if(x.key.equals(rootS.key))
			return x;
		Node left = findSubTreeRootNodeInTree(x.left, rootS);
		if(left == null)
			findSubTreeRootNodeInTree(x.right, rootS);
		return left;
	}

	private boolean areIdentical(Subtree<Item>.Node tmp, Subtree<Item>.Node s) {
		if (tmp == null || s == null) {
			return tmp == null && s == null;
		}
		return tmp.key.equals(s.key) && areIdentical(tmp.left, s.left) && areIdentical(tmp.right, s.right);
	}

	public static void main(String[] args) {
		Subtree<Integer> bt = new Subtree<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		System.out.print("Tree: ");
		bt.inOrderTraversal();
		
		Subtree<Integer> bt1 = new Subtree<Integer>();
		bt1.insert(5);
		bt1.insert(3);
		bt1.insert(8);
		bt1.insert(1);
		bt1.insert(4);
		bt1.insert(6);
		bt1.insert(10);
		System.out.print("\nSub tree: ");
		bt1.inOrderTraversal();
		
		System.out.print("\nIs 'S' is subtree of 'T' : ");
		System.out.println(bt.isSubTree(bt.root, bt1.root));
		System.out.println();
	}

}
