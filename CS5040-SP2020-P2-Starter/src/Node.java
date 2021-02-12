/**
 * Stub for Node class
 * 
 * @author Shreyas
 * @param <T> the generic type; extends Comparable
 */

public class Node<T extends Comparable<T>> {
	Rectangle value;
	Node<T> node;
	Node<T> left;
	Node<T> right;
    /**
     * Instantiates a new node.
     *
     * @param value
     *            the value
     */
    public Node(Rectangle value) {
    	
    	this.value =value;
    	left = null;
    	right = null;
    }
    public void CompareTo() {
    	
    }
    public Rectangle getRectangle() {
        return value;
    }

    public void setRectangle(Rectangle rectangle) {
        this.value = rectangle;
        setLeft(null);
        setRight(null);
    }

    public Node<T> getNode() {
        return node;
    }

    public void setNode(Node<T> node) {
        this.node = node;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

}

