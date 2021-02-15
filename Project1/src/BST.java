/**
 * Stub for binary search tree class
 *
 * @author {shreyasb and veerad}
 */

public class BST<K extends Comparable<K>, V extends Shape> {

	protected Node<K, V> root;

	public BST() {
		root = null;
	}

	public void insert(K key, V val) {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}
		root = insert(root, key, val);
	}

	private Node<K, V> insert(Node<K, V> node, K key, V value) {
		if (node == null)
			return new Node<K, V>(key, value, 1);

		int difference = key.compareTo(node.getKey());
		if (difference < 0) {
			node.setLeft(insert(node.getLeft(), key, value));
		} else if (difference > 0) {
			node.setRight(insert(node.getRight(), key, value));
		} else {
			node.setValue(value);
		}
		node.setSize(1 + size(node.getLeft()) + size(node.getRight()));

		return node;
	}

	public void remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Can't delete with null key");
		}
		root = remove(root, key);
	}

	private Node<K, V> remove(Node<K, V> node, K key) {
		if (node == null) {
			return null;
		}
		int difference = key.compareTo(node.getKey());
		if (difference < 0) {
			node.setLeft(remove(node.getLeft(), key));
		} else if (difference > 0) {
			node.setRight(remove(node.getRight(), key));
		} else {
			if (node.getRight() == null) {
				return node.getLeft();
			}
			if (node.getLeft() == null) {
				return node.getRight();
			}
			Node<K, V> t = node;
			node = findMinimumInRightSubTree(t.getRight());
			node.setRight(deleteMinimum(t.getRight()));
			node.setLeft(t.getLeft());
		}
		node.setSize(size(node.getLeft()) + size(node.getRight()) + 1);
		return node;
	}

	public String searchForRegion(V value) {
		StringBuilder sb = new StringBuilder();
		inOrderForIntersection(root, value, sb);
		return sb.toString();
	}

	private void inOrderForIntersection(Node<K, V> node, V value, StringBuilder sb) {
		if (node == null) {
			return;
		}
		inOrderForIntersection(node.getLeft(), value, sb);
		if (node.getValue().shapeIntersects(value)) {
			sb.append(String.format("Intersecting value found: %s", node));
		}
		inOrderForIntersection(node.getRight(), value, sb);
	}

	public String searchForIntersections() {
		StringBuilder sb = new StringBuilder();
		traverseEachNode(root, sb);
		return sb.toString();
	}

	private void traverseEachNode(Node<K, V> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		traverseEachNode(node.getLeft(), sb);
		System.out.printf("---Searching intersections for %s", node);
		inOrderForIntersection(node, node.getValue(), sb);
		traverseEachNode(node.getRight(), sb);
	}

	public V searchByKey(K key) {
		return inOrderForSearchByKey(root, key);
	}

	private V inOrderForSearchByKey(Node<K, V> node, K key) {
		if (node == null) {
			return null;
		}
		inOrderForSearchByKey(node.getLeft(), key);
		if (node.getKey().equals(key)) {
			return node.getValue();
		}
		inOrderForSearchByKey(node.getRight(), key);
		return null;
	}

	public void removeAllByShape(Shape shape) {
		inOrderForShape(root, shape);
	}

	private void inOrderForShape(Node<K, V> node, Shape shape) {
		if (node == null)
			return;
		inOrderForShape(node.getLeft(), shape);
		if (node.getValue().isShapeEquals(shape)) {
			System.out.printf("Deleting matched node: %s\r\n", node);
			remove(node.getKey());
		}
		inOrderForShape(node.getRight(), shape);
	}

	private Node<K, V> findMinimumInRightSubTree(Node<K, V> x) {
		if (x.getLeft() == null) {
			return x;
		} else {
			return findMinimumInRightSubTree(x.getLeft());
		}
	}

	private Node<K, V> deleteMinimum(Node<K, V> x) {
		if (x.getLeft() == null) {
			return x.getRight();
		}
		x.setLeft(deleteMinimum(x.getLeft()));
		x.setSize(size(x.getLeft()) + size(x.getRight()) + 1);
		return x;
	}

	private int size(Node<K, V> node) {
		if (node == null) {
			return 0;
		} else {
			return node.getSize();
		}
	}

	private void inOrder(Node<K, V> node) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft());
		System.out.printf("%s->", node);
		inOrder(node.getRight());
	}

	public void dump() {
		System.out.println("Dump BST In-Order traversal:");
		inOrder(root);
	}
}
