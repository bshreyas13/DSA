/**
 * Stub for Node class
 *
 * @author {shreyasb and veerad}
 */

public class Node<K extends Comparable<K>, V> {

    private K key;
    private V value;
    private Node<K, V> left;
    private Node<K, V> right;
    private int size;

    /**
     * Instantiates a new node.
     *
     * @param value the value
     */
    public Node(K key, V value, int size) {
        this.key = key;
        this.value = value;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("%s", key, value);
    }

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> left) {
		this.left = left;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> right) {
		this.right = right;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
    
    
}
