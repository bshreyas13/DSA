
/**
 * Represents the leaf node
 *
 * @author {bshreyas and veerad}
 * @version 2021-03-07
 */
public class LeafNode implements Node {
    /**
     * this node sequence
     */
    private Sequence sequence;

    /*
     * node level
     */
    private int level;

    /**
     * leaf node constructor
     *
     * @param sequence
     *            sequence of this leaf node
     */
    public LeafNode(Sequence sequence) {
        Print.log("Reached leaf node:" + sequence.toString());
        this.setSequence(sequence);
        Print.setInserted(this);
    }


    /**
     * print node
     */
    @Override
    public void print() {
        Print.node(this);
    }


    /**
     * @param seq
     *            sequence to insert
     * @return Node
     */
    @Override
    public Node insert(Sequence seq) {
        if (this.sequence.equals(seq)) {
            Print.sequenceAlreadyExists(seq);
            return this;
        }
        else {
            Print.log("Leaf node creating internal node to insert " + seq);
            // System.out.println(this.getLevel());
            return new InternalNode(this, seq);
        }
    }


    /**
     * Remove sequence
     *
     * @param seq
     *            sequence to remove
     * @return node
     */
    @Override
    public Node remove(Sequence seq) {
        if (this.sequence.equals(seq)) {
            Print.info(String.format("sequence %s removed", seq));
            return new EmptyNode();
        }
        else {
            Print.noSeqFound(seq);
            return this;
        }
    }


    /**
     * search node
     *
     * @param seq
     */
    @Override
    public void search(SearchSequence seq) {
        seq.incrementNodesVisited();
        if (seq.isExactMatch() && this.sequence.equals(seq
            .getSearchSequence())) {
            seq.addMatch(this.sequence);
        }
        else if (!seq.isExactMatch() && this.sequence.toString().trim()
            .startsWith(seq.getSearchSequence().toString().trim())) {
            seq.addMatch(this.sequence);
        }
    }


    /**
     * get sequence
     *
     * @return sequence
     */
    public Sequence getSequence() {
        return sequence;
    }


    /**
     * set sequence
     *
     * @param sequence
     *            sequence
     */
    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }


    /**
     * set level
     *
     * @param level
     *            level
     */
    @Override
    public void setLevel(int level) {
        this.level = level;
        this.sequence.setPrefixLength(-1);
    }


    /**
     * get level
     *
     * @return level
     */
    @Override
    public int getLevel() {
        return level;
    }
}
