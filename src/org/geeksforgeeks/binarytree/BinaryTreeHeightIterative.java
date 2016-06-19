package org.geeksforgeeks.binarytree;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class BinaryTreeHeightIterative<Item extends Comparable<Item>> {
	
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

	private BinaryTreeHeightIterative<Item>.Node insert(BinaryTreeHeightIterative<Item>.Node root2,
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
/**
 * Height of a tree is number of edges from root to deepest leaf
 * @param x
 * @return
 */
	private int height(Node x) {
		if(x == null || (x.left == null && x.right == null))
			return 0;
		Queue<Node> q = new LinkedList<Node>();
		q.add(x);
		int height = -1;
		
		while(true) {
			int nodeCount = q.size();
			if (nodeCount <= 0)
				return height;
			else height++;
			//remove all the nodes in one level from queue and add their children
			while(nodeCount > 0) {
				Node tmp = q.poll();
				if(tmp.left != null)
					q.add(tmp.left);
				if(tmp.right != null)
					q.add(tmp.right);
				nodeCount--;
			}
		}
	}


	public static void main(String[] args) {
		BinaryTreeHeightIterative<Integer> bt = new BinaryTreeHeightIterative<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		System.out.println("height :" + bt.height());
	}

}
