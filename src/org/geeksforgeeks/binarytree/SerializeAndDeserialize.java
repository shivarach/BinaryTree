package org.geeksforgeeks.binarytree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Serialize and Deserialize a Binary Tree. The following solution can be
 * optimized wrt space by storing only one key for all child nodes. Remember
 * number of child nodes in a completer tree is numberr of internal nodes + 1.
 * T: n disk writes + n disk reads S: O(n)
 * 
 * @author Shiva
 *
 * @param <int>
 */
public class SerializeAndDeserialize {

	private Node root;

	class Node {
		private String key;
		private Node left, right;

		public Node(String key) {
			this.key = key;
		}
	}

	public void insert(String key) {
		if (key == null)
			return;
		root = insert(root, key);
	}

	private Node insert(Node root2, String key) {
		if (root2 == null)
			return new Node(key);
		if (Integer.parseInt(key) <= Integer.parseInt(root2.key))
			root2.left = insert(root2.left, key);
		else
			root2.right = insert(root2.right, key);
		return root2;
	}

	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	private void inOrderTraversal(Node root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.key + " ");
		inOrderTraversal(root.right);
	}

	// serializes binary tree keys to a file 'binarytree.ser'
	public void serialize(Node x) throws IOException {
		FileOutputStream fos = new FileOutputStream("binarytree.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		serialize(x, oos);
		oos.close();
		fos.close();
	}

	private void serialize(Node x, ObjectOutputStream oos) throws IOException {
		if (x == null) {
			oos.writeObject("#");
			return;
		}
		oos.writeObject(x.key);
		serialize(x.left, oos);
		serialize(x.right, oos);
	}

	public Node deserialize() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("binarytree.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		String firstKey = (String) ois.readObject();
		Node root = deserialize(ois, firstKey);
		ois.close();
		fis.close();
		return root;
	}

	private Node deserialize(ObjectInputStream ois, String key) throws ClassNotFoundException, IOException {
		if (key == null || key.equals("#"))
			return null;
		Node root = new Node(key);
		root.left = deserialize(ois, (String) ois.readObject());
		root.right = deserialize(ois, (String) ois.readObject());
		return root;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerializeAndDeserialize bt = new SerializeAndDeserialize();
		bt.insert("5");
		bt.insert("3");
		bt.insert("8");
		bt.insert("1");
		bt.insert("4");
		bt.insert("6");
		bt.insert("10");

		System.out.print("original tree : ");
		bt.inOrderTraversal();

		bt.serialize(bt.root);
		System.out.println("\nserialized...");
		System.out.print("deserializeing : ");
		Node tmp = bt.deserialize();
		bt.inOrderTraversal(tmp);

	}

}
