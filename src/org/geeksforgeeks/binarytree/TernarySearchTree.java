package org.geeksforgeeks.binarytree;

/**
 * A ternary search tree is a special trie data structure where the child nodes
 * of a standard trie are ordered as a binary search tree.
 * 
 * Insert: L + O(N) Search Hit: L + O(N) Search Miss: O(N) Space: O(N) where L
 * is length of the key and N is number of nodes/chars in the tree
 * 
 * @author Shiva
 *
 * @param <Value>
 */
public class TernarySearchTree<Value> {

	private Node root;

	class Node {
		private char ch;
		private Value val;
		private Node left, mid, right;

	}

	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}

	private TernarySearchTree<Value>.Node put(TernarySearchTree<Value>.Node x, String key, Value val, int i) {
		char ch = key.charAt(i);
		if (x == null) {
			x = new Node();
			x.ch = ch;
		}
		if (ch < x.ch)
			x.left = put(x.left, key, val, i);
		else if (ch > x.ch)
			x.right = put(x.right, key, val, i);
		else if (i < key.length() - 1)// middle node
			x.mid = put(x.mid, key, val, i + 1);
		else
			x.val = val;
		return x;
	}

	public Value get(String key) {
		return get(root, key, 0);
	}

	private Value get(TernarySearchTree<Value>.Node x, String key, int i) {
		if (x == null || key == null || key.length() == 0)
			return null;
		char ch = key.charAt(i);
		if (ch < x.ch)
			return get(x.left, key, i);
		else if (ch > x.ch)
			return get(x.right, key, i);
		else if (i < key.length() - 1)
			return get(x.mid, key, i + 1);
		else
			return x.val;
	}

	public static void main(String[] args) {
		TernarySearchTree<Integer> bt = new TernarySearchTree<Integer>();
		bt.put("she", 0);
		bt.put("sells", 1);
		bt.put("sea", 2);
		bt.put("shells", 3);
		bt.put("by", 4);
		bt.put("the", 5);
		bt.put("sea", 6);
		bt.put("shore", 7);

		System.out.println(bt.get("shore"));
	}

}
