/**
 * Stub for Node class
 * 
 * @author Shreyas
 * @param <T> the generic type; extends Comparable
 */

public class Node<T extends Comparable<T>> {
	T value;
	Node<T> left;
	Node<T> right;
    /**
     * Instantiates a new node.
     *
     * @param value
     *            the value
     */
    public Node(T value) {
    	
    	this.value =value;
    	left =null;
    	right = null;
    }
}
