
public interface Node {
	public void print();

	public Node insert(Sequence sequence);

	public Node remove(Sequence sequence);

	public void search(SearchSequence command);

	public void setLevel(int level);

	public int getLevel();
}
