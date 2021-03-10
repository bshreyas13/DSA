/**
 * Represents the Empty node
 * 
 * @author {bshreyas and veerad}
 * @version 2021-03-07
 */
public class EmptyNode implements Node {
    /**
     * node level
     */
    private int level;

    /**
     * Print node information
     */
    @Override
    public void print() {
        Print.node(this);
    }


    /**
     * @param sequence
     *            sequence string to insert, return new leaf node
     * @return inserted node
     */
    @Override
    public Node insert(Sequence sequence) {
        Print.log(String.format("Empty node reached for %s, creating leaf node",
            sequence));
        return new LeafNode(sequence);
    }


    /**
     * @param sequence
<<<<<<< HEAD
     *            sequence to remove, display as no sequence found becasue
     *            empty node
     * @return return node
=======
     *            sequence to remove, display as no sequence found because empty
     *            node
     * @return
     *         return node
>>>>>>> c36520732ef167bd88d4f52ad2474f06108e8f1f
     */
    @Override
    public Node remove(Sequence sequence) {
        Print.noSeqFound(sequence);
        return this;
    }


    /**
     * @param sequence
     *            increase nodes visited for empty node as no content to search
     *            here
     */
    @Override
    public void search(SearchSequence sequence) {
        sequence.incrementNodesVisited();
    }


    /**
     * @param level
     *            set level
     */
    @Override
    public void setLevel(int level) {
        this.level = level;
    }


    /**
     * @return return level
     */
    @Override
    public int getLevel() {
        return level;
    }
}
