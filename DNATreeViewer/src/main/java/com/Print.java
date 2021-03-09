package com;
/**
 * Represents the Print functionality
 * 
 * @author {bshreyas and veerad}
 * @version 2021-03-07
 */
public class Print {

    /**
     * Represents node that was inserted
     */
    private static Node insertedNode;
    /**
     * hold print mode
     */
    private static PrintMode printMode;

    /**
     * set newly inserted node
     * 
     * @param inserted
     *            inserted node
     */
    public static void setInserted(Node inserted) {
        insertedNode = inserted;
    }


    /**
     * set print mode
     * 
     * @param mode
     *            print mode
     */
    public static void setPrint(PrintMode mode) {
        printMode = mode;
    }


    /**
     * Print inserted
     * 
     * @param s
     *            sequence
     */
    public static void printInserted(Sequence s) {
        if (insertedNode != null) {
            System.out.println(String.format("sequence %s inserted at level %d",
                s, insertedNode.getLevel()));
        }
    }


    /**
     * Show node
     * 
     * @param node
     *            node to display
     */
    public static void node(Node node) {
        String spaces = "";
        for (int i = 1; i <= node.getLevel(); i++) {
            spaces += "  ";
        }
        String text = "";
        String metadata = "";

        if (node instanceof InternalNode) {
            text = "I";
        }

        else if (node instanceof EmptyNode) {
            text = "E";
        }

        else if (node instanceof LeafNode) {
            text = ((LeafNode)node).getSequence().toString();
            if (printMode == PrintMode.LENGTH) {
                metadata = String.format("%d", text.length());
            }
            else if (printMode == PrintMode.STATS) {

                char[] chars = new char[] { 'A', 'C', 'G', 'T' };

                for (int i = 0; i < chars.length; i++) {
                    char c = chars[i];
                    metadata += String.format("%c:%.2f ", c, charStat(text, c));
                }
            }
        }
        metadata = metadata.trim();
        if (metadata.length() > 0) {
            metadata = " " + metadata;
        }
        System.out.println(String.format("%s%s%s", spaces, text, metadata));
    }


    /**
     * show info
     * 
     * @param info
     *            info
     */
    public static void info(String info) {
        System.out.println(info);
    }


    /**
     * show no sequence found info
     * 
     * @param sequence
     *            sequence to display as not found
     */
    public static void noSeqFound(Sequence sequence) {
        info(String.format("sequence %s does not exist", sequence));
    }


    /**
     * Show sequence already exists
     * 
     * @param s
     *            display message that sequence is already exists
     */
    public static void sequenceAlreadyExists(Sequence s) {
        System.out.println(String.format("sequence %s already exists", s));
    }


    /**
     * show search summary
     * 
     * @param searchSequence
     *            search sequence
     */
    public static void searchSummary(SearchSequence searchSequence) {
        info(String.format("# of nodes visited: %d", searchSequence
            .getNodes()));
        if (searchSequence.searchFound()) {
            for (int i = 0; i < searchSequence.getMatches().length(); i++) {
                Sequence sequence = searchSequence.getMatches().get(i);
                info(String.format("sequence: %s", sequence.getSequence()));
            }
        }
        else {
            info("no sequence found");
        }
    }


    /**
     * Calculate character stats
     * 
     * @param text
     *            node text
     * @param c
     *            character to calculate stats
     * @return
     */
    private static float charStat(String text, char c) {
        if (text != null && text.length() > 0) {
            int count = 0;
            for (char x : text.toCharArray()) {
                if (c == x) {
                    count++;
                }
            }
            return ((float)count / (float)text.length()) * 100;
        }
        return 0;
    }


    /**
     * internal log function
     * 
     * @param string
     *            log msg
     */
    public static void log(String string) {
        // info(string);
        string = null;

    }
}
