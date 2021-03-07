/**
 * Represents the Internal Node
 * 
 * @author {shreyasb and veerad}
 * @version 2021-03-07
 */
public class InternalNode implements Node {
    /**
     * occupied nodes limit
     */
    public static final int MIN_NODES = 1;
    /**
     * node level
     */
    private int level;

    /**
     * A child
     */
    private Node nodeA;
    /**
     * C Child
     */
    private Node nodeC;
    /**
     * G Child
     */
    private Node nodeG;
    /**
     * T Child
     */
    private Node nodeT;
    /**
     * $ Value
     */
    private Node nodeD;

    /**
     * @param currentSeq
     *            current sequence
     * @param newSeq
     *            new sequence to insert
     */
    public InternalNode(LeafNode currentSeq, Sequence newSeq) {
        setA(new EmptyNode());
        setC(new EmptyNode());
        setG(new EmptyNode());
        setT(new EmptyNode());
        setD(new EmptyNode());

        final Sequence existingSequence = currentSeq.getSequence();

        Sequence prefix;
        Sequence suffix;
        if (existingSequence.length() < newSeq.length()) {
            prefix = newSeq;
            suffix = existingSequence;
        }
        else {
            prefix = existingSequence;
            suffix = newSeq;
        }

        insert(prefix);

        if (suffix.isPrefixOf(prefix)) {
            insertDollor(suffix);
        }
        else {
            insert(suffix);
        }
    }


    /**
     * @param c
     *            character to switch
     * @return
     *         Child node based on the char
     */
    protected Node getChild(char c) {
        switch (c) {
            case 'A':
                return getA();
            case 'C':
                return getC();
            case 'G':
                return getG();
            case 'T':
                return getT();
            default:
                return null;
        }
    }


    /**
     * Print node
     */
    @Override
    public void print() {
        Print.node(this);
        getA().print();
        getC().print();
        getG().print();
        getT().print();
        getD().print();
    }


    /**
     * @param sequence
     *            sequence to insert
     */
    @Override
    public Node insert(Sequence sequence) {
        if (sequence.hasNext()) {
            final char sequenceChar = sequence.next();
            Node child = getChild(sequenceChar);
            if ((nodeD instanceof LeafNode) && ((LeafNode)nodeD).getSequence()
                .length() > sequence.length() && sequence.isPrefixOf(
                    ((LeafNode)nodeD).getSequence())) {
                insert(replaceSequence(sequence));
            }
            else if (!sequence.hasNext() && (child instanceof LeafNode)
                && (occupiedNodes() < MIN_NODES)) {
                insertDollor(sequence);
            }
            else {
                setChild(sequenceChar, child.insert(sequence));
            }
        }
        else {
            insertDollor(sequence);
        }
        return this;
    }


    /**
     * @param replaceWith
     *            change sequence
     * @return
     *         return sequence
     */
    private Sequence replaceSequence(Sequence replaceWith) {
        Sequence currentSequence = ((LeafNode)nodeD).getSequence();
        ((LeafNode)nodeD).setSequence(replaceWith);
        return currentSequence;
    }


    /**
     * @param sequence
     *            set sequence to $ if empty node other wise call insert
     *            recursively
     */
    public void insertDollor(Sequence sequence) {
        if (nodeD instanceof EmptyNode) {
            nodeD = nodeD.insert(sequence);
        }
        else if (sequence.equals(((LeafNode)nodeD).getSequence())) {
            Print.sequenceAlreadyExists(sequence);
        }
        else {
            insert(replaceSequence(sequence));
        }
    }


    /**
     * @param
     * remove
     *            the sequence at this node iteratively
     */
    @Override
    public Node remove(Sequence sequence) {
        if (sequence.hasNext()) {
            final char sequenceChar = sequence.next();
            Node child = getChild(sequenceChar);
            setChild(sequenceChar, child.remove(sequence));
        }
        else {
            nodeD = nodeD.remove(sequence);
        }

        Node collapsible = null;
        for (Node child : getChildren()) {
            if (child instanceof InternalNode) {
                return this;
            }
            else if (child instanceof LeafNode) {
                if (collapsible == null) {
                    collapsible = child;
                }
                else {
                    return this;
                }
            }
        }

        ((LeafNode)collapsible).getSequence().prev();
        return collapsible;
    }


    /**
     * @param sequence
     *            sequence to search
     */
    @Override
    public void search(SearchSequence sequence) {
        sequence.incrementNodesVisited();
        final Sequence searchSequence = sequence.getSearchSequence();
        if (searchSequence.hasNext()) {
            final char currentSearchChar = searchSequence.next();
            Node child = getChild(currentSearchChar);
            if (child != null) {
                child.search(sequence);
            }
        }
        else if (sequence.isExactMatch()) {
            nodeD.search(sequence);
        }
        else {
            nodeA.search(sequence);
            nodeC.search(sequence);
            nodeG.search(sequence);
            nodeT.search(sequence);
            nodeD.search(sequence);
        }
    }


    /**
     * @param c
     *            character to find child node
     * @param child
     *            child node
     * @return
     *         return true if child was set
     */
    public boolean setChild(char c, Node child) {
        switch (c) {
            case 'A':
                nodeA = child;
                return true;
            case 'C':
                nodeC = child;
                return true;
            case 'G':
                nodeG = child;
                return true;
            case 'T':
                nodeT = child;
                return true;
            default:
                return false;
        }
    }


    /**
     * @return
     *         return nodes occupied
     */
    private int occupiedNodes() {
        int occupiedNodes = 0;
        if (nodeA instanceof LeafNode) {
            occupiedNodes++;
        }
        if (nodeC instanceof LeafNode) {
            occupiedNodes++;
        }
        if (nodeG instanceof LeafNode) {
            occupiedNodes++;
        }
        if (nodeT instanceof LeafNode) {
            occupiedNodes++;
        }
        return occupiedNodes;
    }


    /**
     * @return
     *         return chidren
     */
    private Node[] getChildren() {
        return new Node[] { nodeA, nodeC, nodeG, nodeT, nodeD };
    }


    /**
     * @return
     *         Node
     */
    public Node getA() {
        return nodeA;
    }


    /**
     * @param a
     *            set A
     */
    public void setA(Node a) {
        nodeA = a;
    }


    /**
     * @return
     *         C
     */
    public Node getC() {
        return nodeC;
    }


    /**
     * @param c
     *            set c
     */
    public void setC(Node c) {
        nodeC = c;
    }


    /**
     * @return
     *         G
     */
    public Node getG() {
        return nodeG;
    }


    /**
     * @param g
     *            set g
     */
    public void setG(Node g) {
        nodeG = g;
    }


    /**
     * @return
     *         get T
     */
    public Node getT() {
        return nodeT;
    }


    /**
     * @param t
     *            set T
     */
    public void setT(Node t) {
        nodeT = t;
    }


    /**
     * @return
     *         get Dollor
     */
    public Node getD() {
        return nodeD;
    }


    /**
     * @param d
     *            set Dollor
     */
    public void setD(Node d) {
        this.nodeD = d;
    }


    /**
     * @param level
     *            set level
     */
    @Override
    public void setLevel(int level) {
        this.level = level;
        nodeA.setLevel(level + 1);
        nodeC.setLevel(level + 1);
        nodeG.setLevel(level + 1);
        nodeT.setLevel(level + 1);
        nodeD.setLevel(level + 1);
    }


    /**
     * @return
     *         level
     */
    @Override
    public int getLevel() {
        return level;
    }
}
