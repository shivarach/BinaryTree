package org.geeksforgeeks.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Shiva
 * Print a Binary Tree in Vertical Order
 *T: O(n)
 *S: O(n)
 * @param <Item>
 */
public class PrintBTVertically<Item extends Comparable<Item>> {
	
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

	private PrintBTVertically<Item>.Node insert(PrintBTVertically<Item>.Node root2,
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

	private void inOrderTraversal(PrintBTVertically<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void printVertically(Node x) {
		HashMap<Integer, List<Item>> map = new HashMap<Integer, List<Item>>();
		printVertically(x, 0, map);
		for(Integer i : map.keySet()) {
			for (Item item : map.get(i)) {
				System.out.print(" " + item);
			}
			System.out.println();
		}
	}

	private void printVertically(PrintBTVertically<Item>.Node x, int i, HashMap<Integer, List<Item>> map) {
		if (x == null)
			return;
		printVertically(x.left, i - 1, map);
		if (map.get(i) == null) {
			ArrayList<Item> itemsInAVerticalLine = new ArrayList<Item>();
			itemsInAVerticalLine.add(x.key);
			map.put(i, itemsInAVerticalLine);
		}
		else {
			map.get(i).add(x.key);
		}
		printVertically(x.right, i + 1, map);
		
	}

	public static void main(String[] args) {
		PrintBTVertically<Integer> bt = new PrintBTVertically<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println();
		bt.printVertically(bt.root);
	}

}
