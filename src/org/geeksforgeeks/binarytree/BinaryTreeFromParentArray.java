package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *Construct Binary Tree from given Parent Array representation
 *T: O(n)
 * @param <int>
 */
public class BinaryTreeFromParentArray {
	
	private Node root;
	
	class Node {
		private int key;
		private Node left, right;
		
		public Node(int key) {
			this.key = key;
		}
	}
	
	public void insert(int key) {
		root = insert(root, key);
	}

	private Node insert(Node root2,
			int key) {
		if (root2 == null)
			return new Node(key);
		if(key <= root2.key)
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}
	
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public void createTree(int parent[]) {
		Node[] created = new Node[parent.length];
		
		for(int i = 0 ; i < parent.length ; i++)
			createNode(parent, i, created);
	}

	private void createNode(int[] parent, int i, Node[] created) {
		//if node is already created
		if(created[i] != null)
			return;
		created[i] = new Node(i);
		//if 'i' is change point root and return
		if(parent[i] == -1) {
			root = created[i];
			return;
		}
		//if parent not create then create it
		if(created[parent[i]] == null)
			createNode(parent, parent[i], created);
		// find parent node
		Node p = created[parent[i]];
		
		//if this is first child of parent
		if(p.left == null)
			p.left = created[i];
		else
			p.right = created[i];
		
	}

	public static void main(String[] args) {
		BinaryTreeFromParentArray bt = new BinaryTreeFromParentArray();
		int[] parent = {1, 5, 5, 2, 2, -1, 3};
		bt.createTree(parent);
		
		bt.inOrderTraversal();
	}

}
