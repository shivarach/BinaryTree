package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class LevelOfANodeInBinaryTree<Item extends Comparable<Item>> {
	
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

	private LevelOfANodeInBinaryTree<Item>.Node insert(LevelOfANodeInBinaryTree<Item>.Node root2,
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

	private void inOrderTraversal(LevelOfANodeInBinaryTree<Item>.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public int  getLevel(Node x, int target) {
		return getLevelUtil(x, target, 1);
	}
	/* Helper function for getLevel().  It returns level of the data
    if data is present in tree, otherwise returns 0.*/
	private int getLevelUtil(Node node, int data, int level) 
    {
	if (node == null)
        return 0;

    if (node.key.equals(data))
        return level;

    int downlevel = getLevelUtil(node.left, data, level + 1);
    if (downlevel != 0)
        return downlevel;

    downlevel = getLevelUtil(node.right, data, level + 1);
    return downlevel;
    }
	public static void main(String[] args) {
		LevelOfANodeInBinaryTree<Integer> bt = new LevelOfANodeInBinaryTree<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println("\nLevel is : " + bt.getLevel(bt.root, 10));
	}

}
