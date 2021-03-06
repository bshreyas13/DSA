
public class LeafNode implements Node {
	private Sequence sequence;
	private int level;

	public LeafNode(Sequence sequence) {
		this.sequence = sequence;
		Print.insertedNode = this;
	}

	@Override
	public void print() {
		Print.node(sequence.toString(), level);
	}

	@Override
	public Node insert(Sequence sequence) {
		if (this.sequence.equals(sequence)) {
			DNAtree.sequenceAlreadyExists(sequence);
			return this;
		} else {
			return new InternalNode(this, sequence);
		}
	}

	@Override
	public Node remove(Sequence sequence) {
		if (this.sequence.equals(sequence)) {
			Print.info(String.format("sequence %s removed", sequence));
			return new EmptyNode();
		} else {
			Print.noSeqFound(sequence);
			return this;
		}
	}

	@Override
	public void search(SearchSequence searchData) {
		searchData.incrementNodesVisited();
		if ((searchData.matchExact() && this.sequence.equals(searchData.getSearchSequence()))
				|| !searchData.matchExact()) {
			searchData.matchFound(this.sequence);
		}
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return sequence.toString();
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getLevel() {
		return level;
	}
}
