package org.geeksforgeeks.binarytree;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class BinaryTreeHeight<Item extends Comparable<Item>> {
	
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

	private BinaryTreeHeight<Item>.Node insert(BinaryTreeHeight<Item>.Node root2,
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
	
	public int height() {
		return height(root);
	}

	private int height(BinaryTreeHeight<Item>.Node root2) {
		if(root2 == null)
			return -1;
		return 1 + max( height(root2.left), height( root2.right ));
	}
	
	private int max(int a, int b) {
		int result = a > b ?  a :  b;
		return result;
	}

	public static void main(String[] args) {
		BinaryTreeHeight<Integer> bt = new BinaryTreeHeight<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		System.out.println(bt.height());
	}

}
