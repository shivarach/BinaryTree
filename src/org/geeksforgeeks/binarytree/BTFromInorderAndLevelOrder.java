package org.geeksforgeeks.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * Look http://www.geeksforgeeks.org/construct-tree-inorder-level-order-traversals/ which is easier to understand eventhough tc is O(n^3)
 * @author Shiva
 *T: O(n^2)
 *S: O(n)
 * @param <Item>
 */
public class BTFromInorderAndLevelOrder<Item extends Comparable<Item>> {
	
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

	private BTFromInorderAndLevelOrder<Item>.Node insert(BTFromInorderAndLevelOrder<Item>.Node root2,
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

	private void inOrderTraversal(BTFromInorderAndLevelOrder<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void buildTree(Item[] in, Item[] level, int l, int r) {
		Map<Item, Integer> map = new HashMap<Item, Integer>();
		for (int i = 0; i < level.length; i++)
			map.put(level[i], i);
		root = buildTree(in, level, l, r, map);
	}
	private Node buildTree(Item[] in, Item[] level, int l, int r, Map<Item, Integer> map) {
		if(l > r) return null;
		int min = Integer.MAX_VALUE, i = 0;
		Item val = null;
		for(int j = l; j <= r ; j++ ) {
			if(map.get(in[j]) < min) {
				val = in[j];
				min = map.get(in[j]);
				i = j;
			}
		}
		Node x = new Node(val);
		x.left = buildTree(in, level, l, i - 1, map);
		x.right = buildTree(in, level, i + 1, r, map);
		return x;
	}
	

	public static void main(String[] args) {
		BTFromInorderAndLevelOrder<Integer> bt = new BTFromInorderAndLevelOrder<Integer>();
		/*bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);*/
		Integer[] lev = /*{2, 1, 3};*/{5, 3, 8, 1, 4, 6, 10};
		Integer[] in = /*{1, 2, 3};*/{1, 3, 4, 5, 6, 8, 10};
		bt.buildTree(in, lev, 0, lev.length - 1);
		
		bt.inOrderTraversal();
	}

}
