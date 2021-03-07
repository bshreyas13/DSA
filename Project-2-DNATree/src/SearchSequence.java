/**
 * Represents the Search sequence
 * 
 * @author {bshreyas and veerad}
 * @version 2021-03-07
 */
public class SearchSequence {

    /**
     * Search mode
     * 
     * @author {veerad and bshreyas}
     *
     */
    public static enum SearchMode {
        /**
         * Exact search, with $
         */
        EXACT,
        /**
         * Search for starts with text
         */
        PREFIX
    }

    /**
     * Used for prefix search
     */
    public static final String DOLLOR = "$";

    /**
     * Holds search mode
     */
    private final SearchMode mode;
    /**
     * search sequence
     */
    private Sequence seq;

    /**
     * holds the nodes visited
     */
    private int visited;
    /**
     * found matches
     */
    private MyList<Sequence> matches = new MyList<Sequence>();

    /**
     * Constructor
     * 
     * @param sequence
     *            sequence to hold
     */
    public SearchSequence(String sequence) {
        if (sequence.endsWith(DOLLOR)) {
            mode = SearchMode.EXACT;
            sequence = sequence.substring(0, sequence.length() - DOLLOR
                .length());
        }
        else {
            mode = SearchMode.PREFIX;
        }

        seq = new Sequence(sequence);
    }


    /**
     * Increase visited nodes
     */
    public void incrementNodesVisited() {
        this.visited++;
    }


    /**
     * Found match
     * 
     * @param sequence
     *            sequence found
     */
    public void addMatch(Sequence sequence) {
        this.matches.add(sequence);
    }


    /**
     * check for exact match mode
     * 
     * @return
     *         true if match mode is exact
     */
    public boolean isExactMatch() {
        return (mode == SearchMode.EXACT);
    }


    /**
     * get search sequence
     * 
     * @return
     *         search sequence
     */
    public Sequence getSearchSequence() {
        return seq;
    }


    /**
     * get nodes
     * 
     * @return
     *         nodes count
     */
    public int getNodes() {
        return visited;
    }


    /**
     * Get matches
     * 
     * @return
     *         list of matches
     */

    public MyList<Sequence> getMatches() {
        return matches;
    }


    /**
     * check if search found
     * 
     * @return
     *         true if search was found
     */
    public boolean searchFound() {
        return this.matches != null && this.matches.length() > 0;
    }
}
