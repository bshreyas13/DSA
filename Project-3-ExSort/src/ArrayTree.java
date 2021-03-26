
/**
 * The array - tree methods as based off the heap implementation presented in
 * OpenDSA are general methods for representing a binary tree as an array..
 * 
 * 
 * @author bshreyas and veerad
 * @version 02-26-2021
 */
public abstract class ArrayTree {
    /**
     * number of items visible in this tree
     */
    public abstract int size();


    /**
     * Check if an index in the array refers to a leaf
     * 
     * @param index.
     * @return True if the index is a leaf, false otherwise
     */
    public boolean isLeaf(int index) {
        return ((index >= size() / 2) && (index < size()));
    }


    /**
     * Get the index of the left child of the given node
     * 
     * @param index-parent's
     *            index
     * @return The index of the left child
     */
    public int leftChild(int index) {
        assert (index < size() / 2) : "Position has no left child";
        return 2 * index + 1;
    }


    /**
     * Get the index of the right child of the given node
     * 
     * @param index-parent's
     *            index
     * @return The index of the right child
     */
    public int rightChild(int index) {
        assert (index < (size() - 1) / 2) : "Position has no right child";
        return 2 * index + 2;
    }


    /**
     * Get the position for the parent of a given node
     * 
     * @param index
     *            the child's index
     * @return The index of the child's parent.
     */
    public int parent(int index) {
        assert (index > 0) : "Position has no parent";
        return (index - 1) / 2;
    }

}
