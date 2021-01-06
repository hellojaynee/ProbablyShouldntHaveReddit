

import java.util.NoSuchElementException;

public class BST2 {
	private class Node {
		private Question data;
		private Node left;
		private Node right;

		public Node(Question data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private Node root;

	/*** CONSTRUCTORS ***/

	/**
	 * Default constructor for BST sets root to null
	 */
	public BST2() {
		root = null;
	}

	/**
	 * Copy constructor for BST
	 * 
	 * @param bst the BST to make a copy of
	 */
	public BST2(BST2 bst) {
		if (bst.isEmpty()) {
			return;
		}
		this.root = null;
		copyHelper(bst.root);
	}

	/**
	 * Helper method for copy constructor
	 * 
	 * @param node the node containing data to copy
	 */
	private void copyHelper(Node node) {
		// iterate through the bst and extract each value and insert on the node 
		if (node != null) {
			this.insert(node.data);
			copyHelper(node.left);
			copyHelper(node.right);
			
		}else {
			return;
		}
		
		
	}

	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when precondition is violated
	 */
	public Question getRoot() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("getRoot(): The tree is currently empty, no value to retrieve!");
		} else {
			return root.data;
		}
	}

	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null) {
			return 0;
		} else {
			return getSize(node.left) + getSize(node.right) + 1;
		}
	}

	/**
	 * Returns the height of tree by counting edges.
	 * 
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root);
	}

	/**
	 * Helper method for getHeight method
	 * 
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if (node == null) {
			return -1; 
		}else {
			int leftHeight = 1 + getHeight(node.left);
			int rightHeight = 1 + getHeight(node.right);
			if (leftHeight > rightHeight) {
				return leftHeight;
			}else {
				return rightHeight;
			}
		}
	}

	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public Question findMin() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMin(): The tree is currently empty, no Min value!");
		} else {
			return findMin(root);
		}
	}

	/**
	 * Helper method to findMin method
	 * 
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private Question findMin(Node node) { // helper
		if (node.left == null) {
			return node.data;
		} else {
			return findMin(node.left);
		}
	}

	/**
	 * Returns the largest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public Question findMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMax(): The tree is currently empty, no Max value!");
		} else {
			return findMax(root);
		}
	}

	/**
	 * Helper method to findMax method
	 * 
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */
	private Question findMax(Node node) {
		if (node.right == null) {
			return node.data;
		} else {
			return findMax(node.right);
		}
	}

	/**
	 * Searches for a specified value in the tree
	 * 
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */
	public boolean search(Question data) { // Wrapper
		if (root == null) {
			return false;
		} else {
			return search(data, root);
		}
	}

	/**
	 * Helper method for the search method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */
	private boolean search(Question data, Node node) {
		if (node.data.equals(data)) {
			return true;
		} else if (node.data.compareToSecondaryKey(data) > 0) {
			if (node.left == null) {
				return false;
			}
			return search(data, node.left);
		} else {
			if (node.right == null) {
				return false;
			}
			return search(data, node.right);
		}
	}

	/**
	 * Determines whether two trees store identical data in the same structural
	 * position in the tree
	 * 
	 * @param o another Object
	 * @return whether the two trees are equal
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BST2)) {
			return false;
		} else {
			BST2 b = (BST2) o;
			return equals(root, b.root);
		}
	}

	/**
	 * Helper method for the equals method
	 * 
	 * @param node1 the node of the first bst
	 * @param node2 the node of the second bst
	 * @return whether the two trees contain identical data stored in the same
	 *         structural position inside the trees
	 */
	private boolean equals(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		} else if (node1 != null && node2 != null) {
			return node1.data.equals(node2.data) && equals(node1.left, node2.left) && equals(node1.right, node2.right);
		} else {
			return false;
		}
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 */
	public void insert(Question data) {
		if (root == null) {
			root = new Node(data);
		} else {
			insert(data, root);
		}
	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location in which
	 *             to insert
	 */
	private void insert(Question data, Node node) {
		if (data.compareToSecondaryKey(node.data) <= 0) {
			if (node.left == null) {
				node.left = new Node(data);
			} else if (node.left != null) {
				insert(data, node.left);
			}
		} else if (node.right == null) {
			node.right = new Node(data);
		} else if (node.right != null) {
			insert(data, node.right);
		}
	}

	/**
	 * Removes a value from the BST
	 * 
	 * @param data the value to remove
	 * @precondition !isEmpty()
	 * @precondition the data is located in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public void remove(Question data) throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("remove(): The tree is currently empty, can't remove!");
		} else if (!search(data)){
			throw new NoSuchElementException("remove(): Element not found, can't remove!");
		}else {
			root = remove(data, root);
		}
	}

	/**
	 * Helper method to the remove method
	 * 
	 * @param data the data to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	/*
	 * Pseudocode for remove helper(node, value) If node is null return node
	 * Otherwise, if value < the node's data set node's leftchild equal to the
	 * recursive call of remove helper on node's leftchild Otherwise, if value > the
	 * node's data set node's rightchild equal to the recursive call of remove
	 * helper on node's rightchild Otherwise, If node is a leaf node Set node to
	 * null Otherwise if node has a leftchild but no rightchild set the leftchild to
	 * be the node Otherwise if node has a rightchild but no leftchild set the
	 * rightchild to be the node Otherwise Search for the minimum value in the right
	 * subtree of the node Set the node's data to be the minimum value in the node's
	 * right subtree Set node's rightchild equal to the recursive call of remove
	 * helper, passing it node's rightchild and the minimum data of node's right
	 * subtree (i.e. delete the duplicate value in the right subtree) return the
	 * node
	 */
	private Node remove(Question data, Node node) {
		if (node == null) {
			return node;
		} else if (data.compareToSecondaryKey(node.data) < 0) {
			node.left = remove(data, node.left);
		} else if (data.compareToSecondaryKey(node.data) > 0) {
			node.right = remove(data, node.right);
		} else {
			if (node.left == null && node.right == null) {
				node = null;
			} else if (node.left != null && node.right == null) {
				node = node.left;
			} else if (node.right != null && node.left == null) {
				node = node.right;
			} else {
				node.data = findMin(node.right);
				node.right = remove(node.data, node.right);
			}
		}
		return node;
	}

	/*** ADDITIONAL OPERATIONS ***/

	/**
	 * Prints the data in pre order to the console
	 */
	public void preOrderPrint() {
		preOrderPrint(root);
	}

	/**
	 * Helper method to preOrderPrint method Prints the data in pre order to the
	 * console
	 */
	private void preOrderPrint(Node node) {
		if (node == null) {
			return;
		} else {
			System.out.print(node.data + " \n");
			preOrderPrint(node.left);
			preOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in sorted order to the console
	 */
	public void inOrderPrint() {
		inOrderPrint(root);
	}

	/**
	 * Helper method to inOrderPrint method Prints the data in sorted order to the
	 * console
	 */
	private void inOrderPrint(Node node) {
		if (node == null) {
			return;
		} else {
			inOrderPrint(node.left);
			System.out.print(node.data + " \n");
			inOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in post order to the console
	 */
	public void postOrderPrint() {
		postOrderPrint(root);
	}

	/**
	 * Helper method to postOrderPrint method Prints the data in post order to the
	 * console
	 */
	private void postOrderPrint(Node node) {
		if (node == null) {
			return;
		} else {
			postOrderPrint(node.left);
			postOrderPrint(node.right);
			System.out.print(node.data + " \n");
		}
	}
}

