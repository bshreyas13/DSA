
import java.util.UUID;

/**
 * Represents a node in BST
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 * 
 * @param <V>
 * 
 *            Any value which extends Shape
 */
public class Node<V extends Shape> {

    /**
     * Private fields
     */
    private String key;
    private V value;
    private Node<V> left;
    private Node<V> right;
    private int size;
    private int depth;
    private UUID uuid;

    /**
     * Instantiates a new node.
     *
     * @param key
     *            Key of the node
     * @param value
     *            Value of the node
     */
    public Node(String key, V value) {
        this.key = key;
        this.value = value;
        this.uuid = UUID.randomUUID();
    }


    /**
     * Verify this node is valid
     * 
     * @return
     *         true if valid false otherwise
     */
    public boolean isValid() {
        boolean isKeyValid = (key != null && key.length() > 0 && Character
            .isLetter(key.charAt(0)) && key.matches("[a-zA-Z0-9_]+"));
        return isKeyValid && value.isShapeValid();
    }


    /**
     * String representation of node
     */
    @Override
    public String toString() {
        return String.format("(%s, %s)", key, value);
    }


    /**
     * Get key value
     * 
     * @return
     *         Returns the key
     */
    public String getKey() {
        return key;
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
     * Returns the left node of current node
     * 
     * @return
     *         The left node
     */
    public Node<V> getLeft() {
        return left;
    }


    /**
     * Sets the left node to current node
     * 
     * @param left
     *            Left node
     */
    public void setLeft(Node<V> left) {
        this.left = left;
    }


    /**
     * Returns the right node
     * 
     * @return
     *         The Right node
     */
    public Node<V> getRight() {
        return right;
    }


    /**
     * Set the right node
     * 
     * @param right
     *            The right node
     */
    public void setRight(Node<V> right) {
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


    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }


    /**
     * @param depth
     *            the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }


    /**
     * set the value
     * 
     * @param v
     *            value
     */
    public void setValue(V v) {
        this.value = v;

    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Node<V> otherNode = (Node<V>)obj;
        return this.uuid.compareTo(otherNode.uuid) == 0;
    }


    /**
     * Set key
     * 
     * @param key
     *            key value
     */
    public void setKey(String key) {
        this.key = key;

    }

}
