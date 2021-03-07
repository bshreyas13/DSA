public class SearchSequence {
	public static enum SearchMode {
		EXACT, PREFIX
	}

	public static final String DOLLOR = "$";

	private final SearchMode mode;
	private Sequence searchSequence;
	private int visited;
	private MyList<Sequence> matches = new MyList<Sequence>();

	public SearchSequence(String sequenceDescriptor) {
		if (sequenceDescriptor.endsWith(DOLLOR)) {
			mode = SearchMode.EXACT;
			sequenceDescriptor = sequenceDescriptor.substring(0, sequenceDescriptor.length() - DOLLOR.length());
		} else {
			mode = SearchMode.PREFIX;
		}

		searchSequence = new Sequence(sequenceDescriptor);
	}

	public void incrementNodesVisited() {
		this.visited++;
	}

	public void matchFound(Sequence matchedSequence) {
		this.matches.add(matchedSequence);
	}

	public boolean matchExact() {
		return (mode == SearchMode.EXACT);
	}

	public SearchMode getMode() {
		return mode;
	}

	public Sequence getSearchSequence() {
		return searchSequence;
	}

	public void setSearchSequence(Sequence searchSequence) {
		this.searchSequence = searchSequence;
	}

	public int getNodes() {
		return visited;
	}

	public void setNumNodesVisited(int visited) {
		this.visited = visited;
	}

	public MyList<Sequence> getMatches() {
		return matches;
	}

	public void setMatches(MyList<Sequence> matches) {
		this.matches = matches;
	}

	public boolean searchFound() {
		return this.matches != null && this.matches.length() > 0;
	}
}
