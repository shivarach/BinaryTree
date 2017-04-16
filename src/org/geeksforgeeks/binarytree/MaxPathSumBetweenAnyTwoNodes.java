package org.geeksforgeeks.binarytree;

/**
 * 
 * @author Shiva
 *T: O(n)
 * @param <Item>
 */
public class MaxPathSumBetweenAnyTwoNodes {
	
	private Node root;
	
	class Node {
		private int key;
		private Node left, right;
		
		public Node(int key) {
			this.key = key;
		}
	}
	class Result {
		int maxSum;
		public Result(int i) {
			this.maxSum = i;
		}
	}
	
	public void insert(int key) {
		root = insert(root, key);
	}

	private MaxPathSumBetweenAnyTwoNodes.Node insert(MaxPathSumBetweenAnyTwoNodes.Node root2,
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

	private void inOrderTraversal(MaxPathSumBetweenAnyTwoNodes.Node root) {
		if (root == null) return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}
	
	public int maxPath(Node x) {
		if(x.left == null || x.right == null)
			return Integer.MIN_VALUE;
		Result res = new Result(0);
		maxPath(x, res);
		return res.maxSum;
	}
/**
 * For each node there can be four ways that the max path goes through the node:
1. Node only
2. Max path through Left Child + Node
3. Max path through Right Child + Node
4. Max path through Left Child + Node + Max path through Right Child
 * @param x
 * @param res
 * @return
 */
	private int maxPath(MaxPathSumBetweenAnyTwoNodes.Node x, Result res) {
		if (x == null)
			return 0;
		if (x.left == null && x.right == null)
			return x.key;
		int ls = maxPath(x.left, res);
		int rs = maxPath(x.right, res);
		
		 // This path must include at-most one child of root
        int max_single = Math.max(Math.max(ls, rs) + x.key, x.key);
        // Max Top represents the sum when the Node under
        // consideration is the root of the maxsum path and no
        // ancestors of root are there in max sum path
        int max_top = Math.max(max_single, ls + rs + x.key);
 
        // Store the Maximum Result.
        res.maxSum = Math.max(res.maxSum, max_top);
        return max_single;

	}

	public static void main(String[] args) {
		MaxPathSumBetweenAnyTwoNodes bt = new MaxPathSumBetweenAnyTwoNodes();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		
		bt.inOrderTraversal();
		System.out.println("\nmax path distance between two leaf nodes is : " + bt.maxPath(bt.root));
	}

}
