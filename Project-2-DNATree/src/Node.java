/**
 * Represents the Tree node operations
 * 
 * @author {bshreyas and veerad}
 * @version 2021-03-07
 */
public interface Node {

    /**
     * Print node
     */
    public void print();


    /**
     * Insert node
     * 
     * @param sequence
     *            sequence to insert
     * @return
     *         Node inserted
     */
    public Node insert(Sequence sequence);


    /**
     * Remove sequence
     * 
     * @param sequence
     *            sequence to remove
     * @return
     *         node
     */
    public Node remove(Sequence sequence);


    /**
     * Search node
     * 
     * @param command
     *            command to search
     */
    public void search(SearchSequence command);


    /**
     * set node level
     * 
     * @param level
     *            node level
     */
    public void setLevel(int level);


    /**
     * return level
     * 
     * @return
     *         level
     */
    public int getLevel();
}
