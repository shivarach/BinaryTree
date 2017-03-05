package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class LowestCommonAncestor<Item extends Comparable<Item>> {
	
	private Node root;
	
	private boolean a1, b1;
	
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

	private LowestCommonAncestor<Item>.Node insert(LowestCommonAncestor<Item>.Node root2,
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

	private void inOrderTraversal(LowestCommonAncestor<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public Node findLCA(Node x, Item a, Item b) {
		Node lca = findLCAUtil(x, a, b);
		if (a1 && b1)//both keys a and b are present
			return lca;
		return null;
	}

	private LowestCommonAncestor<Item>.Node findLCAUtil(LowestCommonAncestor<Item>.Node x, Item a, Item b) {
		Node lca = null;
		if (x == null)
			return null;
		if (x.key.equals(a)) {
			a1 = true;
			lca = x;
		}
		if (x.key.equals(b)) {
			b1 = true;
			lca = x;
		}
		Node lcaLeft = findLCAUtil(x.left, a, b);
		Node lcaRight = findLCAUtil(x.right, a, b);
		
		if ((lcaLeft != null && lcaRight != null) || lca != null)
			return x;
		else 
			return lcaLeft != null ? lcaLeft : lcaRight;
	}

	public static void main(String[] args) {
		LowestCommonAncestor<Integer> bt = new LowestCommonAncestor<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		int a = 5;
		int b = 3;
		System.out.println("\nLowest common ancestor of " + a + " and " + b + " is : ");
		LowestCommonAncestor<Integer>.Node result = bt.findLCA(bt.root, a, b);
		System.out.println(result.key);
	}

}
