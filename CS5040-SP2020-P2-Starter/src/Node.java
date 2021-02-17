
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
        return String.format("%s", key, value);
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
     * 
     * @param str 
     * @return true or false based on number of alphabet
     */
 
}
