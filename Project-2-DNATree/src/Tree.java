
/**
 * Represents the Tree
 * 
 * @author {bshreyas and veerad}
 * @version 2021-03-07
 */
public class Tree {
    /**
     * Root node
     */
    private Node root = null;

    /**
     * Default constructor
     */
    public Tree() {
        root = new EmptyNode();
    }


    /**
     * Clear Tree
     */
    public void clear() {
        root = null;
    }


    /**
     * insert sequence
     * 
     * @param sequence
     *            sequence of node
     */
    public void insert(Sequence sequence) {
        Print.setInserted(null);
        // System.out.println(root);
        root = root.insert(sequence);
        // System.out.println(root);
        if (root != null) {
            root.setLevel(0);
            Print.printInserted(sequence);
        }
    }


    /**
     * Remove sequence
     * 
     * @param sequence
     *            sequence to remove
     */
    public void remove(Sequence sequence) {
        root = root.remove(sequence);
        if (root != null) {
            root.setLevel(0);
        }
    }


    /**
     * show tree dump
     */
    public void dump() {
        Print.info("tree dump:");
        root.print();
    }


    /**
     * search for sequence
     * 
     * @param sequence
     *            sequence to search
     */
    public void search(SearchSequence sequence) {
        root.search(sequence);
        Print.searchSummary(sequence);
    }

}
