import java.awt.Rectangle;

/**
 * Represents a node in BST
 * 
 * @author {shreyasb and veerad}
 * @version 2021-2-15
 * 
 */
public class Node implements Comparable<Node> {

    /**
     * Private fields
     */
    private String key;
    private Rectangle value;
    private Node left;
    private Node right;
    private int size;
    private int depth;

    /**
     * Constructor
     * 
     * @param key
     *            key
     * @param x
     *            x
     * @param y
     *            y
     * @param width
     *            width
     * @param height
     *            height
     * @return
     *         Node built
     */
    public static Node build(String key, int x, int y, int width, int height) {
        Rectangle value = new Rectangle(x, y, width, height);
        return new Node(key, value);

    }


    /**
     * Instantiates a new node.
     *
     * @param key
     *            Key of the node
     * @param value
     *            Value of the node
     */
    private Node(String key, Rectangle value) {
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
        return isKeyValid() && isValueValid();
    }


    /**
     * Verify the key is valid
     * 
     * @return
     *         true if valid, false otherwise
     */
    public boolean isKeyValid() {
        return (key != null && key.length() > 0 && Character.isLetter(key
            .charAt(0)) && key.matches("[a-zA-Z0-9_]+"));
    }


    /**
     * Verify the value is valid
     * 
     * @return
     *         true if valid, false otherwise
     */
    public boolean isValueValid() {
        return (value.x >= 0 & value.y >= 0 & value.width > 0
            & value.height > 0) && (value.x < 1024 && value.y < 1024)
            && ((value.x + value.width) <= 1024) && ((value.y
                + value.height) <= 1024);
    }


    /**
     * String representation
     * 
     * @return
     *         String value of node
     */
    @Override
    public String toString() {
        return String.format("(%s, %d, %d, %d, %d)", key, value.x, value.y,
            value.width, value.height);
    }


    /**
     * Used for key comparison
     * 
     * @param name
     *            key name
     * @return
     *         string comparison result
     */
    public int compareKey(String name) {
        return name.compareTo(key);
    }


    /**
     * Used for key comparison
     * 
     * @param name
     *            key name
     * @return
     *         string comparison result
     */
    public int compareValue(Rectangle dims, Rectangle root) {
        return compareVal(root, dims);
    }


    /**
     * Used for node comparison
     */
    @Override
    public int compareTo(Node other) {
        int c = key.compareTo(other.key);
        if (c != 0) {
            return c;
        }
        c = Integer.compare(value.x, other.value.x);
        if (c != 0) {
            return c;
        }
        c = Integer.compare(value.y, other.value.y);
        if (c != 0) {
            return c;
        }
        c = Integer.compare(value.width, other.value.width);
        if (c != 0) {
            return c;
        }
        return Integer.compare(value.height, other.value.height);
    }


    /**
     * 
     * @param value
     *            rectangle 1
     * @param other
     *            rectangle 2
     * @return 0 if equal
     */

    private int compareVal(Rectangle value, Rectangle other) {

        int c = Integer.compare(value.x, other.x);
        if (c != 0) {
            return c;
        }
        c = Integer.compare(value.y, other.y);
        if (c != 0) {
            return c;
        }
        c = Integer.compare(value.width, other.width);
        if (c != 0) {
            return c;
        }
        return Integer.compare(value.height, other.height);
    }


    /**
     * @return the left
     */
    public Node getLeft() {
        return left;
    }


    /**
     * 
     * @return the key
     */
    public String getKey() {
        return this.key;
    }


    /**
     * 
     * @return the value(Rectangle)
     */
    public Rectangle getValue() {
        return this.value;
    }


    /**
     * @param left
     *            the left to set
     */
    public void setLeft(Node left) {
        this.left = left;
    }


    /**
     * @return the right
     */
    public Node getRight() {
        return right;
    }


    /**
     * @param right
     *            the right to set
     */
    public void setRight(Node right) {
        this.right = right;
    }


    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }


    /**
     * @param size
     *            the size to set
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

}
