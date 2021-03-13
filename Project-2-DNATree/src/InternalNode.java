/**
 * Represents the Internal Node
 * 
 * @author {shreyasb and veerad}
 * @version 2021-03-07
 */
public class InternalNode implements Node {

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
     * @param parentNode
     *            current sequence
     * @param newSeq
     *            new sequence to insert
     */
    public InternalNode(LeafNode parentNode, Sequence newSeq) {
        setA(new EmptyNode());
        setC(new EmptyNode());
        setG(new EmptyNode());
        setT(new EmptyNode());
        setD(new EmptyNode());

        final Sequence leafSeq = parentNode.getSequence();

        if (leafSeq.isPrefixOf(newSeq)) {
            newSeq.setPrefixLength(leafSeq.length() + 2);
        }
        else if (newSeq.isPrefixOf(leafSeq)) {
            leafSeq.setPrefixLength(newSeq.length() + 2);
        }

        Sequence first;
        Sequence second;
        if (leafSeq.length() < newSeq.length()) {
            first = newSeq;
            second = leafSeq;
        }
        else {
            first = leafSeq;
            second = newSeq;
        }

        insert(first);
        insert(second);

    }


    /**
     * Internal code constructor
     * 
     * @param parentNode
     *            parent node
     * @param newSeq
     *            sequence code
     */
    public InternalNode(EmptyNode parentNode, Sequence newSeq) {
        setA(new EmptyNode());
        setC(new EmptyNode());
        setG(new EmptyNode());
        setT(new EmptyNode());
        setD(new EmptyNode());

        insert(newSeq);
    }


    /**
     * @param c
     *            character to switch
     * @return Child node based on the char
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
        Print.log(String.format("Inserting %s", sequence));

        if (sequence.hasNextPrefix()) {
            final char c = sequence.nextPrefix();
            Print.log(String.format("current prefix char %c", c));
            Node child = getChild(c);
            setChild(c, child.insert(sequence));
        }
        else if (sequence.hasNext()) {
            Print.log(String.format("current position %d", sequence
                .getPosition()));
            final char c = sequence.next();
            Node child = getChild(c);
            Print.log(String.format("Got child %s for %s", child.toString(),
                sequence));
            setChild(c, child.insert(sequence));
        }
        else {
            // Print.log(String.format(
            // "No characters remaining for %s, insert to dollor-2",
            // sequence));
            insertToDollor(sequence);
        }
        return this;
    }


    /**
     * insert to dollor node
     * 
     * @param sequence
     *            set sequence to $ if empty node other wise call insert
     *            recursively
     */
    public void insertToDollor(Sequence sequence) {
        Print.log(String.format("inserting prefix: %s", sequence));
        if (nodeD instanceof EmptyNode) {
            nodeD = nodeD.insert(sequence);
        }
        else if ((nodeD instanceof LeafNode) && sequence.equals(
            ((LeafNode)nodeD).getSequence())) {
            Print.sequenceAlreadyExists(sequence);
        }
    }


    /**
     * remove node
     * 
     * @param sequence
     *            the sequence at this node iteratively
     */
    @Override
    public Node remove(Sequence sequence) {
        if (sequence.hasNext()) {
            final char nextChar = sequence.next();
            Node child = getChild(nextChar);
            setChild(nextChar, child.remove(sequence));
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
     * @return return true if child was set
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
     * @return return chidren
     */
    private Node[] getChildren() {
        return new Node[] { nodeA, nodeC, nodeG, nodeT, nodeD };
    }


    /**
     * @return Node
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
     * @return C
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
     * @return G
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
     * @return get T
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
     * @return get Dollor
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
     * @return level
     */
    @Override
    public int getLevel() {
        return level;
    }
}
