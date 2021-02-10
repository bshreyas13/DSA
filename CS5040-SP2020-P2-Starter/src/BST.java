/**
 * Stub for binary search tree class
 * 
 * @author Shreyas
 * @param <T> the generic type; extends Comparable
 */

public class BST<T extends Comparable<T>> {

    /** The root. */
    protected Node<T> root;

    /** The size. */
    private int size;

    /**
     * Instantiates a new Binary Search Tree.
     */
    public BST() {
        root = null;
        size = 0;
    }

}
