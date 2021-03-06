
public class Tree {
	private Node root = null;

	public Tree() {
		root = new EmptyNode();
	}

	public void insert(Sequence sequence) {
		Print.insertedNode = null;
		root = root.insert(sequence);
		if (root != null) {
			root.setLevel(0);
			Print.printInserted(sequence);
		}
	}

	public void remove(Sequence sequence) {
		root = root.remove(sequence);
	}

	public void dump() {
		Print.info("tree dump:");
		root.print();
	}

	public void search(SearchSequence searchSequence) {
		root.search(searchSequence);
		Print.searchSummary(searchSequence);
	}
}
