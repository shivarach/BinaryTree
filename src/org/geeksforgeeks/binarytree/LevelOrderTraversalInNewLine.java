package org.geeksforgeeks.binarytree;

import java.util.LinkedList;
import java.util.Queue;


/**
 * First have a look at {@BinaryTreeHeightIterative}
 * Level Order traversal in new lines
 * T:O(n)
 * @author Shiva
 *
 * @param <Item>
 */
public class LevelOrderTraversalInNewLine<Item extends Comparable<Item>> {
	
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

	private Node insert(Node root2,
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

	public void levelOrderTraversalInNewLine() {
		levelOrderTraversalInNewLine(root);
	}
	private void levelOrderTraversalInNewLine(Node x) {
			if(x == null || (x.left == null && x.right == null))
				return;
			Queue<Node> q = new LinkedList<Node>();
			q.add(x);
			
			while(true) {
				int nodeCount = q.size();
				if (nodeCount <= 0)
					return;
				//remove all the nodes in one level from queue and add their children
				while(nodeCount > 0) {
					Node tmp = q.poll();
					System.out.print(tmp.key + " ");
					if(tmp.left != null)
						q.add(tmp.left);
					if(tmp.right != null)
						q.add(tmp.right);
					nodeCount--;
				}
				System.out.println();
			}
		}

	public static void main(String[] args) {
		LevelOrderTraversalInNewLine<Integer> bt = new LevelOrderTraversalInNewLine<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.levelOrderTraversalInNewLine();
	}

}
