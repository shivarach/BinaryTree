package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class DistanceBetweenTwoKeys<Item extends Comparable<Item>> {
	
	private Node root;
	
	private boolean a1, b1;
	
	private int leftKeyDistance, rightKeyDistance, distance;
	
	
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

	private DistanceBetweenTwoKeys<Item>.Node insert(DistanceBetweenTwoKeys<Item>.Node root2,
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

	private void inOrderTraversal(DistanceBetweenTwoKeys<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public int distanceBetweenAandB(Node x, Item a, Item b) {
		findLCAUtil(x, a, b, 0);
		if (a1 && b1)//both keys a and b are present
			return distance;
		return -1;
	}

	private DistanceBetweenTwoKeys<Item>.Node findLCAUtil(DistanceBetweenTwoKeys<Item>.Node x, Item a, Item b, int currentKeyDistance) {
		Node lca = null;
		if (x == null)
			return null;
		if (x.key.equals(a)) {
			a1 = true;//used to check the key a is present
			lca = x;
			leftKeyDistance = currentKeyDistance;
		}
		if (x.key.equals(b)) {
			b1 = true;//used to check the key b is present
			lca = x;
			rightKeyDistance = currentKeyDistance;
		}
		Node lcaLeft = findLCAUtil(x.left, a, b, currentKeyDistance + 1);
		Node lcaRight = findLCAUtil(x.right, a, b, currentKeyDistance + 1);
		
		if ((lcaLeft != null && lcaRight != null) || lca != null) {
			lca = x;
			distance = (leftKeyDistance - currentKeyDistance) + (rightKeyDistance - currentKeyDistance);
		}
		else lca = lcaLeft != null ? lcaLeft : lcaRight;
		return lca;
	}

	public static void main(String[] args) {
		DistanceBetweenTwoKeys<Integer> bt = new DistanceBetweenTwoKeys<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		int a = 4;
		int b = 6;
		System.out.println("\ndistance between " + a + " and " + b + " is : ");
		System.out.println(bt.distanceBetweenAandB(bt.root, a, b));
	}

}
