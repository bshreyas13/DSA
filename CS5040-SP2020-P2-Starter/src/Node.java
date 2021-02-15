
/**
 * Represents a node in BST
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 * @param <K>
 *            The Node type
 * @param <V>
 *            The value type
 */
public class Node<K extends Comparable<K>, V> {

    /**
     * Private fields
     */
    private K key;
    private V value;
    private Node<K, V> left;
    private Node<K, V> right;
    private int size;

    /**
     * Instantiates a new node.
     *
     * @param key
     *            Key of the node
     * @param value
     *            Value of the node
     * @param size
     *            The node size with all its children
     */
    public Node(K key, V value, int size) {
        this.setKey(key);
        this.setValue(value);
        this.setSize(size);
    }


    /**
     * String representation of node
     */
    @Override
    public String toString() {
        return String.format("%s", key, value);
    }


    /**
     * Get key value
     * 
     * @return
     *         Returns the key
     */
    public K getKey() {
        return key;
    }


    /**
     * Set key
     * 
     * @param key
     *            The key value
     */
    public void setKey(K key) {
        this.key = key;
    }


    /**
     * Get value
     * 
     * @return
     *         Value
     */
    public V getValue() {
        return value;
    }


    /**
     * Set value
     * 
     * @param value
     *            Value
     */
    public void setValue(V value) {
        this.value = value;
    }


    /**
     * Returns the left node of current node
     * 
     * @return
     *         The left node
     */
    public Node<K, V> getLeft() {
        return left;
    }


    /**
     * Sets the left node to current node
     * 
     * @param left
     *            Left node
     */
    public void setLeft(Node<K, V> left) {
        this.left = left;
    }


    /**
     * Returns the right node
     * 
     * @return
     *         The Right node
     */
    public Node<K, V> getRight() {
        return right;
    }


    /**
     * Set the right node
     * 
     * @param right
     *            The right node
     */
    public void setRight(Node<K, V> right) {
        this.right = right;
    }


    /**
     * Returns the size of node
     * 
     * @return
     *         The node size
     */
    public int getSize() {
        return size;
    }


    /**
     * Set node size
     * 
     * @param size
     *            The node size
     */
    public void setSize(int size) {
        this.size = size;
    }

}
