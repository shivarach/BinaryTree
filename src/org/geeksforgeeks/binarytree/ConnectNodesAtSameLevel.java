package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class ConnectNodesAtSameLevel<Item extends Comparable<Item>> {
	
	private Node root;
	
	class Node {
		private Item key;
		private Node left, right, next;
		
		public Node(Item key) {
			this.key = key;
		}
	}
	
	public void insert(Item key) {
		if(key == null)
			return;
		root = insert(root, key);
	}

	private ConnectNodesAtSameLevel<Item>.Node insert(ConnectNodesAtSameLevel<Item>.Node root2,
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

	private void inOrderTraversal(ConnectNodesAtSameLevel<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	//iterative solution - it is easier to understand
	public void sameLevelNodes(Node root) {
		if (root == null)
			return;
		Node p = root;
		// p moves through every level and points to first node in every level any time
		while( p != null) {
			Node q = p;
			// q moves through all nodes in a level pointed by p and connects them
			while (q != null) {
				//connect left child(if available) to next available node in the child node level
				if (q.left != null) {
					if (q.right != null)
						q.left.next = q.right;
					else // right child is null
						q.left.next = getNextRightChild(q);
				}
				//connect right child(if available) to next available node in the child node level
				if (q.right != null) {
					q.right.next = getNextRightChild(q);
				}
				q = q.next;
			}
			//now move p down the next level
			if (p.left != null)
				p = p.left;
			else if (p.right != null)
				p = p.right;
			else
				p = getNextRightChild(p);
		}
	}

	// returns the q's next child node available, here q moves next node in same level until a child node is found
	private Node getNextRightChild(Node q) {
		Node tmp = q.next;
		while (tmp != null) {
			if (tmp.left != null)
				return tmp.left;
			if (tmp.right != null)
				return tmp.right;
			tmp = tmp.next;
		}
		return null;
	}

	public void printUsingNextPointer(Node x) {
		while (x != null) {
			Node y = x;
			while (y != null) {
				System.out.print(y.key + " ");
				y = y.next;
			}
			System.out.println();
			if (x.left != null) {
				x = x.left;
				continue;
			} else if (x.right != null) {
				x = x.right;
				continue;
			} else {
				x = getNextRightChild(x);
				continue;
			}

		}

	}
	public static void main(String[] args) {
		ConnectNodesAtSameLevel<Integer> bt = new ConnectNodesAtSameLevel<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		bt.insert(0);
		bt.insert(2);
		bt.insert(9);
		bt.insert(12);
		
		bt.inOrderTraversal();
		System.out.println("\n connecting nodes in each level");
		bt.sameLevelNodes(bt.root);
		System.out.println("\n Nodes in each level are");
		bt.printUsingNextPointer(bt.root);
		
	}

}
