
public class EmptyNode implements Node {
	private int level;

	@Override
	public void print() {
		Print.node(this);
	}

	@Override
	public Node insert(Sequence sequence) {
		return new LeafNode(sequence);
	}

	@Override
	public Node remove(Sequence sequence) {
		Print.noSeqFound(sequence);
		return this;
	}

	@Override
	public void search(SearchSequence searchCommand) {
		searchCommand.incrementNodesVisited();
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
